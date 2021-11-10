/*
	Name:           Ayden Masters
    Date:           Tuesday, February 16, 2021 17:58:36
    Exercise:       Project 3 Fitness Tracker
    Class:          COP2552
	File Name:      StartProgram.java
	Synopsis:		This will be what starts the entire application and calls the main menu class
*/

public class StartProgram {

	public static void main(String[] args) {

		// Instantiate an object of the main menu class then call the driver function
		ActivityMain activityMain = new ActivityMain();
		activityMain.driver();

	}

}
