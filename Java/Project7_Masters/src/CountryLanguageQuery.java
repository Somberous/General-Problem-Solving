/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    CountryLanguageQuery.java
* 
* Synopsis:     This class is responsible for querying the database
                For the languages spoken in a specific country.
*/

import java.sql.*; // Needed for JDBC classes

public class CountryLanguageQuery {
    TestConnection database = new TestConnection(TestConnection.DBFILE);
    Connection databaseConnection = database.getDatabaseConnection();

    /**
     * Receives the query from query Function and then executes it within the
     * database
     * 
     * @param query String to query the database with
     * @return the data found in the database
     */
    public String getLanguage(String countryString) {

        String query = "SELECT language FROM Country, Language where Language.CountryCode = Country.CountryCode AND Country.name IN ('"
                + countryString + "') ORDER by language.language";
        return executeQuery(query).trim();

    }

    private String executeQuery(String query) {
        ResultSet result = null;
        String data = "";

        try (Statement tempQuery = databaseConnection.createStatement()) {
            result = tempQuery.executeQuery(query);

            while (result.next()) {
                data += result.getString("Language");
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
