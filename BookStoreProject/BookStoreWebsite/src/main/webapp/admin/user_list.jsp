<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>List Users - Evergeen Book Administrative</title>
<link rel="stylesheet" href="../css/admin/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div align="center">
		<h2>Users Management</h2>
		<h3>
			<a href="user_form.jsp">Create New User</a>
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
				<th>Email</th>
				<th>Full Name</th>
				<th>Actions</th>
			</tr>


			<c:forEach var="user" items="${listUser}" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${user.userId}</td>
					<td>${user.email}</td>
					<td>${user.fullName}</td>
					<td><a href="edit_user?id=${user.userId}">Edit</a> &nbsp; <a
						href="javascript:void(0);" id="${user.userId}" class="linkDelete">Delete</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$(".linkDelete").each(function(){
			$(this).on('click',function(){
				var userId = $(this).attr('id');
				if (confirm("Are you sure you want to delete the user width ID "
						+ userId + " ?")) {
						window.location = "delete_user?id=" + userId;
				}
			});
		});
	});
	</script>
</body>
</html>
