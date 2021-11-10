/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    CityPopulationQuery.java
* 
* Synopsis:     This class is responsible for querying the database for 
                the population of a given city.
*/

import java.sql.*; // Needed for JDBC classes

public class CityPopulationQuery {
    // Class constants
    TestConnection database = new TestConnection(TestConnection.DBFILE);
    Connection databaseConnection = database.getDatabaseConnection();

    /**
     * Builds the query string to query the database and then passes to a private
     * function which executes the query and returns the data from the database
     * 
     * @param cityString City to query from the Database
     * @return The data from the query
     */
    public String getPopulation(String cityString) {

        String query = "SELECT population FROM City where cityName IN ('" + cityString + "')";
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
