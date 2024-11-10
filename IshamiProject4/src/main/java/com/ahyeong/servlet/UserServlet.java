package com.ahyeong.servlet;

import java.io.IOException;
import com.ahyeong.model.User;
import com.ahyeong.service.UserService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Servlet mapped to the /user URL pattern
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService; // Service instance to handle user-related operations

    // Servlet initialization method, called once when the servlet is first created
    @Override
    public void init(ServletConfig config) throws ServletException {
        userService = new UserService(); // Initialize UserService object
    }
    
    // Handles POST requests sent to the servlet
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action"); // Retrieve action parameter from the request

        if ("create".equals(action)) { // If action is "create", handle user creation logic
            String email = request.getParameter("email"); // Retrieve email parameter
            String password = request.getParameter("password"); // Retrieve password parameter
            String name = request.getParameter("name"); // Retrieve name parameter

            if (email == null || password == null || name == null || email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                response.getWriter().write("Error: All fields are required."); // Validate input fields
                return;
            }

            // Create a new User instance (consider hashing the password here)
            User user = new User(email, name, password);
            boolean isCreated = userService.createUser(user); // Call service to create the user
            
            if (isCreated) {
                response.getWriter().write("User created successfully."); // Respond with confirmation
                response.sendRedirect("signin.jsp");
            } else {
                response.getWriter().write("Error: Could not create user. User may already exist.");
            }
        } else if ("get".equals(action)) { // If action is "get", handle fetching user data
            String email = request.getParameter("email"); // Retrieve email parameter for lookup
            String password = request.getParameter("password"); // Retrieve password for authentication

            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                response.getWriter().write("Error: Email and password are required.");
                return;
            }

            User user = userService.getUser(email, password); // Call UserService to fetch user by email and password
            if (user != null) { // Check if user is found
            	response.getWriter().write("User: " + user.getUsername() + ", ObjectId: " + user.getId()); // Respond with user details
            } else {
                response.getWriter().write("User not found or invalid credentials."); // Respond if user is not found
            }
        } else {
            response.getWriter().write("Invalid action."); // Handle invalid action case
        }
    }
}
