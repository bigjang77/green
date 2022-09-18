

$("#btnDelete").click(() => {
	remove();
});

function remove (){
	let id = $("#id").val();

	$.ajax("/boards/" + id, {
		type: "DELETE",
		dataType: "json"
	}).done((res) => {
		if (res.code == 1) {
			alert("글삭제 완료");
			location.href = "/";
		} else {
			alert("글삭제에 실패했습니다");
		}
	});
};
