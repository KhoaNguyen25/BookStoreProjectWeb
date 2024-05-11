<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>List Book - Evergeen Book Administrative</title>
<link rel="stylesheet" href="../css/admin/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div align="center">
		<h2>Book Management</h2>
		<h3>
			<a href="create_book">Create New Book</a>
		</h3>
		<c:if test="${message != null}">
			<h4>
				<i>${message}</i>
			</h4>
		</c:if>

	</div>

	<div align="center">
		<table border="1" width="60%">
			<tr>
				<th>Index</th>
				<th>Id</th>
				<th>Image</th>
				<th>Title</th>
				<th>Author</th>
				<th>Category</th>
				<th>Price</th>
				<th>Last Updated</th>
				<th>Actions</th>
			</tr>
	<c:forEach var="item" items="${listBooks}" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${item.bookId}</td>
				<td><img src="data:image/jpg;base64,${item.base64Image}" alt="${item.title}" style="width: 140px;height: 170px;display: block;object-fit: contain;"></td>
				<td>${item.title}</td>
				<td>${item.author}</td>
				<td>${item.category.name}</td>
				<td>$ ${item.price}</td>
				<td> <fmt:formatDate pattern='MM/dd/yyyy' value="${item.lastUpdateTime}"/> </td>
				<td>
				<a href="edit_book?id=${item.bookId}" >Edit</a> | <a class="deleteBook" href="javascript:void(0);" id="${item.bookId}">Delete</a>
				</td>

			</tr>
	</c:forEach>
		</table>
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>

	<script type="text/javascript">
		$(document).ready(function(){
			$(".deleteBook").each(function(){
				$(this).on('click',function(){
					var id = $(this).attr('id');
					if(confirm("Are you sure you want to delete the book width ID: " +id+" ")){
						window.location="delete_book?id="+id;
					}
					
				});
			});
		});
	</script>
</body>
</html>
