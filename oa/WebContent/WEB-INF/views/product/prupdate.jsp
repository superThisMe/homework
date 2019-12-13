<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>자료업로드</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input2.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/includes/myheader.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">제품 정보</div>
		        <form action="update.action"
		        	  method="post" enctype="multipart/form-data">
		        <input type="hidden" name="productNo" value="${ product.productNo }">
		        <table>
		            <tr>
		                <th>제품명</th>
		                <td>
		                    <input type="text" name="productName" style="width:580px" value="${ product.productName }" />
		                </td>
		            </tr>
		            <tr>
		                <th>가격</th>
		                <td>
		             		<input type="text" name="price" style="width:580px" value="${ product.price }" />
		                </td>
		            </tr>
		            <tr>
		                <th>판매량</th>
		                <td>
		             		<input type="text" name="sales" style="width:580px" value="${ product.sales }" />
		                </td>
		            </tr>
		            <tr>
		                <th>출시일</th>
		                <td>
		             		${ product.regDate }
		                </td>
		            </tr>
		            <tr>
		                <th>제품사진</th>
		                <td>
			                <c:forEach var="pic" items="${ product.productPics }">
			                <c:choose>
		                	<c:when test="${ pic.active }">
		                	<img src="/oa/upload-files/${ pic.savedPicName }" alt="${ pic.userPicName }" width="500px">
			                <a href="delete-pic.action?picNo=${ pic.picNo }">[x]</a>
			                <br>
		                	</c:when>
		                	<c:otherwise>
		                	<span style="color:lightgray">${ pic.userPicName } (삭제된 사진)</span>
		                	<br>
		                	</c:otherwise>
		                	</c:choose>
			                </c:forEach>
		                    <input type="file" name="attach" style="width:580px;height:25px" />
		                </td>
		            </tr>
		            <tr>
						<th>
							<input type="button" class="addpic" value="+" style="height: 20px">
						</th>
						<td class="picspace"></td>
					</tr>
		            <tr class="addpicbutton">
		                <th>제품매뉴얼</th>
		                <td>
			                <c:forEach var="manual" items="${ product.productManuals }">
			                <c:choose>
		                	<c:when test="${ manual.active }">
			                <a href="/oa/upload-files/${ manual.savedManualName }">${ manual.userManualName }</a>
			                <a href="delete-manual.action?manualNo=${ manual.manualNo }">[x]</a>
			                <br>
		                	</c:when>
		                	<c:otherwise>
		                	<span style="color:lightgray">${ manual.userManualName } (삭제된 매뉴얼)</span>
		                	<br>
		                	</c:otherwise>
		                	</c:choose>
			                </c:forEach>
		                    <input type="file" name="attach" style="width:580px;height:25px" />
		                </td>
		            </tr>
		            <tr>
						<th>
							<input type="button" class="addmanual" value="+" style="height: 20px">
						</th>
						<td class="manualspace"></td>
					</tr>
		            <tr class="addmanualbutton">
		                <th>설명</th>
		                <td>
		                	<textarea name="content" style="width:580px" rows="15">${ product.content }</textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<input type="submit" value="자료등록" style="height:25px" />
		        	<input type="button" id="cancel" value="취소" style="height:25px"  />
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>


	<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
	<script type="text/javascript">
	$(function() {
		$('#cancel').on('click', function(event) {
			history.back();
			//location.href = "list.action";
		});
		var a;
		do {
			a = false;
			$('form').on('click', '.addpic', function(event) {
				$(this).parent().next().append(`<input type="file" name="attach" style="width:580px;height:25px">`);
				$('.addpicbutton').before(`<tr>
		            	<th>
	            		<input type="button" class="addpic" value="+" style="height:20px">
	            	</th>
	            	<td class="picspace"></td>
	            </tr>`);
				$(this).remove();
				a = true;
			});
		} while (a)
		var b;
		do {
			b = false;
			$('form').on('click', '.addmanual', function(event) {
				$(this).parent().next().append(`<input type="file" name="attach" style="width:580px;height:25px">`);
				$('.addmanualbutton').before(`<tr>
		            	<th>
	            		<input type="button" class="addmanual" value="+" style="height:20px">
	            	</th>
	            	<td class="manualspace"></td>
	            </tr>`);
				$(this).remove();
				b = true;
			});
		} while (b)
	});
	</script>
</body>
</html>