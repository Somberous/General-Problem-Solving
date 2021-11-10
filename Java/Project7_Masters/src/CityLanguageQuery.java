/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    CityLanguageQuery.java
* 
* Synopsis:     This class is responsible for querying the database for 
                Languages that are spoken within a specific city.
*/

import java.sql.*; // Needed for JDBC classes

public class CityLanguageQuery {

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
	public String getLanguage(String cityString) {

		String query = "SELECT language FROM City, Language where Language.CountryCode = City.CountryCode AND City.cityName IN ('"
				+ cityString + "') ORDER by language.language";
		return executeQuery(query).trim();

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
