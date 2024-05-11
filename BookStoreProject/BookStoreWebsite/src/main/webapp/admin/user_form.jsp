<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Create New User - Evergeen Book Administrative</title>
<link rel="stylesheet" href="../css/admin/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />


	<div align="center">
		<c:if test="${user != null}">
			<h2>Eidt User</h2>
		</c:if>
		<c:if test="${user == null }">
			<h2>Create New User</h2>
		</c:if>

	</div>

	<div align="center">
		<c:if test="${user != null}">
			<form action="update_user" method="post" id="id_form">
			<input type="hidden" name="userId" value="${user.userId}" />
		</c:if>
		<c:if test="${user == null}">
			<form action="create_user" method="post" id="id_form">
		</c:if>
			<table border="1">
				<tr>
					<td>Full Name:</td>
					<td><input type="text" name="fullName" id="fullname"
						placeholder="Nguyen van A" value="${user.fullName}" /></td>
				</tr>
				<tr>
					<td>Email:</td>
					<td><input type="text" name="email" id="email"
						placeholder="a@gmail.com" value="${user.email}" /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type="password" name="password" id="password"  value="${user.password}"/></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Submit" /> <input
						type="button" value="Cancel"
						onclick="window.history.go(-1); return false;" /></td>
				</tr>
			</table>
		</form>
	</div>

	<jsp:include page="footer.jsp" />
</body>
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#id_form").validate({
			rules:{
				email : {
					required : true,
					email: true
				},
				fullName: "required",
				password : {
					required: true,
					minlength: 3
				},
			},
			messages:{
				email: {
					required: 'Required enter to Email',
					email: "Please enter the correct email address",
				},
				fullName: "Please enter FullName",
				
				password: {
					required: "Please enter password",
					minlength: "Please enter at least 8 elements"
					
				}
			}
		});
	});
</script>
</html>
