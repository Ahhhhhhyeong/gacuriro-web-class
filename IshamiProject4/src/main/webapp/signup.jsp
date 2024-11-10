<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up</title>
</head>
<body>
	<h1>Sign Up</h1>
	<div class="signup-page">
		<form action="user" method="post">
		<!-- Specify form action to send data to /user (assumes the UserServlet is mapped to handle this URL) -->
	        <input type="hidden" name="action" value="create" />
	        Email: <input type="text" name="email" /> <br/>
	        Password: <input type="password" name="password" /><br/>
	        Name: <input type="text" name="name" /> <br/> 
	        <input type="submit" value="Sign up"/>
		</form>
	</div>
</body>
</html>