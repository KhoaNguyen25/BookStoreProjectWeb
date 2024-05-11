<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {
	background-color: #c4c9c4bd;
}
.title_error {
	font-size: 20px;
	font-weight: 700;
	font-family: monospace;
}

.trang-chu {
	padding: 10px 21px;
	background-color: gray;
	border-radius: 5%;
	text-decoration: none;
	color: aliceblue;
	font-size: 20px;
	font-weight: 700;
	font-family: monospace;
	display: inline-block;
	transition: all 0.3s ease;
}
.trang-chu:hover{
	background-color: blue;
}

.button_trangchu{
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>
			<img alt="logo" src="${pageContext.request.contextPath}/images/BookstoreLogo.png"
				style="display: block; margin: 0 auto;">
		</h1>
		<h2 style="text-align: center;" class="title_error">Xin lỗi. Vui
			lòng chuyển hướng về trang chủ</h2>
		<div class="button_trangchu"><a href="javascript:history.go(-1);" class="trang-chu">Trang chủ</a></div>
	</div>
</body>
</html>