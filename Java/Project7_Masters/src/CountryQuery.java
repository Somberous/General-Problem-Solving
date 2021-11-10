/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    CountryQuery.java
* 
* Synopsis:     This class is responsible for querying the database
                For a specific country in the database.
*/

import java.sql.*; // Needed for JDBC classes
import java.util.ArrayList;
import java.util.List;

public class CountryQuery {

    TestConnection database = new TestConnection(TestConnection.DBFILE);
    Connection databaseConnection = database.getDatabaseConnection();

    /**
     * Build the query string from the selected continent
     * 
     * @param continentString selected continent
     * @return data from the query
     */
    public List<String> getCountry(String continentString) {

        String query = "SELECT Name FROM Country where continent IN ('" + continentString + "') ORDER by Name";
        return executeQuery(query);

    }

    /**
     * Receives the query from query Function and then executes it within the
     * database
     * 
     * @param query String to query the database with
     * @return the data found in the database
     */
    private ArrayList<String> executeQuery(String query) {
        ResultSet result = null;
        ArrayList<String> countryList = new ArrayList<>();

        try (Statement tempQuery = databaseConnection.createStatement()) {
            result = tempQuery.executeQuery(query);

            while (result.next()) {
                countryList.add(result.getString("Name"));
            }

        } catch (SQLException e) {
            // Unable to query database so print error
            System.out.println("Error: " + e.getMessage());
        }
        // Cleanup connection
        database.closeDatabaseConnection();
        return countryList;
    }

}
