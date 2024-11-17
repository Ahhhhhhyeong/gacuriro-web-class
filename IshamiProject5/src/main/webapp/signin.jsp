<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Set page language to Java and content type with UTF-8 encoding -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Set character encoding for the document -->
<link rel="stylesheet" type="text/css" href="./css/signin.css">
<!-- Link to external CSS file for styling -->
<title>Sign In</title>
<!-- Page title -->
</head>
<body>
	<h1>Sign In</h1>
	<!-- Page header -->
	<div class="signin-page">
		<!-- Container for the sign-in form -->
		<form action="user" method="post">
			<!-- Form to send data to the UserServlet (mapped to /user) via POST method -->

			<input type="hidden" name="action" value="get" />
			<!-- Hidden input to specify the action as 'get' (e.g., for login authentication) -->

			<!-- Input field for email, required for submission -->
			Email: <input type="text" name="email" required /> <br />

			<!-- Input field for password, required for submission -->
			Password: <input type="password" name="password" required /> <br />

			<!-- Submit button for the form -->
			<input type="submit" value="Login" />
		</form>
	</div>
	<a href="./signup.jsp">Sign up</a>
	<!-- Link to the sign-up page -->
</body>
</html>
