<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.util.List" %>
<%@page import="com.ems.controller.EditHeadOfDeptController" %>
<%@page import="com.session.SessionManagement" %>
<link rel='stylesheet' type='text/css' href='css/styles.css'>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
    function getTaskId(){
    	var oTable = document.getElementById('myTable');

        //gets rows of table
        var rowLength = oTable.rows.length;

        //loops through rows    
        for (i = 0; i < rowLength; i++){

          //gets cells of current row  
           var oCells = oTable.rows.item(i).cells;

           //gets amount of cells of current row
           var cellLength = oCells.length;

           //loops through each cell in current row
           for(var j = 0; j < cellLength; j++){

                  // get your cell info here
                  var cellVal = oCells.item(j).innerHTML;
                  if (j ==0) {
                	  var departmentname = oCells.item(j).textContent;
                	  //alert(departmentname);
                	  document.getElementById("deptID").value = departmentname;
                  }
                  if (j ==1) {
                	  var headOfDepartment = oCells.item(j).textContent;
                	  
                	  document.getElementById("headofdeptID").value = headOfDepartment;
                	  
                  }
                  
               }
        }
    }
</script>
</head>
<body>

<!-- Check if user is inactive to terminate the session-->	
<% SessionManagement.checkUserActivity(request, response); %>

<jsp:include page="/welcomeHeader.jsp"></jsp:include>
<h3> Edit Department Head Information </h3>

<%
List<String> list = EditHeadOfDeptController.getAllHeadOfDeptNamesFromDB();
int size = list.size();
%> 

<div class="right"> <a href="/EmployeeManagementSystemApp/adminHomePage.jsp"> Home</a></div>

<p> Select a Head of Department then update it from the table</p>
<form method=post action="/EmployeeManagementSystemApp/EditHeadOfDeptController"> <fieldset>
<select name="headofdepartment">
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
</select><br><br>
<input type="submit" value="View"> </fieldset>
</form> <br>

<% if(request.getAttribute("departmentname")!=null && request.getAttribute("headofdepartment")!=null) { %>
	
<form method=post action="/EmployeeManagementSystemApp/EditHeadOfDeptController">
<table style="border:2px solid black;width:50%;"
id="myTable" class="table editabletable table-bordered table-condensed table-responsive">

<tr>
  <td><div contenteditable> <%=request.getAttribute("departmentname") %> 
  </div><input type="hidden" id="deptID"name="updateddept" value="<%=request.getAttribute("departmentname") %>"/>
  <input type="hidden" id="deptIDold"name="oldddept" value="<%=request.getAttribute("departmentname") %>"/></td>
  
  <td><div contenteditable> <%=request.getAttribute("headofdepartment") %> </div>
  <input type="hidden" id="headofdeptID"name="updatedheadofdept" value="<%=request.getAttribute("headofdepartment") %>"/>
  <input type="hidden" id="headofdeptIDOld"name="oldheadofdept" value="<%=request.getAttribute("headofdepartment") %>"/></td>
</tr>

</table> <br>

 <input type="submit" name="savetoedit" value="Save" onclick="getTaskId()"/>

</form>
<% }%>


</body>
</html>