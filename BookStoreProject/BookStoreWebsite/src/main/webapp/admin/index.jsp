<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Evergeen Book Administrative</title>
<link rel="stylesheet" href="../css/admin/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div align="center">
		<h2>Administrative Dashboard</h2>
		<hr width="60%" />
	</div>

	<div align="center">
		<h2>Quick Action:</h2>
		<a href="create_book">New Book</a> &nbsp; <a href="create_user">New
			User</a> &nbsp; <a href="create_categories">New Category</a> &nbsp; <a
			href="create_customers">New Customers</a> &nbsp;
		<hr width="60%" />
	</div>

	<div align="center">
		<h2>Recent Sales:</h2>

		<hr width="60%" />
	</div>

	<div align="center">
		<h2>Recent Review:</h2>
		
		<hr width="60%" />
	</div>

	<div align="center">
		<h2>Statistics:</h2>
		
	</div>

	<jsp:include page="footer.jsp" />
	<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
</body>
</html>