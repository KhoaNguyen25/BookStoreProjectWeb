<%@ include file="/common/taglib.jsp" %>
<div align="center" class="header">
	<div class="header_logo">
		<a href="${pageContext.request.contextPath}/admin/"><img alt="logo" src="../images/BookstoreAdminLogo.png" /></a>
	</div>
	<div class="header_nav">
		<div class="header_logout">
			<h4>Welcome, <c:out value="${useremail}" /> <a href="logout">Logout</a>&nbsp; </h4>
		</div>
		<div class="header_menu">
			<a href="list_users"><img alt="logo_users" src="../images/users.png" />Users</a> |
			<a href="list_categories"><img alt="logo_users" src="../images/category.png" />Categories</a> |
			<a href="list_books"><img alt="logo_users" src="../images/bookstack.png" />Books</a> |
			<a href="list_customers"><img alt="logo_users" src="../images/customer.png" />Customers</a> |
			<a href="reviews"><img alt="logo_users" src="../images/review.png" />Reviews</a> |
			<a href="orders"><img alt="logo_users" src="../images/order.png" />Orders</a> 
		</div>
	
	</div>

</div>