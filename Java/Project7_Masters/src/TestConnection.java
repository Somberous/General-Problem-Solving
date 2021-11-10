/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    TestConnection.java
* 
* Synopsis:     This class is responsible for creating a connection to 
                the database and allow for queries to occur within the database
*/

import java.sql.*; // Needed for JDBC classes

public class TestConnection {

    // Private variables for the class
    static final String DBFILE = "jdbc:derby:WorldDB";
    private String dataUrl;
    private Connection databaseConnection;

    // Empty constructor
    public TestConnection() {
    }

    // Constructor
    public TestConnection(String dataUrl) {
        this.dataUrl = dataUrl;
        openDatabaseConnection();
    }

    /**
     * Accessor and mutator methods
     */
    public String getDataUrl() {
        return this.dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public Connection getDatabaseConnection() {
        return this.databaseConnection;
    }

    public void setDatabaseConnection(Connection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Allow the user to close out the database connection that they created
     */
    public void closeDatabaseConnection() {
        try {
            databaseConnection.close();
        } catch (SQLException e) {
            // Print out error
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Allow user to open the connection to the database
     */
    public void openDatabaseConnection() {
        try {
            databaseConnection = DriverManager.getConnection(dataUrl);
        } catch (SQLException e) {
            // Print out error
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "{" + " dataUrl='" + getDataUrl() + "'" + ", databaseConnection='" + getDatabaseConnection() + "'" + "}";
    }
}
