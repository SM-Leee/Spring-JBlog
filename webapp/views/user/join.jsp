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
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
	$(function() {
		/* $("#join-form").submit(function() {
			//이름체크
			if ($("#name").val() == "") {
				alert("이름을 입력해주세요.")
				$("#name").focus();
				return false;
			}
			//id체크
			if ($("#blog-id").val() == "") {
				alert("ID를 입력해주세요.")
				$("#blog-id").focus();
				return false;
			}
			//비밀번호체크
			if ($("#password").val() == "") {
				alert("비밀번호를 입력해주세요.")
				$("#password").focus();
				return false;
			}
			//약관동의
			if ($("#agree-prov").is(":checked") == false) {
				alert("약관동의 해주세요.");
				return false;
			}
			return true;
		}); */
		$("#id").change(function() {
			$("#btn-checkemail").show();
			$("#img-checkemail").hide();
		});

		$("#btn-checkemail")
				.click(
						function() {
							var id = $("#blog-id").val();
							if (id == "") {
								return;
							}
							$
									.ajax({
										url : "${pageContext.servletContext.contextPath}/user/checkid?id="
												+ id,
										type : "get",
										dataType : "json",
										data : "",
										success : function(response) {
											if (response.result == "fail") {
												console.error(response.message);
												return;
											}
											if (response.data == true) {
												alert("이미존재하는 아이디 입니다. 다른 아이디를 사용해주세요");
												("#blog-id").val("").focus();
											} else {
												$("#btn-checkemail").hide();
												$("#img-checkemail").show();
											}
										},
										error : function(shr, status, e) {
											console.error(status + ":" + e);
										}
									});
						});
	});
</script>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		<c:import url="/views/includes/header.jsp" />
		<form:form modelAttribute="userVo" class="join-form" id="join-form"
			method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<input id="name" name="name" type="text" value="${userVo.name}">
			<spring:hasBindErrors name="userVo">
				<c:if test="${errors.hasFieldErrors('name') }">
					<p style="padding: 0; text-align: left; color: red;">
						<strong> <spring:message
								code="${errors.getFieldError( 'name' ).codes[0]}"
								text="${errors.getFieldError( 'name' ).defaultMessage}" />
						</strong>
					</p>
				</c:if>
			</spring:hasBindErrors>

			<label class="block-label" for="id">아이디</label>
			<form:input path="id" />
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">
			<p
				style="margin: 0; padding: 0; font-weight: bold; color: red; text-align: left;">
				<form:errors path="id" />
			</p>


			<label class="block-label" for="password">패스워드</label>
			<form:input path="password" type="password" />
			<p
				style="margin: 0; padding: 0; font-weight: bold; color: red; text-align: left;">
				<form:errors path="password" />
			</p>
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
