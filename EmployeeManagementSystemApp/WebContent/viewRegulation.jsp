<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.util.List" %>
<%@page import="com.ems.model.RegulationDetail" %>
<%@page import="com.ems.controller.DeleteRegulationController" %>
<%@page import="com.session.SessionManagement" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<title>Insert title here</title>
</head>
<body>

<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>

<h3> View Regulations Page </h3>

<div class="right"> <a href="/EmployeeManagementSystemApp/adminHomePage.jsp"> Home</a></div>

<form method=get action="/EmployeeManagementSystemApp/DeleteRegulationController">
<table  style="background-color:#b3e6ff; border:1px solid black;width:90%;" 
id="myTable" class="table editabletable table-bordered table-condensed table-responsive">

<%
List<RegulationDetail> list = DeleteRegulationController.getAllRegulationsFromDB();

for (int i=0; i<list.size(); i++) { 
	%>
	
	<tr><td style="border: 1px solid #000000;padding:5px;">ID</td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getRegulationId()%></td>
	</tr>
	<tr><td style="border: 1px solid #000000;padding:5px;">Department</td>	
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getDeptName()%></td>
	</tr>
	<tr><td style="border: 1px solid #000000;padding:5px;">Department Head</td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getDeptHeadName()%></td>
	</tr>
	<tr><td style="border: 1px solid #000000;padding:5px;">Creation Date</td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getCreateDate().toString().substring(0,list.get(i).getCreateDate().toString().indexOf(" ")) %></td>
	</tr>
	<tr><td style="border: 1px solid #000000;padding:5px;">Regulation Text</td>
		<td style="border: 1px solid #000000;padding:5px;"><%=list.get(i).getRegulationDetail()%></td>
	</tr>
	<tr>	
		<td style="border: 1px solid #000000;padding:5px;"><a href="/EmployeeManagementSystemApp/DeleteRegulationController?id=<%=list.get(i).getRegulationId()%>">Delete </a>
		</td>
	</tr> 
	
<%
}
%>

</table>
</form>
</body>
</html>