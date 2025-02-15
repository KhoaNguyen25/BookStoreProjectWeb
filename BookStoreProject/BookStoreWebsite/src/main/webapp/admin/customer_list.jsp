<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>List Customer - Evergeen Book Administrative</title>
<link rel="stylesheet" href="../css/admin/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div align="center">
		<h2>Customers Management</h2>
		<h3>
			<a href="customer_form.jsp">Create New Customer</a>
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
				<th>E-mail</th>
				<th>Full Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Register Date</th>
				<th>Actions</th>
			</tr>

	
			<c:forEach var="customer" items="${listCustomers}" varStatus="status">
				<tr>
					<td align="center">${status.index + 1}</td>
					<td align="center" class="id_customer">${customer.customerId}</td>
					<td align="center">${customer.email}</td>
					<td align="center">${customer.fullname}</td>
					<td align="center">${customer.city}</td>
					<td align="center">${customer.country}</td>
					<td align="center"><fmt:formatDate pattern='MM/dd/yyyy' value="${customer.registerDate}" /></td>
					<td align="center"><a href="edit_customer?id=${customer.customerId}" >Edit</a> | 
					<a href="javascript:void(0);" class="listDelete" id="${customer.customerId }" >Delete</a></td>
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
					var customerId = $(this).attr("id");
					console.log(customerId);
					if (confirm("Are you sure you want to delete the Customer width ID "
							+ customerId + " ?")) {
							window.location = "delete_customer?id=" + customerId;
					}
				});
			});	
		});
	</script>
</body>
</html>
