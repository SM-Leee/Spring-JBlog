<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<ul class="admin-menu">
	<c:choose>
		<c:when test='${param.menu == "basic" }'>
			<li class="selected"><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
			<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
			<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/write">글작성</a></li>
		</c:when>
		<c:when test='${param.menu == "category" }'>
			<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
			<li class="selected"><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
			<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/write">글작성</a></li>
		</c:when>
		<c:when test='${param.menu == "write" }'>
			<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/basic">기본설정</a></li>
			<li><a href="${pageContext.request.contextPath}/${authUser.id }/admin/category">카테고리</a></li>
			<li class="selected"><a href="${pageContext.request.contextPath}/${authUser.id }/admin/write">글작성</a></li>
		</c:when>
	</c:choose>
</ul>
