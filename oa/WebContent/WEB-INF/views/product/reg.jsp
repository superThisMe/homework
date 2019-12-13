<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8" />
<title>자료업로드</title>
<link rel="Stylesheet" href="/oa/styles/default.css" />
<link rel="Stylesheet" href="/oa/styles/input2.css" />
</head>
<body>

	<div id="pageContainer">

		<jsp:include page="/WEB-INF/views/includes/myheader.jsp" />

		<div style="padding-top: 25px; text-align: center">
			<div id="inputcontent">
				<div id="inputmain">
					<div class="inputsubtitle">제품 정보 등록</div>
					<form action="reg.action" method="post"
						enctype="multipart/form-data">
						<table>
							<tr>
								<th>제품명</th>
								<td><input type="text" name="productName"
									style="width: 580px" /></td>
							</tr>
							<tr>
								<th>가격(원)</th>
								<td><input type="text" name="price" style="width: 300px" />
								</td>
							</tr>
							<tr>
								<th>제품사진</th>
								<td><input type="file" name="attach"
									style="width: 580px; height: 25px" /></td>
							</tr>
							<tr>
								<th><input type="button" class="addpic" value="+"
									style="height: 20px"></th>
								<td class="picspace"></td>
							</tr>
							<tr class="addpicbutton">
								<th>제품매뉴얼</th>
								<td><input type="file" name="attach"
									style="width: 580px; height: 25px" /></td>
							</tr>
							<tr>
								<th><input type="button" class="addmanual" value="+"
									style="height: 20px"></th>
								<td class="manualspace"></td>
							</tr>
							<tr class="addmanualbutton">
								<th>설명</th>
								<td><textarea name="content" style="width: 580px" rows="15"></textarea>
								</td>
							</tr>
						</table>
						<div class="buttons">
							<input type="submit" value="자료등록" style="height: 25px" /> <input
								id="cancel" type="button" value="취소" style="height: 25px" />
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
			//history.back();
			location.href = "prlist.action";
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