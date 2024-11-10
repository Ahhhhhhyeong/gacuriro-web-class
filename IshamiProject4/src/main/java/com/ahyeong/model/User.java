package com.ahyeong.model;

import org.bson.types.ObjectId;

// Model class representing a User entity
public class User {

    private ObjectId id; // Unique identifier for the user
    private String userEmail; // Email of the user
    private String userName; // Name of the user
    private String userPassword; // Password of the user

    // Default constructor which generates a new ObjectId for the user
    public User(String userEmail, String userName, String userPassword) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    // Parameterized constructor to initialize user properties
    public User(String userName, String userEmail, String userPassword, ObjectId id) {
        this.userEmail = userEmail; // Assigns email to userEmail field
        this.userName = userName; // Assigns name to userName field
        this.userPassword = userPassword; // Assigns password to userPassword field
        this.id = id; // Assigns _id to id field
    }

    // Getter for id field
    public ObjectId getId() {
        return id;
    }

    // Setter for id field
    public void setId(ObjectId id) {
        this.id = id;
    }

    // Getter for userEmail field
    public String getUserEmail() {
        return userEmail;
    }

    // Setter for userEmail field
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    // Getter for userName field
    public String getUsername() {
        return userName;
    }

    // Setter for userName field
    public void setUsername(String userName) {
        this.userName = userName;
    }

    // Getter for userPassword field
    public String getUserPassword() {
        return userPassword;
    }

    // Setter for userPassword field
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
