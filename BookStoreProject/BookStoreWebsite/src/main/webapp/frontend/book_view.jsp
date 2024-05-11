<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book in ${category.name} - Evergeen Book Website's</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<jsp:include page="header.jsp" />
		
	<div align="center">
		<h3>${category.name}</h3>
		<div class="grid-container">
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
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>