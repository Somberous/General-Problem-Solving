/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    ContinentLanguageQuery.java
* 
* Synopsis:     This class is responsible for querying the database
                For the languages spoken in a specific continent.
*/

import java.sql.*; // Needed for JDBC classes
import java.util.ArrayList;
import java.util.List;

public class ContinentLanguageQuery {
    // Class Constants
    TestConnection database = new TestConnection(TestConnection.DBFILE);
    Connection databaseConnection = database.getDatabaseConnection();

    /**
     * Builds the query string to query the database and then passes to a private
     * function which executes the query and returns the data from the database
     * 
     * @param continentString continent name
     * @return Data from the query
     */
    public List<String> getLanguage(String continentString) {

        String query = "SELECT language FROM Country, Language where Language.CountryCode = Country.CountryCode AND Country.continent IN ('"
                + continentString + "') ORDER by language.language";
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
        ArrayList<String> languageList = new ArrayList<>();

        try (Statement tempQuery = databaseConnection.createStatement()) {
            result = tempQuery.executeQuery(query);

            while (result.next()) {
                languageList.add(result.getString("Language"));
            }

        } catch (SQLException e) {
            // Unable to query database so print error
            System.out.println("Error: " + e.getMessage());
        }
        // Cleanup connection
        database.closeDatabaseConnection();
        return languageList;
    }
}
