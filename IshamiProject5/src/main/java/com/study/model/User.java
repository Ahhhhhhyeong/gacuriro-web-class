package com.study.model;

import org.bson.types.ObjectId; // Import ObjectId for MongoDB integration

// Model class representing a User entity
public class User {

	private ObjectId id; // Unique identifier for the user (MongoDB ObjectId)
	private String email; // Email of the user
	private String name; // Name of the user
	private String password; // Password of the user
	private String level; // Level of the user (e.g., admin, regular user)

	// Constructor to initialize all fields
	public User(ObjectId id, String email, String name, String password, String level) {
		this.id = id; // Assigns the provided ObjectId
		this.email = email; // Assigns the provided email
		this.name = name; // Assigns the provided name
		this.password = password; // Assigns the provided password
		this.level = level; // Assigns the provided level
	}

	// Constructor for creating a user without an ObjectId
	public User(String email, String name, String password, String level) {
		this.email = email; // Assigns the provided email
		this.name = name; // Assigns the provided name
		this.password = password; // Assigns the provided password
		this.level = level; // Assigns the provided level
	}

	// Constructor for creating a user without a level (default level can be added
	// later)
	public User(String email, String name, String password) {
		this.email = email; // Assigns the provided email
		this.name = name; // Assigns the provided name
		this.password = password; // Assigns the provided password
	}

	// Constructor to initialize all fields including the ObjectId
	public User(String name, String email, String password, ObjectId id) {
		this.email = email; // Assigns email to the email field
		this.name = name; // Assigns name to the name field
		this.password = password; // Assigns password to the password field
		this.id = id; // Assigns _id to the id field
	}

	// Getter for the ObjectId
	public ObjectId getId() {
		return id; // Returns the user's unique identifier
	}

	// Getter for the email
	public String getEmail() {
		return email; // Returns the user's email
	}

	// Getter for the name
	public String getName() {
		return name; // Returns the user's name
	}

	// Getter for the password
	public String getPassword() {
		return password; // Returns the user's password
	}

	// Getter for the level
	public String getLevel() {
		return level; // Returns the user's level
	}
}
