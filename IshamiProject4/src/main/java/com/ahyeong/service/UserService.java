package com.ahyeong.service;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.ahyeong.dbconnect.MongoConfig;
import com.ahyeong.model.User;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

// Service class responsible for managing user-related database operations
public class UserService {
    private static final long serialVersionUID = 1L; // Serial version UID for possible serialization
    private MongoDatabase database;
    private final String COLLECTION = "users"; // Name of the user collection

    // Constructor initializing the database connection    
	public UserService() { 
		 this.database = new MongoConfig().getDatabase(); //Retrieve the database instance from configuration 
	}
	 
    
    // Method to create a new user in the database
    public boolean createUser(User user) {
    	database = new MongoConfig().getDatabase();
        MongoCollection<Document> collection = database.getCollection(COLLECTION); // Retrieve the user collection
        // Check for existing user with the same email
        Document query = new Document("email", user.getUserEmail()); // Query to find a document with the same email
        Document existingUser = collection.find(query).first(); // Find first matching document

        if (existingUser != null) { // If a user with the same email exists
            // Print error message and return without creating a new user
            System.out.println("Error: A user with this email already exists.");
            return false;
        }

        // Create a new user document if no duplicate email is found
        Document doc = new Document("name", user.getUsername())  // Add user name
                .append("email", user.getUserEmail()) // Add user email
                .append("password", user.getUserPassword()); // Add user password
        collection.insertOne(doc); // Insert the document into the collection

        System.out.println("User created successfully."); // Confirm successful user creation
        return true;
    }
    
    // Method to retrieve a user by their email and password
    public User getUser(String email, String password) {
        MongoCollection<Document> collection = database.getCollection(COLLECTION); // Retrieve the user collection
        
        // Create a query that matches both the email and password
        Document query = new Document("email", email).append("password", password); // Match both email and password
        Document result = collection.find(query).first(); // Find the first matching document

        if (result != null) { // If a matching user is found
            return new User(
                result.getString("name"), // Retrieve and set user name
                result.getString("email"), // Retrieve and set user email
                result.getString("password"), // Retrieve and set user password (assuming the field exists)
                result.getObjectId("_id")
            );
        }
        
        return null; // Return null if no matching user is found
    }


}
