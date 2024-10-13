package com.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

/**
 * Servlet implementation class MongoDBConnect
 */
@WebServlet("/dbconn")
public class MongoDBConnect extends HttpServlet {       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MongoDBConnect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String note = dbConnection();
		response.setContentType("text/html");
		response.getWriter().println("<h1>"+note+"</h1>");
	}

	//MongoClientConnectExample
	//This defines a method named dbConnection that returns a String. 
	//This method will handle the database connection.
	public String dbConnection() {
		//Here, a connection string is defined. 
		//You must replace <user_name> and <db_password> with your MongoDB credentials. 
		//This string contains information on how to connect to your MongoDB instance.
		String connectionString = "mongodb+srv://<user_name>:<db_password>@mongodb.lfnur.mongodb.net/?retryWrites=true&w=majority&appName=MongoDB";
		
		//This part sets the server API version to V1. 
		//The API version ensures compatibility with the MongoDB server.
		ServerApi serverApi = ServerApi.builder()
	               .version(ServerApiVersion.V1)
	               .build();
		/*
		 * Here, the MongoClientSettings are configured. 
		 * The connection string and server API version are applied to the settings.*/
		MongoClientSettings settings = MongoClientSettings.builder()
	               .applyConnectionString(new ConnectionString(connectionString))
	               .serverApi(serverApi)
	               .build();
	        // Create a new client and connect to the server
		/*
		 * This line creates a new MongoClient using the settings defined earlier. 
		 * The try-with-resources statement ensures that the client is closed automatically after use.*/
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                //The code retrieves the "admin" database. 
            	//This is where you can perform operations on your MongoDB instance
                MongoDatabase database = mongoClient.getDatabase("admin");
                System.out.println("You successfully connected to MongoDB!");
                
                //If there is an error while connecting to MongoDB, 
                //the exception is caught, an error message is printed, 
                //and a failure message is returned.
            } catch (MongoException e) {
                System.err.println("MongoDB error: " + e.getMessage());
                return "You failed connected to MongoDB!";
            }
        }
        return "You successfully connected to MongoDB!";
	}

}
