<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergeen Book Website's</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<jsp:include page="header.jsp" />

	<div align="center">
		<h2>This is Content:</h2>
		
		<h3>New Books:</h3>
		<div class="grid-container-index">
			<c:forEach items="${listBook}" var="item">
				<div class="book">
					<div><a href="view_book?id=${item.bookId}" title="${item.title}"><img src="data:images/jpg;base64,${item.base64Image}" width="120" height="150" /></a></div>
					<div class="book_title">
						${item.title}
					</div>
					<div>Rating *****</div>
					<div class="book_author">
						by ${item.author}
					</div>
					<div>
						$${item.price}
					</div>
				</div>
			</c:forEach>
		</div>
		<h3>Best-Selling Books:</h3>
		<h3>Most-Favored Books:</h3>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>