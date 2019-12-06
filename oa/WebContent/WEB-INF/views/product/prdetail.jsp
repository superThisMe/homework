<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><%-- JSTL의 함수를 제공하는 taglib --%>
    
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>자료업로드</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/includes/myheader.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">제품 정보</div>
		        <table>
		            <tr>
		                <th>제품명</th>
		                <td>${ product.productName }</td>
		            </tr>
		            <tr>
		                <th>가격</th>
		                <td>${ product.price }</td>
		            </tr>
		            <tr>
		            	<th>판매량</th>
		            	<td>${ product.sales }</td>
		            </tr>
		            <tr>
		            	<th>출시일</th>
		            	<td>${ product.regDate }</td>
		            </tr>
		            <tr>
		                <th>제품 사진</th>
		                <td>
		                <c:forEach var="pic" items="${ product.productPics }">
		                <c:choose>
		                <c:when test="${ pic.active }">
		                <img src="C:\workspace\web-application\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\oa\upload-files\${ pic.savedPicName }">
		                </c:when>
		                <c:otherwise>
		                ${ pic.userPicName } (삭제된 사진)
		                </c:otherwise>
		                </c:choose>
		                </c:forEach>
		                </td>
		            </tr>
		            <tr>
		                <th>자료설명</th>
<%-- 줄바꿈 문자열을 저장하고 있는 변수 만들기(EL) --%>	
<c:set var="enter" value="
" />
		                <%-- upload.content 문자열에서 \r\n을 <br>로 변경 --%>
		                <td>${ fn:replace(product.content, enter, "<br>") }</td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<input type="button" id="update_button" value="편집" style="height:25px" />
		        	
		        	<form action="delete.action" style="display:inline"
		        	  method="post">
		        	<input type="hidden" name="productNo" value="${ product.productNo }" />
		        	<input type="submit" id="delete_button" value="삭제" style="height:25px" />
		        	</form>
		        	
		        	<input type="button" id="cancel_button" value="목록보기" style="height:25px" />
		        	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
		        	<script type="text/javascript">
		        	//브라우저가 html을 모두 읽고 처리할 준비가 되었을 때 호출할 함수 지정
		        	$(function() {//js의 main 함수 역할
		        		$("#cancel_button").on('click', function(event) {
		        			location.href = "prlist.action"; //주소창에 list.action을 입력하고 엔터
		        			//history.back(); // 브라우저의 이전 버튼을 클릭
		        		});
		        		
		        		$("#delete_button").on('click', function(event) {
		        			var ok = confirm("${product.productdNo}번 제품을 삭제할까요?");
		        			if (!ok) {
			        			//<a 를 통한 요청이므로 주소 뒤에 ?key=value 형식을 써서 데이터 전송
			        			event.preventDefault();
		        			}
		        		});
		        		
		        		$("#update_button").on('click', function(event) {
		        			location.href = "update.action?productNo=${ product.productNo }";
		        		});
		        		
		        	});
		        	</script>
		        </div>
		    </div>
		</div>   	
	
	</div>
	</div>

</body>
</html>