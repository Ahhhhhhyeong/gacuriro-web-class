package com.study.servlet;

import java.io.IOException;
import java.util.List;

import com.study.model.User;
import com.study.service.UserService;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// This servlet handles all user-related requests (e.g., login, logout, CRUD operations)
@WebServlet("/user") // URL mapping for this servlet (accessed via "/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService; // Service class for database operations

	// Initialize the servlet and its dependencies
	@Override
	public void init(ServletConfig config) throws ServletException {
		userService = new UserService(); // Create an instance of UserService
	}

	// Handles GET requests (used to retrieve data, e.g., list of users)
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Fetch all users from the database
		List<User> users = userService.getUserList();

		// Set the response format as JSON and encoding to UTF-8
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// Check if the user list is not empty
		if (users != null && !users.isEmpty()) {
			// Add the user list to the request so it can be used in the JSP
			request.setAttribute("userList", users);
			// Forward the request and response to the "user.jsp" page
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user.jsp");
			dispatcher.forward(request, response);
		} else {
			// If no users are found, set a message attribute
			request.setAttribute("message", "No users found.");
			// Forward to the same page with the message
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user.jsp");
			dispatcher.forward(request, response);
		}
	}

	// Handles POST requests (used to send data, e.g., create, update, delete, or
	// login actions)
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the "action" parameter to determine what the user wants to do
		String action = request.getParameter("action");

		// If no action is provided, respond with an error
		if (action == null || action.isEmpty()) {
			response.getWriter().write("Invalid action.");
			return;
		}

		// Determine which action to perform based on the value of "action"
		switch (action) {
		case "create": // Add a new user
			handleCreateUser(request, response);
			break;
		case "get": // Authenticate a user (login)
			handleGetUser(request, response);
			break;
		case "update": // Update user information
			handleUpdateUser(request, response);
			break;
		case "delete": // Delete a user
			handleDeleteUser(request, response);
			break;
		case "logout": // Log out the user
			handleLogout(request, response);
			break;
		default: // If the action is invalid
			response.getWriter().write("Invalid action.");
			break;
		}
	}

	// Method to handle user creation (Sign-up)
	private void handleCreateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get input values from the request
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String level = request.getParameter("level"); // User role/level (e.g., admin, regular)

		// Check if any required fields are missing
		if (email == null || password == null || name == null || email.isEmpty() || password.isEmpty()
				|| name.isEmpty()) {
			response.getWriter().write("Error: All fields are required.");
			return;
		}

		// Create a new User object with the provided data
		User user = new User(email, name, password, level);
		// Call the service method to save the user in the database
		boolean isCreated = userService.createUser(user);

		// Check if the user was created successfully
		if (isCreated) {
			response.sendRedirect("signin.jsp"); // Redirect to the sign-in page
		} else {
			response.getWriter().write("Error: Could not create user. User may already exist.");
		}
	}

	// Method to handle user login (Sign-in)
	private void handleGetUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get login credentials from the request
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		// Check if credentials are provided
		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			response.getWriter().write("Error: Email and password are required.");
			return;
		}

		// Verify the credentials by calling the service
		User user = userService.getUser(email, password);
		if (user != null) {
			// Create a session for the authenticated user
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // Save the user object in the session
			response.sendRedirect("Welcome.jsp"); // Redirect to the welcome page
		} else {
			response.getWriter().write("User not found or invalid credentials."); // Error message
		}
	}

	// Method to handle user updates (e.g., change name or role)
	private void handleUpdateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("id"); // Get the user ID
		String name = request.getParameter("name"); // Get the updated name
		String level = request.getParameter("level"); // Get the updated level/role

		// Check if the required fields are provided
		if (userId == null || userId.isEmpty() || name == null || name.isEmpty()) {
			response.getWriter().write("Error: All fields are required.");
			return;
		}

		// Call the service to update the user in the database
		boolean isUpdated = userService.updateUser(userId, name, level);
		if (isUpdated) {
			response.getWriter().write("User updated successfully."); // Success message
		} else {
			response.getWriter().write("Error: Could not update user."); // Error message
		}
	}

	// Method to handle user deletion
	private void handleDeleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String userId = request.getParameter("id"); // Get the user ID to delete

		// Check if the user ID is provided
		if (userId == null || userId.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("Error: User ID is required.");
			return;
		}

		// Call the service to delete the user
		boolean deleted = userService.deleteUser(userId);
		if (deleted) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.sendRedirect("user.jsp"); // Redirect to the user list page
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("Error: Could not delete user."); // Error message
		}
	}

	// Method to handle user logout
	private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(); // Get the current session
		session.invalidate(); // Invalidate the session (logout)
		response.sendRedirect("signin.jsp"); // Redirect to the sign-in page
	}
}
