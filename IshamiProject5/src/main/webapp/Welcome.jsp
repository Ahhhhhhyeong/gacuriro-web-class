<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Set page language to Java and content type with UTF-8 encoding -->
<%@ page import="com.study.model.User"%>
<!-- Import the User model class -->
<%@ page import="java.util.*"%>
<!-- Import Java utilities -->
<%@ page session="true"%>
<!-- Enable session tracking for the JSP -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- Import JSTL core tag library -->

<html>
<body>
	<%
	// Retrieve the user object from the session
	User user = (User) session.getAttribute("user");
	%>
	<!-- Check if the user object exists and if the user level is 1 (e.g., admin) -->
	<c:choose>		
		<c:when test="${user != null && user.level == 1}">
			<c:redirect url="user?action=loadUsers" />
			<!-- Redirect to loadUsers action if the user is an admin -->
		</c:when>
		<c:otherwise>
			<!-- For non-admin users, display a welcome message -->
			<h2>Welcome, ${user.name}! ${user.level }</h2>

			<!-- Logout form -->
			<form action="user" method="post">
				<input type="hidden" name="action" value="logout" />
				<!-- Hidden input to specify the logout action -->
				<button type="submit">Logout</button>
				<!-- Submit button to log out -->
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>
