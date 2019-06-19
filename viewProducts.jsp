<%@page import="com.lti.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		List <Product> products = (List <Product>) session.getAttribute("currentProduct");
	     for(Product product : products)  {
	%>
			id: <%=product.getId() %> <br />
			Name: <%=product.getName() %> <br />
			Price : <%= product.getPrice() %> <br/>
			Quantity:<%= product.getQuantity() %><br/>
			=============== <br />
<%
     }
%>
	<a href ="ProductControllerServlet?page=prev">&lt;&lt;</a>
	<a href ="ProductControllerServlet?page=next">&gt;&gt;</a>
</body>
</html>