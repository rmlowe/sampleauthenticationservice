<%@page import="java.security.MessageDigest"%>
<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	byte[] salt = Base64.decodeBase64(request.getParameter("salt"));
	String key = application.getInitParameter("key");
	String userId = "joestudent";
	
	MessageDigest md = MessageDigest.getInstance("MD5");
	md.update(userId.getBytes("UTF-8"));
	md.update(key.getBytes("UTF-8"));
	md.update(salt);
	byte[] digest = md.digest();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Authentication service</title>
</head>
<body>

</body>
</html>