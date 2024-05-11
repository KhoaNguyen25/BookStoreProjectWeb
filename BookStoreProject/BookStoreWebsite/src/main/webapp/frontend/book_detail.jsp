<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${book.title} - Evergeen Book Website's</title>
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<div class="book-detail">
			<div class="book_item1">
				<h3>${book.title}</h3>
				<p>by ${book.author}</p>
				<div class="">
					<img src="data:images/jpg;base64,${book.base64Image}" width="200"
						height="300" />
				</div>
			</div>
			<div class="book_item2">
				<p>
					***** <a href="">4 Reviews</a>
				</p>
				<form action="" class="form-review" id="">
					<textarea rows="5" cols="50"></textarea>
					<label>Customer review</label>
					<input type="submit" value="Write a customer review" class="input-text-review" name="review" />
				</form>
			</div>
			<div class="book_item3">
				<p class="book-detail__price">
					${book.price}
				</p>
				<a href="" id="${book.bookId}" >Add to cart</a>
			</div>
		</div>
		<div class="book-description">
			<p>${book.description}</p>
		</div>

	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>