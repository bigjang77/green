<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="mb-3 mt-3">
			<input id="username" type="text" class="form-control" placeholder="Enter username">
			<button id="btnUsernameSameCheck" class="btn btn-warning" type="button">유저네임중복체크</button>
		</div>
		<div class="mb-3">
			<input id="password" type="password" class="form-control" placeholder="Enter password">
		</div>
		<div class="mb-3">
			<input id="email" type="email" class="form-control" placeholder="Enter email">
		</div>
		<button id="btnJoin" type="button" class="btn btn-primary">회원가입</button>
	</form>
</div>

<script>
	let isUsernameSameCheck = false;

	//회원가입
	$("#btnJoin").click(()=>{
		if(isUsernameSameCheck == false){
			alert("유저네임 중복 체크를 진행해주세요");
			return;
		}
		//0. 통신 오브젝트 생성
		let data = {
				username: $("#username").val(),
				password: $("#password").val(),
				email: $("#email").val()
		};
		
		//2.AJax 통신
		$.ajax("/join",{
			type:"POST",
			dataType: "json",
			data: JSON.stringify(data),
			headers : {
				"Content-Type" : "application/json"
			}
		}).done((res)=>{
			if(res.code == 1){
				location.href="/";
			}
		});
	});
	
	
	//유저네임 중복체크
	$("#btnUsernameSameCheck").click(()=>{
		//0.통신 오브젝트 생성(get 요청은 body가 필요업다)

		
		//1.사용자가 적은 username 값을 가져오기
		let username = $("#username").val();
		
		//2.Ajax 통신(db에 잇는지 확인)
		$.ajax("/users/usernameSameCheck?username="+username,{
			type:"GET", //디폴트 get
			dataType: "json",//디폴트 json 나는 json데이터를 기대해 라는 뜻
			async: true
		}).done((res)=>{
			console.log(res);
			if(res.code == 1){
				if(res.data == false){
					alert("아이디가 중복되지 않았습니다.");
					isUsernameSameCheck = true;
				}else{
					alert("아이디가 중복되었어요. 다른 아이디를 사용해주세요");
					isUsernameSameCheck = false;
					$("#username").val("");
				}
			}
		});
	});
	
	
</script>


<%@ include file="../layout/footer.jsp"%>

