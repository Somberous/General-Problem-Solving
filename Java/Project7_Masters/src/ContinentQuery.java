/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    ContinentQuery.java
* 
* Synopsis:     This class is responsible for querying the database
                For all the continents within the database.
*/

import java.sql.*; // Needed for JDBC classes
import java.util.ArrayList;
import java.util.List;

public class ContinentQuery {
    // Class Constants
    TestConnection database = new TestConnection(TestConnection.DBFILE);
    Connection databaseConnection = database.getDatabaseConnection();

    /**
     * Receives the query from query Function and then executes it within the
     * database
     * 
     * @param query String to query the database with
     * @return the data found in the database
     */
    private List<Continent> executeQuery(String query) {
        ResultSet result = null;
        List<Continent> continents = new ArrayList<>();

        try (Statement tempQuery = databaseConnection.createStatement()) {
            result = tempQuery.executeQuery(query);

            // Build the continent objects
            while (result.next()) {
                continents
                        .add(new Continent(result.getString(1), result.getInt(2), result.getInt(3), result.getInt(4)));
            }

        } catch (SQLException e) {
            // Unable to query database so print error
            System.out.println("Error: " + e.getMessage());
        }
        // Cleanup connection
        database.closeDatabaseConnection();
        return continents;
    }

    /**
     * Build the query string and pass it to the private function to execute the
     * query and then return it
     * 
     * @return continents from the database
     */
    public List<Continent> getContinents() {
        String query = "SELECT DISTINCT Country.continent, COUNT(City.CityName),"
                + "COUNT(Country.CountryCode), COUNT(Language.language) FROM Country,Language"
                + ",City where Language.CountryCode = Country.CountryCode AND City.CountryCode"
                + " = Country.CountryCode GROUP by Country.continent ORDER by Country.continent";
        return executeQuery(query);
    }
}
