<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.util.List" %>
<%@page import="com.ems.controller.DeleteDepartmentController" %>
<%@page import="com.session.SessionManagement" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<title>Insert title here</title>
</head>
<body> 

<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>

<h3> Delete a Department </h3>

<%
List<String> list = DeleteDepartmentController.getAllDepartmentsFromDB();
int size = list.size();
%>

<div class="right"> <a href="/EmployeeManagementSystemApp/adminHomePage.jsp"> Home</a></div>

<p> Select a Department to Delete</p>
<form method=post action="/EmployeeManagementSystemApp/DeleteDepartmentController"> <fieldset>
<select name="department">
	<option value="" label="Select"/>
<%
	for (int i=0;i<size;i++) {
  		String s = (String)list.get(i);
%>
	<option value="<%=s%>" ><%=s%>
	</option>
<%
}
%>

</select> <br> <br>
<input type="submit" value="Delete"> </fieldset>
</form>
	
</body>
</html>