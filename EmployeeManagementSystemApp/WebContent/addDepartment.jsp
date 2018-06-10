<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.session.SessionManagement" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<title>Add a Department</title>
</head>
<body>
	
<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>

<h3> Here you can add a new department</h3>
<div align="center">
   <% if(request.getAttribute("nonsuccess") != null) { %>
    <p style="color: red">
     <%= request.getAttribute("nonsuccess") %>
    </p>
   <% } %>
   <% if(request.getAttribute("success") != null) { %>
    <p style="color: green;">
     <%= request.getAttribute("success") %>
    </p>
   <% } %>
</div>

<div class="right"> <a href="/EmployeeManagementSystemApp/adminHomePage.jsp"> Home</a></div>

<form method=post action="/EmployeeManagementSystemApp/AddDepartmentController"> <fieldset> <p> Add a new Department </p>
	<div><label for="department">Department Name</label><input type="text" required name='department' placeholder="Enter name"></div>
	<br> 
	<div><label for="departmentHead">Department Head</label><input type="text" required name='departmentHead' placeholder="Enter Head name"></div>
	<br>

	<p class="submit"> <input type="submit" name="submit" value="Add"></p></fieldset>
</form>

	
</body>
</html>