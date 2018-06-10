<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.session.SessionManagement" %>
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>

<h2> My Details </h2>

<div class="right"> <a href="/EmployeeManagementSystemApp/employeeHomePage.jsp"> Home</a></div>

<form method=get action="/EmployeeManagementSystemApp/GetEmployeeDetailsController">
<table style="background-color:#b3e6ff; border:1px solid black; width:50%" 
id="myTable" class="table editabletable table-bordered table-condensed table-responsive">

<tr>
  <td style="border: 1px solid #000000;padding:5px;">Username:</td> <td style="border: 1px solid #000000;padding:5px;"> <%=request.getAttribute("username") %> 
  </td>
</tr>

<tr>
  <td style="border: 1px solid #000000;padding:5px;">Department:</td> <td style="border: 1px solid #000000;padding:5px;"> <%=request.getAttribute("userdepartment") %> 
  </td>
</tr>

<tr>
  <td style="border: 1px solid #000000;padding:5px;">Email:</td> <td style="border: 1px solid #000000;padding:5px;"> <%=request.getAttribute("email") %> 
  </td>
</tr>

<tr>
  <td style="border: 1px solid #000000;padding:5px;">Firstname:</td> <td style="border: 1px solid #000000;padding:5px;"> <%=request.getAttribute("firstname") %> 
  </td>
</tr>

<tr>
  <td style="border: 1px solid #000000;padding:5px;">Lastname:</td> <td style="border: 1px solid #000000;padding:5px;"> <%=request.getAttribute("lastname") %> 
  </td>
</tr>

<tr>
  <td style="border: 1px solid #000000;padding:5px;">Role:</td> <td style="border: 1px solid #000000;padding:5px;"> <%=request.getAttribute("userrole") %> 
  </td>
</tr>

</table>
</body>
</html>