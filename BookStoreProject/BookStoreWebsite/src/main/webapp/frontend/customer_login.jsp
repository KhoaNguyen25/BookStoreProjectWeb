<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Login</title>
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="./js/jquery-ui.min.css" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="center">
		<h3 class="title_heading">Customer Login</h3>
		<c:if test="${message != null}">
			<h4>${message}</h4>
		</c:if>
		<form action="customer_login" method="post" id="customer_login">
			<div class="form-group">
				<label class="form-label">E-mail: </label><input type="text"
					name="email" id="email" />
			</div>
			<div class="form-group" style="margin-top:10px; margin-bottom:10px;">
				<label class="form-label">Password: </label><input type="password"
					name="password" id="password" />
			</div>
			<input type="submit" value="Login" >
		</form>
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript" src="./js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="./js/jquery.validate.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#customer_login").validate({
			rules:{
				email:{
					  required: true,
					  email:true
				  },
				  password:"required",
			},
			message:{
				email:{
					required: "Please enter E-mail",
					email: "Please enter the correct email."
				},
				password:"Please enter Password",
			}
		});
	});
	</script>
</body>
</html>