<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.util.List" %>
<%@page import="com.ems.controller.ViewEmployeesListController" %>
<%@page import="com.ems.model.Employee" %>
<%@page import="com.session.SessionManagement" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<title>Insert title here</title>
<style> th{
text-align:left;
}

</style>
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

<h3> View Employees List</h3>

<div class="right"> <a href="/EmployeeManagementSystemApp/adminHomePage.jsp"> Home</a></div>

<form method=get action="/EmployeeManagementSystemApp/ViewEmployeesListController">
<table  style="background-color:#b3e6ff; border:1px solid black;width:90%;" 
id="myTable" class="table editabletable table-bordered table-condensed table-responsive">
<tr><th>ID</th><th>UserName</th><th>Department</th><th>Email</th><th>FirstName</th><th>LastName</th><th>Role</th><th>Date of Birth</th></tr>
<%
List<Employee> list = ViewEmployeesListController.getAllEmployeesFromDB();

for (int i=0; i<list.size(); i++) { 
	%>
	<tr>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getId()%></td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getUsername()%></td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getDepartment()%></td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getEmail()%></td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getFirstname()%></td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getLastname()%></td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getRole()%></td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getDateOfBirth()%></td>
		<input type="hidden" id="password" name="password" value="<%=list.get(i).getPassword() %>">
		<td style="border: 1px solid #000000;padding:5px;"><a href="/EmployeeManagementSystemApp/ViewEmployeesListController?id=<%=list.get(i).getId()%>">Edit </a></td>
		<td style="border: 1px solid #000000;padding:5px;"><a href="/EmployeeManagementSystemApp/DeleteEmployeeController?id=<%=list.get(i).getId()%>">Delete </a></td>
	</tr>
	
<%
}
%>
</table>
</form>


</body>
</html>