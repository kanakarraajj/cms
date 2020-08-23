<%@ page import="source.FormData" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>user-update-form</title>
  <link rel="stylesheet" href="jquery-ui.css"/>
  <link rel="stylesheet" href="style.css"/>
  <script src="jquery1.12.4.js"></script>
  <script src="jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>
</head>
<body>

<%
String id = request.getParameter("id").toString();
String name = request.getParameter("name").toString();
String password = request.getParameter("password").toString();
String married= request.getParameter("married").toString();
String qualn = request.getParameter("qualn").toString();
String dob= request.getParameter("dob").toString();

FormData ufd = new FormData(id, name,password, dob, married, qualn);

%>

<form action="/cms/FormServlet" method="post"> 
<input type="hidden" name="operation" value="update" > 
<input type="hidden" name="id" value="<%=ufd.getId()%>" >
 
	<table align="center" border="1" width="80%">
		<tr align="center" height="50px">
		<td colspan="2">
			<H3> Update CMS Form Details</H3> 
			
			<%
			if ( request.getAttribute("status") != null) { %>
			 <font color="green"> <b><%=request.getAttribute("status").toString()%> </b></font>
			<% } %>

		</td>
		</tr>
		<tr height="100px">
		<td>Name: <input type="text" name="name" value="<%=ufd.getName() %>" size="55"><br>
			Password: <input type="password" name="password" value="<%=ufd.getPassword()%>" size="50"></td>
		<td>DOB: <input type="text" name="dob" id="datepicker" value="<%=ufd.getDob() %>"></td>
		</tr>
		<tr height="100px">
		<td>Married:  
			
			<input type="radio" name="married" value="Yes" <%=(ufd.getMarried().equals("Yes") ? "checked" : "")%>> Yes 
  			<input type="radio" name="married" value="No"  <%=(ufd.getMarried().equals("No") ? "checked" : "")%>> No<br>
		</td>
		
		<td>Qualification: 
		  <select name="qualification">
			  <option value="BTech" <%=(ufd.getQualification().equals("BTech") ? "selected" : "")%>>BTech</option>
			  <option value="BE" <%=(ufd.getQualification().equals("BE") ? "selected" : "")%>>BE</option>
			  <option value="MBA" <%=(ufd.getQualification().equals("MBA") ? "selected" : "")%>>MBA</option>
			  <option value="BSc" <%=(ufd.getQualification().equals("BSc") ? "selected" : "")%>>BSc</option>
			</select> 
		</td>
		</tr>
		
		<tr align="center" height="50px">
		<td>
			<input type="submit" value="Submit Form Details" >
			<input type="reset" value="Clear" > 
		</td>
		<td> <a href="/cms/list_search.jsp">List Form Data</a></td>
		</tr>
	</table>
</form>

</body>
</html>