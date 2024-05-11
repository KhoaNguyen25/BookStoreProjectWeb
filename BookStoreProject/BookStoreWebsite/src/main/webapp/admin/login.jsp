<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
<link rel="stylesheet" href="../css/admin/login.css">
</head>
<body>
	<div class="container">
		<h1>Book Store Adminstration</h1>
		<h2>Admin Login</h2>
		<c:if test="${message != null}">
			<h4 style="text-align:center;">
				<i>${message}</i>
			</h4>
		</c:if>
		<form action="login" method="post" class="user_login">
			<div class="email">
				<label>Email: </label>
				<input type="text" value="" name="email" id="email"/>
			</div>
			<div class="password">
				<label>Password: </label>
				<input type="password" value="" name="password" id="password"/>
			</div>
			<input type="submit" name="login" value="login"  />
		</form>
	
	</div>
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.user_login').validate({
				rules:{
					"email":{
						required: true,
						email:true
					},
					"password":{
						required:true,
						minlength:3
					}
				},
				messages:{
					"email":{
						required:"Please enter field Email",
						email:"Please enter the correct email format"
					},
					"password":{
						required:"Please enter field Password",
						minlength : "Please enter min 3 length"
					}
				}
			});
		});
		
	</script>
</body>
</html>