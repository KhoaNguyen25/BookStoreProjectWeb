<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Internal Server Error</title>
<style type="text/css">
body {
	background-color: #c4c9c4bd;
}
.title_error {
	font-size: 20px;
	font-weight: 700;
	font-family: monospace;
}


</style>
</head>
<body>
	<div class="container">
		<h1>
			<img alt="logo" src="${pageContext.request.contextPath}/images/BookstoreLogo.png"
				style="display: block; margin: 0 auto;">
		</h1>
		<h2 style="text-align: center;" class="title_error">Xin lỗi. Máy chủ đang gặp lỗi.</h2>
		<h3 style="text-align: center;" class="title_error">Vui lòng kiểm tra lại sau.</h3>
		<a href="javascript:history.go(-1);">Go Back</a>
	</div>
</body>
</html>