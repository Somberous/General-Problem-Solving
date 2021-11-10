/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    CountryPopulationQuery.java
* 
* Synopsis:     This class is responsible for querying the database
                For a specific countries population.
*/

import java.sql.*; // Needed for JDBC classes

public class CountryPopulationQuery {
    TestConnection database = new TestConnection(TestConnection.DBFILE);
    Connection databaseConnection = database.getDatabaseConnection();

    /**
     * Build the string for the query for the selected country
     * 
     * @param countryString selected Country
     * @return data found in the query
     */
    public String getPopulation(String countryString) {

        String query = "SELECT population FROM Country where name IN ('" + countryString + "')";
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

            // get population
            while (result.next()) {
                data += result.getString("population");
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
