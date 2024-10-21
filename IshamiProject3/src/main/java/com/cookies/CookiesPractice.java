package com.cookies;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CookiesPractice
 */
@WebServlet("/cookies")
public class CookiesPractice extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String name = request.getParameter("name");
		/*
		 *  new Cookie object is created with the name "username" 
		 *  and the value being the name retrieved from the request (e.g., "Ahyeong").
		 *  This cookie will store the username in the client’s browser.
		 */
		Cookie username = new Cookie("username", name); 
		/*
		 * This sets the lifespan of the username cookie to 400 seconds. After 400
		 * seconds, the cookie will expire, meaning it will no longer be sent by the
		 * client (browser) in subsequent requests.
		 */
		username.setMaxAge(400);
		/*
		 * The addCookie() method adds the username cookie to the HTTP response, which
		 * sends it to the client's browser. The browser will store this cookie and send
		 * it back to the server in future requests to the same domain, as long as the
		 * cookie is still valid (i.e., it hasn’t expired).
		 */
		response.addCookie(username);
	}

}
