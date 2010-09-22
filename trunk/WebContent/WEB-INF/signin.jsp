<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign in</title>
</head>
<body>
<h1>Sign in</h1>
<p>You can sign in using one of the accounts below.</p>
<table border="1">
	<tr>
		<th>User ID</th>
		<th>Password</th>
	</tr>
	<tr>
		<td>joestudent</td>
		<td>topsecret</td>
	</tr>
	<tr>
		<td>marylearner</td>
		<td>try2guess</td>
	</tr>
</table>
<form action="loginhandler" method="POST"><input name="salt"
	type="hidden" value="<%=request.getAttribute("salt")%>">
<p><label>User ID: <input name="userid" type="text"></label></p>
<p><label>Password: <input name="password" type="password"></label></p>
<%
	if (request.getAttribute("error") != null
			&& ((Boolean) request.getAttribute("error")).booleanValue()) {
%>
<p style="color:red;">The user ID or password you entered is incorrect.</p>
<%
	}
%>
<p><input type="submit" value="Sign in"></p>
</form>
</body>
</html>