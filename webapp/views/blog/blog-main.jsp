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
				<c:choose>
					<c:when test="${empty postList}">

					</c:when>
					<c:otherwise>
						<table class="tbl-ex">
							<c:forEach items="${commentList }" var="vo" varStatus="status">
								<tr>
									<td style="width: 95%"><img style="padding-left: 10px;"
										src="${pageContext.request.contextPath}/assets/images/reply.png">
										${vo.content}</td>
									<td><a href="${pageContext.request.contextPath}/${userVo.id}/${postVo.category_no }/${postVo.no }/${vo.no}/comment/delete">
									<img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>
									</td>
								</tr>
							</c:forEach>
						</table>

						<form class="board-form" method="post"
							action="${pageContext.request.contextPath}/${userVo.id}/${postVo.category_no }/${postVo.no }/comment/insert">
							<table class="tbl-ex">
								<tr class="tr-ex">
									<td class="label">댓글</td>
									<td><input type="text" name="content" value=""></td>
									<td><input type="submit" value="댓글"></td>
								</tr>
							</table>
						</form>
					</c:otherwise>
				</c:choose>

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
				<img
					onerror="this.src='${pageContext.request.contextPath }/assets/images/default_profile.png'"
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