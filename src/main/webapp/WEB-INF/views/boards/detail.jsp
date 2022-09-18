<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<br /> <br />

	<c:if test="${principal.id == boards.usersId}">
	<div class="d-flex">
	<a href="/boards/${boards.id}/updateForm" class="btn btn-warning">수정하러가기</a>
		<form>
			<button id="btnDelete" class="btn btn-danger">삭제</button>
		</form>
	</div>
		</c:if>
		
	<br />
	<div class="d-flex justify-content-between">
		<h3>${boards.title}</h3>
		<div>좋아요수:10 <i id="iconHeart" class="fa-regular fa-heart"></i></div>
	</div>
	<hr />

	<div>${boards.content}</div>
</div>

<script src="/js/boards.js">
</script>
<%@ include file="../layout/footer.jsp"%>

