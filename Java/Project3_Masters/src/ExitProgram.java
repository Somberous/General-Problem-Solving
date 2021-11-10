/*
	Name:           Ayden Masters
    Date:           Tuesday, February 16, 2021 17:58:36
    Exercise:       Project 3 Fitness Tracker
    Class:          COP2552
	File Name:      ExitProgram.java
	Synopsis:		Class to handle the display of the final output of the program
*/

import javax.swing.JOptionPane;

// Print the final output to the user in a dialog box
public class ExitProgram {

	public void displayOutput(String workoutTotals) {

		JOptionPane.showMessageDialog(null, workoutTotals, "Final Stats",JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);

	}

}
