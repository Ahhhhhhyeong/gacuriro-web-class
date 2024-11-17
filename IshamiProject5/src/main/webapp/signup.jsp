<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Set page language to Java and content type with UTF-8 encoding -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Set character encoding for the document -->
<link rel="stylesheet" type="text/css" href="./css/signup.css">
<!-- Link to external CSS file for styling -->
<title>Sign Up</title>
<!-- Page title -->
</head>
<body>
	<h1>Sign Up</h1>
	<!-- Page header -->
	<div class="signup-page">
		<!-- Container for the sign-up form -->
		<form action="user" method="post">
			<!-- Form to send data to the UserServlet (mapped to /user) via POST method -->

			<!-- Hidden input to specify the action as 'create' -->
			<input type="hidden" name="action" value="create" />

			<!-- Hidden input to set the default level for the new user -->
			<input type="hidden" name="level" value="2" />

			<!-- Input field for email -->
			Email: <input type="text" name="email" /> <br />

			<!-- Input field for password -->
			Password: <input type="password" name="password" /><br />

			<!-- Input field for name -->
			Name: <input type="text" name="name" /> <br />

			<!-- Submit button for the form -->
			<input type="submit" value="Sign up" />
		</form>
	</div>
</body>
</html>
