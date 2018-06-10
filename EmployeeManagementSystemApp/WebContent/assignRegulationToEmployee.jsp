<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.util.List" %>
<%@page import="com.session.SessionManagement" %>
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>

<h3> Assign Regulation to Employee</h3>

<% List<String> fullnamesList = (List<String>) request.getAttribute("fullnameslist");%>

<div class="right"> <a href="/EmployeeManagementSystemApp/headDeptHomePage.jsp"> Home</a></div>

<p> Select an Employee to Assign the Regulation</p>
<form method=post action="/EmployeeManagementSystemApp/AssignRegulationController"> <fieldset>
<select name="employee">
	<option value="" label="Select"/>
<%
	for (int i = 0; i < fullnamesList.size(); i++) {
  		String s = (String)fullnamesList.get(i);
%>
	<option value="<%=s%>" ><%=s%>
	</option>
<%
}
%>
</select>
 <input type="hidden" name="regulationIdToBeAssigned" value="<%=request.getAttribute("regulationIdToBeAssigned")%>">
<input type="submit" value="Assign"> </fieldset>

</form>

</body>
</html>