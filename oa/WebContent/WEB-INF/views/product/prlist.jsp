<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>자료 목록</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<style>
	a {
	text-decoration: none;
	}
	</style>
</head>
<body>

	<div id="pageContainer">
	
	
		<jsp:include page="/WEB-INF/views/includes/myheader.jsp" />
		
		<div style="padding-top:25px;text-align:center">
			[ <a href="reg.action">제품 등록</a> ]
			<br /><br />
			<table border="1" style="width:600px;margin:0 auto">
				<tr style="background-color:orange;height:30px">
					<th style="width:150px">제품번호</th>
					<th style="width:500px">제품명</th>
					<th style="width:150px">가격</th>
					<th style="width:150px;text-align:center">출시일</th>
					<th style="width:150px">판매량</th>
				</tr>
				
				<c:forEach var="product" items="${ requestScope.prList }">
				<tr style="height:30px">
					<td>${ product.productNo }</td>
					<td>
					<c:choose>
					<c:when test="${ product.active }">
					<a href="prdetail.action?productNo=${ product.productNo }">${ product.productName }</a>
					</c:when>
					<c:otherwise>
					<span style="color:lightgray">${ product.productName } (삭제된 제품)</span>
					</c:otherwise>
					</c:choose>
					</td>
					<td>${ product.price }</td>
					<td>${ product.regDate }</td>
					<td>${ product.sales }</td>
				</tr>
				</c:forEach>
				
			</table>
			<br /><br /><br /><br />
		
		</div>
		
	</div>
		

</body>
</html>











