<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%
  response.addHeader("Pragma", "no-cache");
  response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
  response.addHeader("Cache-Control", "pre-check=0, post-check=0");
  response.setDateHeader("Expires", 0);

  if(session.getAttribute("sessionEmail")==null)
      response.sendRedirect("/SupplierMVC/login.jsp");
  %>      
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">	
	<title>List Supplier</title>

</head>
<body>
<div class="container">
  <nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">           
  		<a class="navbar-brand" href="ProductController?action=listAll">List All</a>
  		<a class="navbar-brand" href="SupplierController?action=listSupplier">Manage Supplier</a>
  		<a class="navbar-brand" class="active" href="ProductController?action=listProduct">Manage Product</a> 
  		<a class="navbar-brand" href="RegisterController">Register User</a>
  		<a class="navbar-brand" href="LogoutController">Logout</a>       		
    </div>
  </nav>
</div>
<div class="container"></div>
<div class="container">
<br><br>
<h3>List of p</h3>
<br><br>
<a href="ProductController?action=addProduct" class="btn btn-success" style="float:right">ADD PRODUCT</a><br><br>
<table class="table table-striped" style="width:100%">
  <tr>
    <th>Product Id</th>
    <th>Product Name</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Supplier Id</th>    
    <th colspan="3">Actions</th>
  </tr>
 <c:forEach items="${products}" var="product" varStatus="p">
  <tr>
    <td><c:out value="${product.pid}" /></td>
    <td><c:out value="${product.productname}" /></td>
    <td><c:out value="${product.price}" /></td>
    <td><c:out value="${product.quantity}" /></td>
    <td><c:out value="${product.sid}" /></td>        
    <td><a href="ProductController?action=viewProduct&pid=<c:out value="${product.pid}" />" class="btn btn-warning">View</a></td>
    <td><a href="ProductController?action=updateProduct&pid=<c:out value="${product.pid}" />" class="btn btn-primary">Update</a></td>
    <td><input type="hidden" id="pid-${p.index}" value="<c:out value="${product.pid}"/>"><button class="btn btn-danger" onclick="confirmation('${p.index}')">Delete</button></td>    
       
  </c:forEach>
</table>
</div>
	<script>
		function confirmation(index){
			  var pid = $("#pid-" + index).val();
			 
			  console.log(pid);
			  var r = confirm("Are you sure you want to delete?");
			  if (r == true) {				 		  
				  location.href = 'ProductController?action=deleteProduct&pid=' + pid;
				  alert("Product successfully deleted");			
			  } else {				  
			      return false;	
			  }
		}
	</script>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</body>
</html>
