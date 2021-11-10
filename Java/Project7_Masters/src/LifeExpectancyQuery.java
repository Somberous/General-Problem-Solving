/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    LifeExpectancyQuery.java
* 
* Synopsis:     This class is responsible for querying the database
                For the life expectancy in a given country.
*/

import java.sql.*; // Needed for JDBC classes

public class LifeExpectancyQuery {
    TestConnection database = new TestConnection(TestConnection.DBFILE);
    Connection databaseConnection = database.getDatabaseConnection();

    /**
     * Build the query string and pass it to the private function to execute the
     * query and then return it
     * 
     * @param countryString selected country
     * @return data found from the query
     */
    public String getLifeExpectancy(String countryString) {

        String query = "SELECT lifeExpectancy FROM Country where name IN ('" + countryString + "')";
        return executeQuery(query);

    }

    /**
     * Receives the query from query Function and then executes it within the
     * database
     * 
     * @param query String to query the database with
     * @return the data found in the database
     */
    private String executeQuery(String query) {
        ResultSet result = null;
        String data = "";

        try (Statement tempQuery = databaseConnection.createStatement()) {
            result = tempQuery.executeQuery(query);

            // Data
            while (result.next()) {
                data += result.getString("lifeExpectancy");
            }

        } catch (SQLException e) {
            // Unable to query database so print error
            System.out.println("Error: " + e.getMessage());
        }
        // Cleanup connection
        database.closeDatabaseConnection();
        return data;
    }
}
