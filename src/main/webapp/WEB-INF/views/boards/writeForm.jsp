<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="mb-3 mt-3">
			<input id="title" type="text" class="form-control" placeholder="Enter title">
		</div>
		<div class="mb-3">
			<textarea id="content" class="form-control" rows="8" ></textarea>
		</div>
		<button id="btnWrite" type="button" class="btn btn-primary">글쓰기완료</button>
	</form>
</div>

<script>
	$("#btnWrite").click(()=>{
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		$.ajax("/write", {
			type: "POST",
			dataType: "json",
			data: JSON.stringify(data),
			headers: {
				"Content-Type": "application/json"
			}
		}).done((res) => {
			if (res.code == 1) {
				location.href = "/";
			}
		});
	});
</script>
<%@ include file="../layout/footer.jsp"%>

