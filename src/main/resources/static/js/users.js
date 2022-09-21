let isUsernameSameCheck = false;



//회원가입
$("#btnJoin").click(() => {
	join();
});


//유저네임 중복체크
$("#btnUsernameSameCheck").click(() => {
	checkUsername();
});

//로그인
$("#btnLogin").click(() => {
	login();
});

//회원삭제
$("#btnDelete").click(() => {
	resign();
});

//회원수정
$("#btnUpdate").click(() => {
	update();
});



//메서드들
function join() {
	if (isUsernameSameCheck == false) {
		alert("유저네임 중복 체크를 진행해주세요");
		return;
	}

	if (koreanCheck() == true) {
		alert("한글이 있으면 안됩니다");
		return;
	}

	/*	if (upperCheck() == false) {
			alert("유저네임에 대문자가 있어야합니다");
			return;
		}*/

	if (emailCheck() == false) {
		alert("이메일 형식에 맞게 작성하세요");
		return;
	}

	if (nullCheck() == true) {
		alert("공백을 제거하세요");
		return;
	}

	//0. 통신 오브젝트 생성
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		password: $("#passwordSame").val(),
		email: $("#email").val()
	};

	//ex.비밀번호 확인
	if ($("#password").val() != $("#passwordSame").val()) {
		alert("비밀번호가 일치하지 않습니다.");
		return;
	};

	//1.AJax 통신
	$.ajax("/api/join", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/loginForm";
		} else {
			alert(res.msg);
			history.back();
		}
	});
}

function checkUsername() {
	//0.통신 오브젝트 생성(get 요청은 body가 필요업다)


	//1.사용자가 적은 username 값을 가져오기
	let username = $("#username").val();

	//2.Ajax 통신(db에 잇는지 확인)
	$.ajax(`/users/usernameSameCheck?username=${username}`, {
		type: "GET", //디폴트 get
		dataType: "json",//디폴트 json 나는 json데이터를 기대해 라는 뜻
		async: true
	}).done((res) => {
		if (res.code == 1) {
			if (res.data == false) {
				alert("아이디가 중복되지 않았습니다.");
				isUsernameSameCheck = true;
			} else {
				alert("아이디가 중복되었어요. 다른 아이디를 사용해주세요");
				isUsernameSameCheck = false;
				$("#username").val("");
			}
		}
	});
}


function login() {
	if (koreanCheck() == true) {
		alert("한글이 있으면 안됩니다");
		return;
	}

	if (nullCheck() == true) {
		alert("공백을 제거하세요");
		return;
	}


	//0.통신오브젝트 생성
	let data = {
		username: $("#username").val(),
		password: $("#password").val(),
		remember: $("#remember").prop("checked")
	};

	//1.ajax통신
	$.ajax("/api/login", {
		type: "POST",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			location.href = "/";
		} else {
			alert("로그인실패, 아이디 패스워드를 확인해주세요")
		}
	});
}

function resign() {
	let id = $("#id").val();

	$.ajax("/s/api/users/" + id, {
		type: "DELETE",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			alert("회원 탈퇴 완료");
			location.href = "/";
		} else {
			alert("회원탈퇴에 실패했습니다");
		}
	});
}

function update() {
	if (emailCheck() == false) {
		alert("이메일 형식에 맞게 작성하세요");
		return;
	}

	if (nullCheck() == true) {
		alert("공백을 제거하세요");
		return;
	}

	let data = {
		password: $("#password").val(),
		email: $("#email").val()
	};

	let id = $("#id").val();

	$.ajax("/s/api/users/" + id, {
		type: "PUT",
		dataType: "json",
		data: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json; charset=utf-8"
		}
	}).done((res) => {
		if (res.code == 1) {
			alert("회원 수정 완료");
			location.reload();//f5
		} else {
			alert("업데이트에 실패했습니다");
		}
	});
}


function koreanCheck() {
	let username = $("#username").val();
	let password = $("#password").val();
	let email = $("#email").val();
	let korRule = /^[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]*$/;
	if (korRule.test(username)) {
		return true;
	} else {

	}

	if (korRule.test(password)) {
		return true;
	} else {

	}

	if (korRule.test(email)) {
		return true;
	} else {
		return false;
	}
}



function emailCheck() {
	let email = $("#email").val();
	let emailRule = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	if (emailRule.test(email)) {
		return true;
	} else {
		return false;
	}
}


function nullCheck() {
	let username = $("#username").val();
	let password = $("#password").val();
	let email = $("#email").val();
	let nullRule = /[\s]/g;
	if (nullRule.test(username)) {
		return true;
	} else {

	}
	if (nullRule.test(password)) {
		return true;
	} else {

	}
	if (nullRule.test(email)) {
		return true;
	} else {
		return false;
	}
}

/*function upperCheck() {
	let username = $("#username").val();
	let upperRule = /^[A-Z]*$/;
	if (upperRule.test(username)) {
		return true;
	} else {
		return false;
	}
}*/


