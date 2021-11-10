/*
	Name:           Ayden Masters
    Date:           Thursday, March 4, 2021 00:45:44
    Exercise:       Project 4 Hurricane Tool
    Class:          COP2552
	File Name:      HurricaneMain.java
	Synopsis:		This class will be responsible for the main menu and handling user selections
*/

import javax.swing.JOptionPane;

public class HurricanesMain {

	// Ask user to select a valid selection or exit the application
	private void getOutputSelection() {

		String[] options = { "Sort by Storm Name", "Sort by Storm Category", "Sort by Storm Year",
				"Sort by Storm Month", "Display Average Storm Category", "Display Most Active Year",
				"Display Total by Category", "Display Total by Year" };

		String selection = (String) JOptionPane.showInputDialog(null, "Select an option, or hit cancel to close.\n\n",
				"Florida Major Hurricanes 1950 - 2020", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

		// Exit because user hit close/cancel on the dialog
		if (selection == null) {

			System.exit(1);

		}
		getHurricaneInfo(selection);
	}

	// From user selection pass to proper class for displaying output
	private void getHurricaneInfo(String outputSelection) {

		switch (outputSelection) {

			case "Sort by Storm Name":
				new NameSort().driver();
				break;

			case "Sort by Storm Category":
				new CategorySort().driver();
				break;

			case "Sort by Storm Year":
				new YearSort().driver();
				break;

			case "Sort by Storm Month":
				new MonthSort().driver();
				break;

			case "Display Average Storm Category":
				new AverageCategory().driver();
				break;

			case "Display Most Active Year":
				new ActiveYear().driver();
				break;

			case "Display Total by Category":
				new TotalCategory().driver();
				break;

			case "Display Total by Year":
				new TotalYear().driver();
				break;

			// This should never be reached but putting an error message just in case, then
			// exit the application with error code 1
			default:
				JOptionPane.showMessageDialog(null, "An error has occurred!", "Error!",
						JOptionPane.INFORMATION_MESSAGE);
				System.exit(1);
				break;

		}

		// Restart prompt
		getOutputSelection();

	}

	public void driver() {
		// call the driver method of the hurricane creation class
		new CreateHurricaneObjects().driver();
		// Enter main menu
		getOutputSelection();
	}

}
