/*
	Name:           Ayden Masters
    Date:           Thursday, March 4, 2021 00:45:44
    Exercise:       Project 4 Hurricane Tool
    Class:          COP2552
	File Name:      Hurricane.java
	Synopsis:		This class will be the blueprint for building the hurricane objects
*/

public class Hurricane {

	private String name;
	private int category;
	private String date;

	public String getName() {

		return name;

	}

	public String getDate() {

		return date;

	}

	public int getCategory() {

		return category;

	}

	public int getMonth() {

		String[] month = getDate().split("[/]");

		return Integer.parseInt(month[0]);

	}

	public int getYear() {

		String[] year = getDate().split("[/]");

		return Integer.parseInt(year[2]);

	}

	// How we will create the Hurricane object
	Hurricane(String name, int category, String date) {

		this.name = name;
		this.category = category;
		this.date = date;

	}

}
