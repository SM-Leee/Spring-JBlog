<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<h1>
	<a href="${pageContext.request.contextPath}/${userVo.id}">${blogVo.title }</a>
</h1>

<ul>
	<c:choose>
		<c:when test="${empty authUser}">
			<li><a href="${pageContext.request.contextPath}/user/auth">로그인</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>

			<c:if test="${authUser.no == blogVo.user_no}">
				<li><a
					href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">블로그
						관리</a></li>
			</c:if>
			<c:if test="${authUser.no != blogVo.user_no }">
			<li><a href="${pageContext.request.contextPath}/${authUser.id}">내블로그</a></li>
			</c:if>
		</c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${authUser.no == blogVo.user_no}">
			<li><a href="${pageContext.request.contextPath}/${authUser.id }">메인페이지</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/${userVo.id }">메인페이지</a></li>
		</c:otherwise>
	</c:choose>
</ul>

