<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Customer - Evergeen Book Administrative</title>
<link rel="stylesheet" href="../js/jquery-ui.min.css" />
<link rel="stylesheet" href="../css/admin/style.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="../css/richtext.min.css" />


<style type="text/css">
.container {
	display: flex;
	justify-content: center;
}

.form-group {
	display: flex;
	justify-content: space-evenly;
	flex-direction: column;
	margin: 0 0 10px 0;
}

select {
	width: 100%;
	height: 40px;
	margin: 0 0 10px 0;
}

.title_heading {
	text-align: center;
	font-size: 30px;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<c:if test="${customer == null}">
		<h3 class="title_heading">Create Customer</h3>
	</c:if>
	<c:if test="${customer != null}">
		<h3 class="title_heading">Edit Customer</h3>
	</c:if>
	<div class="container">
		<c:if test="${customer == null}">
			<form action="create_customer" method="post" id="customer_id">
		</c:if>

		<c:if test="${customer != null}">
			<form action="updated_customer" method="post" id="customer_id">
				<input type="hidden" name="customerId"
					value="${customer.customerId }" />
		</c:if>
		<div class="form-group">
			<label class="form-label">E-mail Address: </label><input type="text"
				name="email" id="email" value="${customer.email}" />
		</div>
		<div class="form-group">
			<label class="form-label">FullName: </label><input type="text"
				name="fullname" id="fullname" value="${customer.fullname}" />
		</div>
		<div class="form-group">
			<label class="form-label">Password: </label><input type="password"
				name="password" id="password" value="${customer.password}" />
		</div>
		<div class="form-group">
			<label class="form-label">Confirm Password: </label><input
				type="password" name="confirmPassword" id="confirmPassword" value="${customer.password}" />
		</div>
		<div class="form-group">
			<label class="form-label">Phone Number: </label><input type="text"
				name="phone" id="phone" value="${customer.phone }" />
		</div>
		<div class="form-group">
			<label class="form-label">Address: </label><input type="text"
				name="address" id="address" value="${customer.address}" />
		</div>
		<div class="form-group">
			<label class="form-label">City: </label><input type="text"
				name="city" id="city" value="${customer.city}" />
		</div>
		<div class="form-group">
			<label class="form-label">Zip Code: </label><input type="text"
				name="zipcode" id="zipcode" value="${customer.zipcode}" />
		</div>
		<div class="form-group">
			<label class="form-label">Country: </label><input type="text"
				name="country" id="country" value="${customer.country}" />
		</div>
		<input type="submit" value="Submit"> <input type="button"
			value="cancle" onclick="back();">

		</form>
	</div>
	<jsp:include page="footer.jsp" />
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="../js/jquery.richtext.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){

		//check password and confirm password
		$("#customer_id").validate({
			rules:{
			  email:{
				  required: true,
				  email:true
			  },
			  fullname:"required",
			  address:"required",
			  city:"required",
			  country:"required",
			  phone:"required",
			  zipcode:"required",
			  password:"required",
			  confirmPassword:{
				  required:true,
				  equalTo:"#password"
			  },

			},
			messages:{
				email:{
					required: "Please enter E-mail",
					email: "Please enter the correct email."
				},
				fullname:"Please enter Fullname",
				address:"Please enter Address",
				city:"Please enter City",
				country:"Please enter Country",
				phone:"Please enter Phone",
				zipcode:"Please enter ZipCode",
				password:"Please enter Password",
				confirmPassword: {
					required: "Please enter password.",
					equalTo: "Password is not correct."
				}
			}
		});
		

	});
	
	function back(){
		window.history.go(-1);
		return false;
	}
</script>

</body>
</html>