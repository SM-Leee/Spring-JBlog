<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	var no = 0;
	var categoryNo = 0;
	var id = "${authUser.id}"
	//var post_value_zero = if(vo.post_value==0){"<img src=${pageContext.request.contextPath}/assets/images/delete.jpg>"};
	var render = function(vo, mode) {
		no++;
		var htmls = 
			"<tr data-no='"+vo.no+"'>"
				+ "<th>"+ no+ "</th>"
				+ "<th>"+ vo.name+ "</th>"
				+ "<th>"+ vo.post_value	+ "</th>"
				+ "<th>"+ vo.description+ "</th>"
				+ "<th>"+ "<a href='' data-no='"+vo.no+"'><img src=${pageContext.request.contextPath}/assets/images/delete.jpg>"+ "</th>" +
			"</tr>";
		if (mode == true) {
			$(".admin-cat").append(htmls);
		} else {
			$(".admin-cat").prepend(htmls);
		}

	}
	$(function() {
		$.ajax({
			url : "${pageContext.request.contextPath}/" + id
					+ "/admin/category/list",
			type : "get",
			dataType : "json",
			data : "",
			success : function(response) {

				if (response.result == "fail") {
					console.warn(response.date);
					return;
				}

				$.each(response.data, function(index, vo) {
					console.log(vo);
					render(vo, true);
					if (vo.post_value != 0) {
						$(".admin-cat tr th a").hide();
					}
				});
			},
			error : function(xhr, status, e) {
				console.error(status + ":" + e);
			}
		});

		$(".admin-cat-form").submit(
				function(event) {
					event.preventDefault();

					var name = $("#name").val();
					var description = $("#description").val();

					if (name == "") {
						alert("이름은 필수항목입니다.");
						$(name).focus();
						return false;
					}
					if (description == "") {
						alert("설명은 필수항목입니다.");
						$(description).focus();
						return false;
					}

					$.ajax({
						url : "${pageContext.request.contextPath}/" + id
								+ "/admin/category/insert",
						type : "post",
						dataType : "json",
						data : "name=" + name + "&description=" + description,
						success : function(response) {
							if (response.result == "fail") {
								console.warn(response.data);
								return;
							}
							render(response.data, true);
							$("#name").val("");
							$("#description").val("");							
						},
						error : function(xhr, status, e) {
							console.error(status + ":" + e);
						}

					});
				});
		$(document).on(
				"click",
				".admin-cat tr th a",
				function() {
					categoryNo = $(this).data("no");
					console.log("categoryNO" + categoryNo);
					console.log("click : " + no)

					$.ajax({
						url : "${pageContext.request.contextPath}/" + id
								+ "/admin/category/delete",
						type : "post",
						dataType : "json",
						data : "no=" + categoryNo,
						success : function(response) {
							if (response.data) {
								console.log("삭제성공");
								$("#admin-cat tr[data-no=" + categoryNo + "]")
										.remove();
							} else {
								console.log("삭제실패");
							}
						},
						error : function(xhr, status, e) {
							console.error(status + " : " + e);
						}
					});
				});
	});
</script>
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/includes/blog-header.jsp" />
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/views/includes/blog-admin-header.jsp">
					<c:param name="menu" value="category" />
				</c:import>
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>

				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form class="admin-cat-form">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name" id="name"></td>

						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="desc" id="description"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div id="footer">
			<c:import url="/views/includes/footer.jsp" />
		</div>
	</div>
</body>
</html>