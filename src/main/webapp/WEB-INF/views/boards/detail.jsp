<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<input id="page" type ="hidden" value="${sessionScope.referer.page}">
<input id="keyword" type ="hidden" value="${sessionScope.referer.keyword}">
<div class="container">
	<br /> <br />

	<div class="d-flex">
	<a href="/boards/${boards.id}/updateForm" class="btn btn-warning">수정하러가기</a>
		<form>
				<input id="id" type="hidden" value="${boards.id}"/>
				<button id="btnDelete" class="btn btn-danger">삭제</button>
		</form>
	</div>
		
	<br />
	<div class="d-flex justify-content-between">
		<h3>${boards.title}</h3>
		<div>좋아요수:10 <i id="iconHeart" class="fa-regular fa-heart"></i></div>
	</div>
	<hr />

	<div>${boards.content}</div>
</div>

<script>
	$("#iconHeart").click((event)=>{
		
	});

	$("#btnDelete").click(() => {
		remove();
	});

	function remove(){
		let id = $("#id").val();

		let page = $("#page").val();
		let keyword = $("#keyword").val();
		
		$.ajax("/boards/" + id, {
			type: "DELETE",
			dataType: "json"
		}).done((res) => {
			if (res.code == 1) {
				//document.referer로 찾으면 제일쉬움 location href = document.referrer;
				location.href = "/?page="+page+"&keyword="+keyword;//?page=?keyword
			} else {
				alert("글삭제에 실패했습니다");
			}
		});
	};
</script>
<%@ include file="../layout/footer.jsp"%>

