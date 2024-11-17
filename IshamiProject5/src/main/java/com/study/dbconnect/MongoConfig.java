package com.study.dbconnect;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConfig {
    private String dbConfigFile;

    public MongoConfig() {

    }
    // Constructor to accept configuration file name
    public MongoConfig(String dbConfigFile) {
        this.dbConfigFile = dbConfigFile;
    }

    // Method to retrieve a MongoDatabase connection
    public MongoDatabase getDatabase() {
        String connectUrl = readProperties("db.connecturl");
        String dbName = readProperties("db.databasename");

        MongoClient mongoClient = MongoClients.create(connectUrl);
        MongoDatabase database = mongoClient.getDatabase(dbName);
        return database;
    }

    // Method to read properties from a configuration file
    public String readProperties(String key) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return "";
            }
            properties.load(input);
        } catch (IOException ex) {
            System.out.println("IOException");
            System.err.println(ex.getMessage());
            return "";
        }
        return properties.getProperty(key);
    }
}
