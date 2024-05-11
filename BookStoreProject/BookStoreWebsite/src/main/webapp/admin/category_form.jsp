<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Category - Evergeen Book Administrative</title>
<link rel="stylesheet" href="../css/admin/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<c:if test="${category != null}">
			<h2>Edit New Category</h2>
		</c:if>
		<c:if test="${category == null}">
			<h2>Create New Category</h2>
		</c:if>

		<c:if test="${category != null}">
			<form action="update_categories" method="POST" id="id_form">
				<input name="categoryId" type="hidden"
					value="${category.categoryId }" id="categoryId" />
		</c:if>
		<c:if test="${category == null}">
			<form action="create_categories" method="POST" id="id_form">
		</c:if>


		<table border="1">
			<tbody>

				<tr>
					<td>Name *:</td>
					<td><input name="name" type="text" placeholder="Enter name"
						value="${category.name }" id="name" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="Save" value="Save" /> <input
						type="button" name="Cancel" value="Cancel"
						onclick="windows:history.go(-1); return false;" /></td>
				</tr>
			</tbody>
		</table>
		</form>
	</div>
	<jsp:include page="footer.jsp" />

	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script>
	$(document).ready(function(){
		$("#id_form").validate({
			rules: {
				name: {
					required :true,
					minlength: 2
				}
			},
			messages: {
				name: {
					required: "Please enter name",
					minlength: "Please enter at least 8 elements"
				}
			}
		});
	});
	</script>
</body>
</html>