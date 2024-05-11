<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div align="center">
	<div>
		<a href="${pageContext.request.contextPath}"><img alt="logo" src="images/BookstoreLogo.png"></a>

	</div>
	<div>
	<form action="search_book" method="get" style="display:inline-block;">
		<input type="text" name="keywork" size="50" /> 
		<input type="submit" name="search" value="search" id="search" />
	</form>
		<c:if test="${customerLogin == null}">
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="show_login">Sign In</a>&nbsp; | &nbsp; <a href="register_form">Register</a>&nbsp;|
		&nbsp; <a href="cart">Cart</a>
		</c:if>
		<c:if test="${customerLogin != nulll}">
		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<a href="">Welcome, ${customerLogin.fullname} </a>&nbsp; | 
			&nbsp; <a href="">My Order</a>&nbsp;| &nbsp; <a href="customer_logout">Sign Out</a>&nbsp;|
		&nbsp; <a href="">Cart</a>
		</c:if>
	</div>
	<div>
		<ul class="category_list">
			<c:forEach items="${listCategory}" var="category">
				<li><a href="view_category?id=${category.categoryId}"><c:out value="${category.name}" /></a></li>
			</c:forEach>
		</ul>
	</div>

</div>