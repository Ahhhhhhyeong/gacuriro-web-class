package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class HelloServlet
 */
/*
 * The @WebServlet(“/~~~”) annotation 
 * is an annotation provided by the Java Servlet API 
 * that is used to map a servlet class to a specific URL.
 * */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    /**
     * Default constructor. 
     */
    public HelloServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
     * This method is designed to handle GET requests. 
     * It takes two parameters: 
     * HttpServletRequest, which contains information about the client's request, 
     * and HttpServletResponse, 
     * which is used to construct the response that will be sent back to the client. 
     * The method can throw ServletException and IOException.
     * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * This line sets the content type of the response to HTML. 
		 * This informs the client that the response will be in HTML format.
		 * */
		response.setContentType("text/html");
		/*
		 * Here, the output stream for the response is obtained using response.getWriter(), 
		 * and a message is written to it. The message <h1>Hello, 
		 * GET request!</h1> is sent back to the client, 
		 * which will be displayed as a heading in the browser.
		 * */
	    response.getWriter().println("<h1>Hello, GET request!</h1>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	 * This method handles HTTP POST requests.
	 * HttpServletRequest contains information about the client's request, 
	 * while HttpServletResponse is used to create the response sent back to the client.
	*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {	
		/*
		 * It retrieves the name parameter sent in the POST request. 
		 * This value could be the name entered by the user.
		 * */
	    String name = request.getParameter("name");
	    /*
	     * This sets the content type of the response to HTML, 
	     * indicating that the client expects the response in HTML format.
	     * */
	    response.setContentType("text/html");
	    /*
	     * It gets the output stream for the response using response.getWriter(), 
	     * and writes a personalized message in HTML format. 
	     * For example, if the user inputs "IShami," 
	     * the response would be <h1>Hello, IShami!</h1> sent back to the client.
	     * */
	    response.getWriter().println("<h1>Hello, " + name + "!</h1>");
	}


}
