/*
* Name:         Ayden Masters 
* Date:         Tuesday, March 23, 2021 13:39:09
* Exercise:     Project 5 Movie Genre Sort / Testing Inheritance
* Class:        COP2552 
* File Name:    Production.java
* 
* Synopsis:     This class will be responsible for the Production objects and the 
				Composer and Director data that is needed to create them.
*/

public class Production {

	protected String directorName;
	protected String composerName;

	/*
	 * Constructor for the class
	 */
	public Production(String dir, String comp) {

		directorName = dir;
		composerName = comp;

	}

	/*
	 * Initialize the object with default values
	 */
	Production() {

		directorName = "";
		composerName = "";

	}

	/*
	 * Methods for accessing and retrieving info about the movie
	 */
	public void setDirector(String name) {

		directorName = name;

	}

	public void setComposer(String name) {

		directorName = name;

	}

	public String getDirector() {

		return directorName;

	}

	public String getComposer() {

		return composerName;

	}

	/*
	 * Print out the Production Object
	 */
	@Override
	public String toString() {

		// Print to the Console also
		System.out.println("Director Name: " + directorName);
		System.out.println("Composer Name: " + composerName);

		return "Director Name: " + directorName + "\n" + "Composer Name: " + composerName;
	}

}
