package dbconn;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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
@WebServlet("/dbconn") // Maps the servlet to the /dbconn URL
public class MongoDBConnect extends HttpServlet {
    /**
     * Default constructor
     */
    public MongoDBConnect() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Handles GET requests
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html"); // Sets the content type of the response
        String connect = dbConnection(); // Calls dbConnection to establish a connection
        response.getWriter().println("<h1>" + connect + "</h1>"); // Returns the connection status to the client
    }
    
    /**
     * Reads properties from a properties file
     *
     * @param key The key for the property to retrieve
     * @return The value of the property, or an empty string if not found
     */
    public String readProperties(String key) {
        Properties properties = new Properties();
        // Loads properties from a file
        try (InputStream input = getServletContext().getResourceAsStream(getServletContext().getInitParameter("dbConfig"))) {
        	if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return "";
            }
            properties.load(input);
        } catch (IOException ex) {
        	System.out.println("IOException");
            System.err.println(ex.getMessage()); // Prints error message for IO exceptions
            return "";
        }
        return properties.getProperty(key); // Returns the property value for the given key
    }
    
    /**
     * Establishes a connection to the MongoDB database
     *
     * @return A message indicating whether the connection was successful or not
     */
    private String dbConnection() {   
        String dbUsername = readProperties("db.username"); // Reads the database username
        String dbPassword = readProperties("db.password"); // Reads the database password
        
        // Constructs the connection string for MongoDB
        String connectionString = "mongodb+srv://" + dbUsername + ":" + dbPassword + "@webprojectclass.lsyet.mongodb.net/?retryWrites=true&w=majority&appName=WebProjectClass";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1) // Sets the server API version
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString)) // Applies the connection string
                .serverApi(serverApi) // Applies the server API settings
                .build();
        
        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin"); // Accesses the "admin" database
                System.out.println("You successfully connected to MongoDB!"); // Prints connection success message
            } catch (MongoException e) {
                return "Does not Connect"; // Returns error message if connection fails
            }
        }
        return "You successfully connected to MongoDB!"; // Returns success message
    }
}
