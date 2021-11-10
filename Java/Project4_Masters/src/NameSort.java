/*
	Name:           Ayden Masters
    Date:           Thursday, March 4, 2021 00:45:44
    Exercise:       Project 4 Hurricane Tool
    Class:          COP2552
	File Name:      NameSort.java
	Synopsis:		This class will be responsible for sorting,outputting, and displaying Hurricane Names
*/

import java.util.Arrays;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class NameSort {

	Hurricane[] hurricanes = new CreateHurricaneObjects().getHurricanesArray();
	File path;

	// Ask user to select sort order
	private void getSortOrder() {

		String[] options = { "Sort Ascending", "Sort Descending" };

		String selection = (String) JOptionPane.showInputDialog(null, "Sort By Hurricane Name\n\n",
				"Major Florida Hurricanes 1950 - 2020", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		

		// Exit because user hit close/cancel on the dialog
		if (selection == null) {

			System.exit(1);

		}

		setSortOrder(selection);

	}

	// Call sort method
	private void setSortOrder(String sortSelection) {

		switch (sortSelection) {

			case "Sort Ascending":
				sortAscending();
				break;

			case "Sort Descending":
				sortDescending();
				break;

			// This should never be reached but putting an error message just in case, then
			// exit the application with error code 1
			default:
				JOptionPane.showMessageDialog(null, "An error has occurred!", "Error!",
						JOptionPane.INFORMATION_MESSAGE);
				System.exit(1);
				break;

		}

	}

	// Switch statement to handle ascending sort
	private void sortAscending() {

		Arrays.sort(hurricanes, (first, second) ->

		first.getName().compareTo(second.getName())

		);

		displayOutput("Hurricane names in Ascending Order");

	}

	// Switch statement to handle descending sort
	private void sortDescending() {

		Arrays.sort(hurricanes, (first, second) ->

		second.getName().compareTo(first.getName())

		);

		displayOutput("Hurricane names in Descending Order");
	}

	// Display the output in a Jtable for the user
	private void displayOutput(String sortType) {

		JFrame frame = new JFrame(sortType);
		JTable table = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(0, 0);
		String[] header = { "Name", "Category", "Date" };

		frame.add(new JScrollPane(table));
		tableModel.setColumnIdentifiers(header);

		table.setModel(tableModel);

		for (int i = 0; i < hurricanes.length; i++) {

			String name = hurricanes[i].getName();
			int category = hurricanes[i].getCategory();
			String date = hurricanes[i].getDate();

			tableModel.addRow(new Object[] { name, category, date });
		}

		setProperties(frame);
		createFolderPath(table);
		setEventListener(frame);
		JOptionPane.showMessageDialog(null, "Your output file has been created at " + path + "\\SortByName.txt",
				"Complete!", JOptionPane.INFORMATION_MESSAGE);

	}

	private void setProperties(JFrame frame) {

		// Format the frame
		frame.setSize(300, 300);
		frame.pack();
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

	}

	private void setEventListener(JFrame frame) {

		// This was much more difficult to achieve then I had initially imagined.
		// Here is the link to what helped me solve this
		// LINK:
		// https://stackoverflow.com/questions/1341699/how-do-i-make-a-thread-wait-for-jframe-to-close-in-java
		Object lock = new Object();

		Thread eventThread = new Thread() {
			@Override
			public void run() {
				synchronized (lock) {
					while (frame.isVisible())
						try {
							lock.wait();
						} catch (InterruptedException e) {
							JOptionPane.showMessageDialog(null, "An error has occurred!", "Error!", JOptionPane.INFORMATION_MESSAGE);
							Thread.currentThread().interrupt();
						}
				}
			}
		};
		eventThread.start();

		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				synchronized (lock) {
					frame.setVisible(false);
					lock.notify();
				}
			}

		});

		try {
			eventThread.join();
		} catch (InterruptedException e) {

			JOptionPane.showMessageDialog(null, "An error has occurred!", "Error!", JOptionPane.INFORMATION_MESSAGE);
			Thread.currentThread().interrupt();
		}

	}

	private void writeOutput(JTable table) {
		try {
			PrintWriter p = new PrintWriter(new File(path + "/SortByName.txt"));

			for (int j = 0; j < 3; j++) {
				p.print(table.getModel().getColumnName(j) + "\t\s\s");
			}

			p.print("\n\n\n\n");
			for (int i = 0; i < table.getRowCount(); i++) {
				for (int j = 0; j < table.getColumnCount(); j++) {

					Object str = table.getModel().getValueAt(i, j);
					p.print(str.toString().replaceAll("\\s", "") + "\t\t");

				}
				// Formatting
				p.print("\n______________________________________________\n");
			}
			p.close();

		} catch (IOException e) {

			JOptionPane.showMessageDialog(null, "An error has occurred!", "Error!", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	// Neat Idea i thought of just to make the application a little more modular
	// since I run linux quite a bit
	private String checkOs() {
		return System.getProperty("os.name");
	}

	// This function will handle creating a folder path for the output files
	private void createFolderPath(JTable table) {
		try {
			if (!checkOs().toLowerCase().contains("win")) {
				// Keep current working directory and create folder for output files if it is a
				// Mac/Linux Box
				path = new File("./Outputfiles/");
			} else {
				path = new File("C:/SFC/COP2552/Project4/");
			}
			// Create directory if the path does not exist
			if (!path.exists()) {
				path.mkdirs();
			}
			writeOutput(table);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Directory does not exist please create " + path + " for the program to operate.", "Error!",
					JOptionPane.INFORMATION_MESSAGE);

		}
	}

	public void driver() {

		getSortOrder();

	}

}
