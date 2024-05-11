<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Result in ${keywork} Online Book store</title>
<link rel="stylesheet" href="./css/style.css" />
<link rel="stylesheet" href="./css/search.css" />
</head>
<body>
	<jsp:include page="header.jsp" />

	<c:if test="${fn:length(result) == 0}">
		<h2 align="center">No Results for "${keywork}"</h2>
	</c:if>
	<c:if test="${fn:length(result) > 0}">
		<h2 align="center">Results for "${keywork}"</h2>
		<c:forEach var="item" items="${result}">

			<div class="search">
				<div class="search--image">
					<a href="view_book?id=${item.bookId}" title="${item.title}">
					<img alt="logo" src="data:images/jpg;base64,${item.base64Image}" class="search--item__image">
					</a>
				</div>
				<div class="search--content">
					<h4>
						<a href="view_book?id=${item.bookId}" title="${item.title}">${fn:substring(item.title, 0 , 40)}...</a>
					</h4>
					<p>Rating ****</p>
					<p>by <i>${fn:substring(item.author, 0 , 40)}</i>...</p>
					<p>${fn:substring(item.description, 0 , 100)}...</p>
				</div>
				<div class="search--price">
					<p class="search--item__price">$${item.price }</p>
					<p class="search--item__cart">
						<a class="">Add To Cart</a>
					</p>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<jsp:include page="footer.jsp" />
</body>
</html>