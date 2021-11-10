/*
	Name:           Ayden Masters
    Date:           Tuesday, February 16, 2021 17:58:36
    Exercise:       Project 3 Fitness Tracker
    Class:          COP2552
	File Name:      ActivityMain.java
	Synopsis:		This file will act as the main menu of the application it will loop until program is exited or 6 is pressed
*/

import javax.swing.JOptionPane; // Import the option pane class for the dialog boxes

public class ActivityMain {

	// Build class objects from our external classes
	private Bicycle bicycleStats = new Bicycle();
	private Run runStats = new Run();
	private Swim swimStats = new Swim();
	private Walk walkStats = new Walk();
	private Weights weightsStats = new Weights();
	private ExitProgram exitProgram = new ExitProgram();

	// Ask user to select a valid workout or exit the application
	private void getWorkout() {

		// Temporary values for storing and testing input
		String tempString = "";
		int tempInt = 0;

		while (tempInt < 1 || tempInt > 6) {

			try {

				tempString = JOptionPane.showInputDialog("Activity Monitor\n\n" + "Press 1 for Bicycle\n"
						+ "Press 2 for Running/Jogging\n" + "Press 3 for Swimming\n" + "Press 4 for Walking\n"
						+ "Press 5 for Weight Training\n" + "Press 6 to Exit");

				tempInt = (Integer.parseInt(tempString));

				if (tempInt < 1 || tempInt > 6) {

					JOptionPane.showMessageDialog(null, "Invalid Selection please try again!");

				}

			} catch (Exception e) {

				// Exit because user hit close/cancel on the dialog
				if (tempString == null) {

					System.exit(1);

				} else {

					// Catch any other errors
					JOptionPane.showMessageDialog(null, "Bad input please try again");

				}

			}

		}
		// Pass workout to intensity selection if less than 6 else skip
		if (tempInt != 6) {

			getIntensity(tempInt);

		} else {

			setWorkoutStats(6, 0);

		}

	}

	// Ask user to select a valid workout intensity
	private void getIntensity(int workoutSelection) {

		// Temporary values for storing and testing input
		String tempString = "";
		int tempInt = 0;

		while (tempInt < 1 || tempInt > 2) {

			try {

				tempString = JOptionPane.showInputDialog(
						"Your Workout Intensity\n\n" + "Press 1 for Moderate\n" + "Press 2 for Vigorous\n");

				tempInt = Integer.parseInt(tempString);

				if (tempInt < 1 || tempInt > 2) {

					JOptionPane.showMessageDialog(null, "Invalid Selection please try again!", "Error!",
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

		// Pass values to setWorkoutStats to handle selection of proper workout class
		setWorkoutStats(workoutSelection, tempInt);

	}

	// From user selection pass to proper class for calorie calculation
	private void setWorkoutStats(int workoutSelection, int intensitySelection) {

		switch (workoutSelection) {

			case 1:
				bicycleStats.calculateCalories(intensitySelection);
				break;

			case 2:
				runStats.calculateCalories(intensitySelection);
				break;

			case 3:
				swimStats.calculateCalories(intensitySelection);
				break;

			case 4:
				walkStats.calculateCalories(intensitySelection);
				break;

			case 5:
				weightsStats.calculateCalories(intensitySelection);
				break;

			case 6:
				exitProgram.displayOutput(bicycleStats.getBikeTotals() + runStats.getRunTotals()
						+ swimStats.getSwimTotals() + walkStats.getWalkTotals() + weightsStats.getWeightsTotals() + "\n"
						+ "Your total time working out was " + getWorkoutTotalTime() + " minutes"
						+ " and your total calories burned were " + getWorkoutTotalCalories());
				break;

			// This should never be reached but putting an error message just in case, then
			// exit the application with error code 1
			default:
				JOptionPane.showMessageDialog(null, "An error has occurred!", "Error!",
						JOptionPane.INFORMATION_MESSAGE);
				System.exit(1);
				break;

		}

		// Restart workout prompt
		getWorkout();

	}

	// Calculate total workout time for every workout
	private String getWorkoutTotalTime() {

		return String.format("%.2f", bicycleStats.getTime() + runStats.getTime() + swimStats.getTime()
				+ walkStats.getTime() + weightsStats.getTime());

	}

	// Calculate total calories burnt for every workout
	private String getWorkoutTotalCalories() {

		return String.format("%.0f", bicycleStats.getCalories() + runStats.getCalories() + swimStats.getCalories()
				+ walkStats.getCalories() + weightsStats.getCalories());

	}

	// Handle start of the application
	public void driver() {

		getWorkout();

	}
}
