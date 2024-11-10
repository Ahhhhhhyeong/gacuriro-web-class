<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In</title>
</head>
<body>
	<h1>Sign In</h1>
	<div class="signin-page">
	    <form action="user" method="post">
	        <!-- Specify form action to send data to /user (assumes the UserServlet is mapped to handle this URL) -->
	        <input type="hidden" name="action" value="get" />
	        <!-- Hidden input to indicate the action type (e.g., login) -->
	        Email: <input type="text" name="email" required /> <br/>
	        Password: <input type="password" name="password" required /> <br/>
	        <input type="submit" value="Login" />
	    </form>
	</div>
	<a href="./signup.jsp">Sign up</a>
</body>
</html>