package com.study.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document; // MongoDB document representation
import org.bson.types.ObjectId; // MongoDB ObjectId for unique identifiers

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.study.dbconnect.MongoConfig; // Configuration class for connecting to MongoDB
import com.study.model.User; // User model class

// Service class that handles user-related operations with the database
public class UserService {
	private MongoDatabase database; // Instance of the MongoDB database
	private static final String COLLECTION = "users"; // Name of the collection where user data is stored

	// Constructor to initialize the database connection
	public UserService() {
		this.database = new MongoConfig().getDatabase(); // Get the database instance from MongoConfig
	}

	// Method to create a new user in the database
	public boolean createUser(User user) {
		MongoCollection<Document> collection = database.getCollection(COLLECTION); // Get the "users" collection

		// Check if a user with the same email already exists
		Document query = new Document("email", user.getEmail());
		Document existingUser = collection.find(query).first(); // Query the database for the email

		if (existingUser != null) {
			System.out.println("Error: A user with this email already exists.");
			return false; // Return false if the user already exists
		}

		// Create a new document representing the user
		Document doc = new Document("name", user.getName()) // Add name field
				.append("email", user.getEmail()) // Add email field
				.append("password", user.getPassword()) // Add password field
				.append("level", user.getLevel()); // Add level (e.g., admin or regular user)

		collection.insertOne(doc); // Insert the new user document into the collection

		System.out.println("User created successfully.");
		return true; // Return true to indicate success
	}

	// Method to retrieve a user by email and password (used for login)
	public User getUser(String email, String password) {
		MongoCollection<Document> collection = database.getCollection(COLLECTION); // Get the "users" collection

		// Query to find a user with matching email and password
		Document query = new Document("email", email).append("password", password);
		Document result = collection.find(query).first(); // Execute the query and fetch the first result

		if (result != null) {
			// If a user is found, create a User object from the database document
			return new User(result.getObjectId("_id"), // MongoDB ObjectId
					result.getString("email"), // Email
					result.getString("name"), // Name
					result.getString("password"), // Password
					result.getString("level") // User level
			);
		}

		return null; // Return null if no matching user is found
	}

	// Method to retrieve all users from the database
	public List<User> getUserList() {
		MongoCollection<Document> collection = database.getCollection(COLLECTION); // Get the "users" collection
		List<User> users = new ArrayList<>(); // Create a list to store users

		FindIterable<Document> documents = collection.find(); // Fetch all documents from the collection

		// Loop through each document and convert it to a User object
		for (Document doc : documents) {
			User user = new User(doc.getObjectId("_id"), // MongoDB ObjectId
					doc.getString("email"), // Email
					doc.getString("name"), // Name
					doc.getString("password"), // Password
					doc.getString("level") // User level
			);
			users.add(user); // Add the User object to the list
		}

		return users; // Return the list of users
	}

	// Method to update an existing user's details
	public boolean updateUser(String userId, String name, String level) {
		MongoCollection<Document> collection = database.getCollection(COLLECTION); // Get the "users" collection

		// Query to find the user by their MongoDB ObjectId
		Document query = new Document("_id", new ObjectId(userId));

		// Fields to be updated
		Document updateFields = new Document("name", name) // Update name
				.append("level", level); // Update level

		// Update command using $set operator
		Document update = new Document("$set", updateFields);

		// Execute the update and check if any document was modified
		if (collection.updateOne(query, update).getMatchedCount() > 0) {
			System.out.println("User updated successfully.");
			return true; // Return true if the update was successful
		} else {
			System.out.println("Error: User not found.");
			return false; // Return false if no matching user was found
		}
	}

	// Method to delete a user by their ID
	public boolean deleteUser(String userId) {
		MongoCollection<Document> collection = database.getCollection(COLLECTION); // Get the "users" collection

		// Query to find the user by their MongoDB ObjectId
		Document query = new Document("_id", new ObjectId(userId));

		// Execute the delete command and check if any document was deleted
		if (collection.deleteOne(query).getDeletedCount() > 0) {
			System.out.println("User deleted successfully.");
			return true; // Return true if the user was deleted
		} else {
			System.out.println("Error: User not found.");
			return false; // Return false if no matching user was found
		}
	}
}
