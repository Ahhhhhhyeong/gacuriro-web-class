<%@ page import="com.study.model.User"%>
<!-- Import the User model -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Set page language, content type, and encoding -->
<%@ page import="java.util.*"%>
<!-- Import Java utilities -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!-- Import JSTL core tag library -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Set character encoding for the document -->
<title>User List</title>
<!-- Page title -->
<link rel="stylesheet" type="text/css" href="./css/user.css">
<!-- Link to CSS file -->
<script type="text/javascript" src="./js/user.js"></script>
<!-- Link to JavaScript file -->
</head>
<body>
	<h2>Welcome, Admin: `${user.name}`</h2>
	<!-- Display admin's name -->
	<form action="user" method="post">
		<!-- Form for logging out -->
		<input type="hidden" name="action" value="logout" />
		<!-- Hidden input to specify logout action -->
		<button type="submit">Logout</button>
		<!-- Logout button -->
	</form>
	<h1>User List</h1>
	<!-- Header for user list -->

	<!-- Use JSTL to check if the user list is not empty -->
	<c:choose>
		<c:when test="${not empty userList}">
			<table>
				<!-- Display the user list in a table -->
				<thead>
					<tr>
						<th>ID</th>
						<!-- Table header for user ID -->
						<th>Name</th>
						<!-- Table header for user name -->
						<th>Email</th>
						<!-- Table header for user email -->
						<th>Level</th>
						<!-- Table header for user level -->
						<th>Update</th>
						<!-- Table header for update button -->
						<th>Delete</th>
						<!-- Table header for delete button -->
					</tr>
				</thead>
				<tbody>
					<!-- Iterate through the user list -->
					<c:forEach var="user" items="${userList}">
						<tr>
							<td>${user.id}</td>
							<!-- Display user ID -->
							<td>${user.name}</td>
							<!-- Display user name -->
							<td>${user.email}</td>
							<!-- Display user email -->
							<td>${user.level}</td>
							<!-- Display user level -->
							<td>
								<button class="btn updateBtn"
									onclick="openPopup('${user.email}', '${user.name}', '${user.level}', '${user.id}')">Update</button>
								<!-- Button to open modal for updating user -->
							</td>
							<td>
								<button class="btn deleteBtn" onclick="deleteUser('${user.id}')">Delete</button>
								<!-- Button to delete user -->
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>No users found.</p>
			<!-- Message if the user list is empty -->
		</c:otherwise>
	</c:choose>

	<!-- Modal HTML for update user -->
	<div id="popupModal" class="modal">
		<div class="modal-content">
			<span class="modal-close" onclick="closePopup()">&times;</span>
			<!-- Close button for modal -->
			<div id="popupContent"></div>
			<!-- Content area for dynamic modal data -->
		</div>
	</div>
</body>
</html>
