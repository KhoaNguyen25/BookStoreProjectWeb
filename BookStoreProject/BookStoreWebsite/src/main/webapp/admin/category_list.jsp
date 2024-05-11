<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>List Category - Evergeen Book Administrative</title>
<link rel="stylesheet" href="../css/admin/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div align="center">
		<h2>Category Management</h2>
		<h3>
			<a href="category_form.jsp">Create New Category</a>
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
				<th>Category Name</th>
				<th>Actions</th>
			</tr>

	
			<c:forEach var="category" items="${listCategory}" varStatus="status">
				<tr>
					<td align="center">${status.index + 1}</td>
					<td align="center" class="id_category">${category.categoryId}</td>
					<td align="center">${category.name}</td>
					
					<td align="center"><a href="edit_categories?id=${category.categoryId}" >Edit</a> | 
					<a href="javascript:void(0);" class="listDelete" id=${category.categoryId}>Delete</a></td>
				</tr>
			</c:forEach>
 	
		</table>
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$(".listDelete").each(function(){
				$(this).on('click',function(){
					var categoryId = $(this).attr("id");
					if (confirm("Are you sure you want to delete the category width ID "
							+ categoryId + " ?")) {
							window.location = "delete_category?id=" + categoryId;
					}
				});
			});	
		});
	</script>
</body>
</html>
