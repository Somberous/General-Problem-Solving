/*
	Name:           Ayden Masters
    Date:           Thursday, March 4, 2021 00:45:44
    Exercise:       Project 4 Hurricane Tool
    Class:          COP2552
	File Name:      CreateHurricaneObjects.java
	Synopsis:		This class will be responsible for creating the hurricane array which will be used throughout the application
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CreateHurricaneObjects {

	File inputFile = new File("src/NamedFloridaHurricanes.txt");
	private int fileLength = getFileLength(inputFile);
	Hurricane[] hurricanes = new Hurricane[fileLength];

	// Load the files into the scanner objects so that we can sort them
	private void setHurricanesArray() {

		try (Scanner scanner = new Scanner(inputFile);) {

			for (int i = 0; i < fileLength; i++) {

				String line = scanner.nextLine();
				String[] tokens = line.split("[,:]");

				hurricanes[i] = new Hurricane(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);

			}

		} catch (FileNotFoundException e) {

			System.out.println("Files do not exist in the current directory, please locate the files then try again.");
			System.exit(20);

		}
	}

	private int getFileLength(File file) {

		int i = 0;

		try (Scanner scanner = new Scanner(file);) {

			// count for each line that we encounter within the file
			while (scanner.hasNext()) {

				scanner.nextLine();
				i++;

			}

		} catch (FileNotFoundException e) {

			System.out.println(
					"Unable to locate the files to calculate the length of the file, please locate the files and try again.");
			System.exit(20);

		}
		return i;
	}

	// Build the hurricane objects, this will be called by the main menu
	public void driver() {

		getHurricanesArray();

	}

	// Pass a copy of the array to the caller
	public Hurricane[] getHurricanesArray() {
		setHurricanesArray();
		return hurricanes;

	}

}
