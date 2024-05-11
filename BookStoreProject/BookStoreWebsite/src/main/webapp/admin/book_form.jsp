<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Book - Evergeen Book Administrative </title>
<link rel="stylesheet" href="../js/jquery-ui.min.css" />
<link rel="stylesheet" href="../css/admin/style.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="../css/richtext.min.css" />


<style type="text/css">
.container{
	display: flex;
	justify-content: center;
}
.form-group{
	display: flex;
    justify-content: space-evenly;
    flex-direction: column;
    margin: 0 0 10px 0;
}
select{
    width: 100%;
    height: 40px;
     margin: 0 0 10px 0;
}
.title_heading{
	text-align: center;
	font-size: 30px;
}
</style>
</head>
<body>
<jsp:include page="header.jsp" />
		<c:if test="${book == null}">
			<h3 class="title_heading">Create Book</h3>
		</c:if>
		<c:if test="${book != null}">
			<h3 class="title_heading">Edit Book</h3>
		</c:if>
	<div class="container">
		<c:if test="${book == null}">
		<form action="create_book" method="post" id="book_id" enctype="multipart/form-data" >
		</c:if>

		<c:if test="${book != null}">
		<form action="updated_book" method="post" id="book_id"  enctype="multipart/form-data" >
		<input type="hidden" name="bookId" value="${book.bookId}" />
		</c:if>
			<div class="form-group">
				<label class="form-label" style="margin-bottom:10px;">Category:</label>

					<select name="category">
						<option value="">Please choose !</option>
						<c:forEach items="${listCategory}" var="listCategory">
							<option value="${listCategory.categoryId}" ${book.category.categoryId == listCategory.categoryId ? 'selected="selected"':"" }>${listCategory.name}</option>
						</c:forEach>
							
					</select>
			</div>
			<div class="form-group"><label class="form-label">Title: </label><input type="text" name="title" id="title" value="${book.title}" /> </div>
			<div class="form-group"><label class="form-label">Author: </label><input type="text" name="author" id="author" value="${book.author}" /> </div>
			<div class="form-group"><label class="form-label">ISBN: </label><input type="number" name="isbn" id="isbn" value="${book.isbn}" /> </div>
			<div class="form-group"><label class="form-label">Publich Date: </label><input type="text" name="publishDate" id="publishDate" value='<fmt:formatDate pattern='MM/dd/yyyy' value="${book.publishDate}"/>' /></div>
			<div class="form-group"><label class="form-label">Book image: </label><input type="file" name="image" id="image" />
				<img src="data:image/jpg;base64,${book.base64Image}" alt="thumbnail" id="thumnail-image" style="width: 10%;"> 
			</div>
			<div class="form-group"><label class="form-label">Price: </label><input type="number" name="price" id="price" value="${book.price}" /></div>
			<div class="form-group"><label class="form-label">Description: </label><textarea rows="10" cols="80" name="description" id="description">${book.description}</textarea></div>
			
			<input type="submit" value="Submit"> 
			<input type="button" value="cancle" onclick="back();">
					
		</form>
	</div>
<jsp:include page="footer.jsp" />
<script type="text/javascript" src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#description").richText();
		$( "#publishDate" ).datepicker();
		$("#book_id").validate({
			rules:{
				category: "required",
				title: "required",
				author: "required",
				isbn: "required",
				publishDate: "required",
				<c:if test="${book == null}">
				image: "required",
				</c:if>
				price: "required",
				description: "required"

			},
			messages:{
				category: "Please enter category",
				title: "Please enter title",
				author:"Please enter author",
				isbn: "Please enter isbn",
				publishDate:"Please enter publishDate",
				image: "Please enter image",
				price: "Please enter price",
				description: "Please enter description",
			}
		});
		$("#image").change(function() {
			showImageThumbnail(this);
			
		});

	});
	function showImageThumbnail(fileInput){
			var file = fileInput.files[0]; 

			var reader = new FileReader();

			reader.onload = function(e){
				$("#thumnail-image").attr("src", e.target.result);
			}
			reader.readAsDataURL(file);

		}
		function back(){
			window.history.go(-1);
			return false;
		}
</script>
	
</body>
</html>