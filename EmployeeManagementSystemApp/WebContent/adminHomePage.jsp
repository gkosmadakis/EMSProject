<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.session.SessionManagement" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<title>Insert title here</title>
</head>
<body>

<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>

<h4>
  <div align="center">
   <% if(request.getAttribute("nonSuccess") != null) { %>
    <p style="color: red">
     <%= request.getAttribute("nonSuccess") %>
    </p>
   <% } %>
   <% if(request.getAttribute("success") != null) { %>
    <p style="color: green;">
     <%= request.getAttribute("success") %>
    </p>
   <% } %>
</h4>

<div class="dropdown">
  <button class="dropbtn">Department</button>
  <div class="dropdown-content">
    <a href=/EmployeeManagementSystemApp/addDepartment.jsp> Add a Department</a>
    <a href=/EmployeeManagementSystemApp/deleteDepartment.jsp> Delete a Department</a>
   <a href=/EmployeeManagementSystemApp/editDeptHeadInfo.jsp> Edit the Department Head information</a>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">Employees</button>
  <div class="dropdown-content">
    <a href=/EmployeeManagementSystemApp/viewAllEmployees.jsp> View Employees List</a>
    <a href=/EmployeeManagementSystemApp/addEmployeeDetails.jsp> Add employee details</a>
  
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">Regulations</button>
  <div class="dropdown-content">
   <a href=/EmployeeManagementSystemApp/addRegulation.jsp> Add regulations</a>
   <a href=/EmployeeManagementSystemApp/deleteRegulation.jsp> Delete regulations</a>
  <a href=/EmployeeManagementSystemApp/viewRegulation.jsp> View regulations</a>
  </div>
</div>


</body>
</html>