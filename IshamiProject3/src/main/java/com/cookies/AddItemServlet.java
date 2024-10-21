package com.cookies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/addItem")
public class AddItemServlet extends HttpServlet {

	/**
	 * Handles the HTTP GET request.
	 * Retrieves the item name and quantity from the request, saves them as a cookie,
	 * and redirects the user to the cart page.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    // Get the item name from the request parameters (from a form or query string).
	    String itemname = request.getParameter("item");
	    
	    // Get the quantity of the item from the request parameters.
	    String quntity = request.getParameter("num");
	    
	    // Create a new cookie with the item name as the cookie name and the quantity as the value.
	    Cookie item = new Cookie(itemname, quntity);
	    
	    // Set the cookie expiration time to 2400 seconds (40 minutes).
	    item.setMaxAge(2400);
	    
	    // Add the cookie to the HTTP response, so it is stored in the user's browser.
	    response.addCookie(item);
	    
	    // Redirect the user to the "cart.html" page to view the shopping cart.
	    response.sendRedirect("cart.html");
	}


}
