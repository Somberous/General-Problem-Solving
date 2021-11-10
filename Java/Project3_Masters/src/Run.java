/*
	Name:           Ayden Masters
    Date:           Tuesday, February 16, 2021 17:58:36
    Exercise:       Project 3 Fitness Tracker
    Class:          COP2552
	File Name:      Run.java
	Synopsis:		Class to handle the running exercise selection
*/

import javax.swing.JOptionPane;

public class Run {

	private double time = 0;
	private double calories = 0;

	private double setTime() {

		// temporary value for storing and testing input
		String tempString = "";
		double tempInt = 0;

		while (tempInt <= 0) {

			try {

				tempString = JOptionPane.showInputDialog("How long did you run for? (Minutes)\n\n");
				tempInt = Double.parseDouble(tempString);

				// Prevent zero from being input for time
				if (tempInt <= 0) {

					JOptionPane.showMessageDialog(null, "Workout time can not be negative or 0!", "Error!",
							JOptionPane.INFORMATION_MESSAGE);

				}

			} catch (Exception e) {

				// Exit because user hit close/cancel on the dialog
				if (tempString == null) {

					System.exit(1);

				} else {

					// Catch any other errors
					JOptionPane.showMessageDialog(null, "Bad input please try again", "Error!",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}

		}

		time += tempInt;
		return tempInt;

	}

	// Calculate calories based on the intensity level passed to the method
	public void calculateCalories(int intensityLevel) {

		double tempInt = 0;
		tempInt = setTime();

		switch (intensityLevel) {

			case 1:
				calories += 12 * tempInt;
				break;

			case 2:
				calories += 17 * tempInt;
				break;

			// This should never be reached but putting an error message just in case, then
			// exit the application with error code 1
			default:
				JOptionPane.showMessageDialog(null, "An error has occurred.", "Error!",
						JOptionPane.INFORMATION_MESSAGE);
				break;

		}

	}

	// Return total time to caller
	public double getTime() {

		return time;

	}

	// Return total calories to caller
	public double getCalories() {

		return calories;

	}

	// Pass final values to caller of specified class object
	public String getRunTotals() {

		return "Your total time running was " + String.format("%.2f", time) + " minutes"
				+ " and your total calories burned were " + String.format("%.0f", calories) + " calories" + "\n";

	}

}
