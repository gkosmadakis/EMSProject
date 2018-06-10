<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="com.ems.model.RegulationAnalysis" %>
<%@page import="com.session.SessionManagement" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<script type="text/javascript">
    function toggle_visibility(id) {
       var e = document.getElementById(id);
       if(e.style.display == 'block')
          e.style.display = 'none';
       else
          e.style.display = 'block';
    }

</script>
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

<h3> Employee's Assigned Regulation </h3>

<div class="right"> <a href="/EmployeeManagementSystemApp/employeeHomePage.jsp"> Home</a></div>

<form method=post action="/EmployeeManagementSystemApp/GetEmployeeDetailsController">
<table  style="background-color:#b3e6ff; border:1px solid black;width:90%;" 
id="myTable" class="table editabletable table-bordered table-condensed table-responsive">

<%
RegulationAnalysis regulation = (RegulationAnalysis) request.getAttribute("regulationassigned");%>

	
	<tr><td style="border: 1px solid #000000;padding:5px;">ID</td>
		<td style="border: 1px solid #000000;padding:5px;"><%=regulation.getRegAnalysisId()%></td>
	</tr>
	<tr><td style="border: 1px solid #000000;padding:5px;">Department Head Name</td>	
		<td style="border: 1px solid #000000;padding:5px;"><%=regulation.getDeptHeadName()%></td>
	</tr>
	
	<tr><td style="border: 1px solid #000000;padding:5px;">Creation Date</td>
		<td style="border: 1px solid #000000;padding:5px;"><%=regulation.getRegulationDate().toString().substring(0,regulation.getRegulationDate().toString().indexOf(" "))%></td>
	</tr>
	<tr><td style="border: 1px solid #000000;padding:5px;">Regulation Status</td>
		<td style="border: 1px solid #000000;padding:5px;"><%=regulation.getRegulationStatus()%></td>
	</tr>
	<tr><td style="border: 1px solid #000000;padding:5px;">Regulation Text</td>
		<td style="border: 1px solid #000000;padding:5px;"><%=regulation.getRegulationDetails()%></td>
	</tr>
	<tr>	
		<td style="border: 1px solid #000000;padding:5px;"><a href="#" onclick="toggle_visibility('foo');">Add a comment</a>
		</td>
		<td style="border: 1px solid #000000;padding:5px;">
		<input type="submit" value="Accept" name="accept" style="background-color:#40ff00;"> 
		<input type="submit" value="Decline" name="decline" style="background-color:#ff0000;"></td>
	</tr> 
	
</table>

</form>

<div id="foo" style="display:none;">
<form method=post action="/EmployeeManagementSystemApp/GetEmployeeDetailsController">
<textarea rows="4" cols="45" name="comment"> </textarea>
<% request.setAttribute("regulation", regulation); %>
<input type="hidden"  name="regulationassigned" value="<%=regulation %>">
<input type="submit" value="Save Comment">
</form>
</div> <br>

<div id="commentArea" style="display:none;">
<h3> Comments:</h3>
<% if(regulation.getComments()!=null){%>
<script> toggle_visibility('commentArea'); </script> 
 <p> You commented: <%=regulation.getComments() %>
</p>
<%} %>
 </div>

</body>
</html>