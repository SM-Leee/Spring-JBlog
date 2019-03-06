<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<div id="header">
			<c:import url="/views/includes/blog-header.jsp" />
		</div>
		<div id="wrapper">
			<div id="content">
				<c:import url="/views/includes/blog-admin-header.jsp" />
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>${postVo.content}
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${postList }" var="vo" varStatus="status">
						<li><a
							href="${pageContext.request.contextPath}/${userVo.id}/${vo.category_no }/${vo.no }">${vo.title }</a>
							<span>${vo.reg_date}</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img onerror="this.src='${pageContext.request.contextPath }/assets/images/default_profile.png'"
				src="${pageContext.request.contextPath}/assets${blogVo.logo}">
				<%-- <img src="${pageContext.request.contextPath }/assets/images/default_profile.png"> --%>
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${categoryList }" var="vo" varStatus="status">
					<li><a
						href="${pageContext.request.contextPath}/${userVo.id}/${vo.no}">${vo.name }</a></li>
				</c:forEach>
			</ul>
		</div>

		<div id="footer">
			<c:import url="/views/includes/footer.jsp" />
		</div>
	</div>
</body>
</html>