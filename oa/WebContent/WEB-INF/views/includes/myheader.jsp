<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${ not empty param.bgColor }">
		<div id="header" style="background-color:${ param.bgColor }">
	</c:when>
	<c:otherwise>
		<div id="header">
	</c:otherwise>
</c:choose>

<div class="title">
	<a href="/oa/myhome.action">OA Sales WEBSITE</a>
</div>
<!-- 로그인 기능 미구현
<div class="links">

	<c:choose>
		<c:when test="${empty loginuser}">
			<a href="/demoweb/account/login.action">로그인</a>
			<a href="/demoweb/account/register.action">회원가입</a>
		</c:when>
		<c:otherwise>
			<a href="/demoweb/account/update.action"> ${ loginuser.memberId }
			</a>님 환영합니다.
                <a href="/demoweb/account/logout.action">로그아웃</a>
		</c:otherwise>
	</c:choose>

</div>
-->
</div>

<div id="menu">
	<div>
		<ul>
			<li><a href="#">회사소개(미구현)</a></li>
			<li><a href="/oa/product/prlist.action">제품소개</a></li>
			<li><a href="#">문의게시판(미구현)</a></li>
			<li><a href="#">contact us(미구현)</a></li>
		</ul>
	</div>
</div>
