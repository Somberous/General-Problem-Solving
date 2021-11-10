/*
    * Name: Ayden Masters 
    * Date: Friday, January 29, 2021 10:27:20
    * Exercise: Project 2
    * Patient list Updater
    * Class: COP2552 
    * File Name: PatientInfo.java
*/

import java.io.*; // Import to allow for file manipulation
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class PatientInfo {

    Scanner originalPatient; // open file
    Scanner newPatient; // open file
    Scanner removePatient; // open file
    PrintWriter newWriter; // output file
    PrintWriter errorWriter; // output file
    File path;
    File newPatientFile = new File("./NewPatientList.txt");
    File removePatientFile = new File("./RemovePatientList.txt");
    private boolean errorCheck = false;

    // Load the files into the scanner objects so that we can sort them
    private void loadPatientFile() {
        try {

            // Load files and set them to the scanner variable declared above
            originalPatient = new Scanner(new File("./PatientListW3.txt"));

        } catch (FileNotFoundException e) {

            System.out.println("Files do not exist in the current directory, please locate the files then try again.");
            System.exit(20);

        }
    }

    // Neat Idea i thought of just to make the application a little more modular
    // since I run linux quite a bit
    private String checkOs() {
        return System.getProperty("os.name");
    }

    private void createNewPatientFile(File file) throws FileNotFoundException {

        try {

            newWriter = new PrintWriter(new File(file + "/PatientListW4.txt"));

        } catch (FileNotFoundException e) {

            System.out.println("Files not found at " + file + " attempting to create file path.");
            // Just call the driver function again to attempt to recreate the path.
            driver();

        }

    }

    // Handler for creating error file but only if function is called once
    private void createNewErrorFile(File file) throws FileNotFoundException {
        File txtFile = new File(file + "/PatientErrorsW4.txt");
        if (!(txtFile.exists())) {
            try {

                errorWriter = new PrintWriter(new File(file + "/PatientErrorsW4.txt"));
                // Add date to the file on creation
                writeError(getDate());

            } catch (FileNotFoundException e) {

                System.out.println("Files not found at " + file);


            }
        } else {
            // do nothing
        }
    }

    // This function will handle creating a folder path for the output files
    private void createFolderPath() {
        try {
            if (!checkOs().toLowerCase().contains("win")) {
                // Keep current working directory and create folder for output files if it is a
                // Mac/Linux Box
                path = new File("./Outputfiles/");
            } else {
                path = new File("C:/SFC/COP2552/Project2/");
            }

            // Create directory if the path does not exist
            if (!path.exists()) {
                path.mkdirs();
            }
            // Create .txt files once directory is created
            createNewPatientFile(path);

        } catch (Exception e) {

            System.out.println("Directory does not exist please create " + path + " for the program to operate.");
            e.printStackTrace();

        }
    }

    // Compare the values and send them to their proper output file
    private void compareFiles(Patient pat) throws FileNotFoundException {
        // Expected outcome of this was to loop through the new patient file and the
        // remove patient file,
        // Which would test the value against the entire new patient file then write it
        // to the file from the decision structure
        // But it does not seem to operate in this way. I am at a loss at this point.
        // Furthermore I guess java does some weird stuff where == will not return true
        // even if the values are the same. Without using arrays or some data structure
        // to remember values between files
        // I truly am not sure how
        // you would go about solving this problem
        Patient current = pat;
        while (newPatient.hasNextLine()) {
            Patient newPat = getNewPat();
            if (newPat.getId() < current.getId()) {

                // Test the current patient value against any value in remove patient if it
                // matches write to error log
                while (removePatient.hasNext()) {
                    if (getRemovePat().getId() == current.getId()) {
                        break;
                    }
                }
                // maintain descending order
                writeNew(current.toString());
                writeNew(newPat.toString());
            } else if (newPat.getId() > current.getId()) {
                while (removePatient.hasNext()) {
                    // Test the current patient value against any value in remove patient if it
                    // matches write to error log
                    if (getRemovePat().getId() == current.getId()) {
                        break;
                    }
                }
                // maintain descending order
                writeNew(current.toString());
                writeNew(newPat.toString());
            } else if (current.getId() == newPat.getId()) {
                // this covers any value not caught by the above expression
                writeError(newPat.toString());
            } else {
                System.out.println("error");
            }
        }
    }

    // Handler for getting every ID from current patient list
    private void loopFiles() throws FileNotFoundException {
        // skip first line of the file
        originalPatient.nextLine();
        // Write date to the top of the output file
        writeNew(getDate());

        // loop through each ID in file
        while (originalPatient.hasNextLine()) {
            newPatient = new Scanner(newPatientFile);
            removePatient = new Scanner(removePatientFile);
            Patient current = getCurrentPat();
            compareFiles(current);
        }
    }

    // Build patient object from current patient list
    private Patient getCurrentPat() {
        return new Patient(originalPatient.nextInt(), originalPatient.next().concat(originalPatient.next()),
                originalPatient.nextInt(), originalPatient.nextInt());
    }

    // Build patient object from new patient list
    private Patient getNewPat() {
        return new Patient(newPatient.nextInt(), newPatient.next().concat(newPatient.next()), newPatient.nextInt(),
                getYear());

    }

    // Build patient object from remove patient list
    private Patient getRemovePat() {
        return new Patient(removePatient.nextInt(), removePatient.next().concat(removePatient.next()),
                removePatient.nextInt(), getYear());
    }

    // Write a string to W4 output file
    private void writeNew(String string) {
        newWriter.println(string);
    }

    // Write a string to the error file
    private void writeError(String string) throws FileNotFoundException {
        errorCheck = true;
        errorWriter = new PrintWriter(new File(path + "/PatientErrorsW4.txt"));
        errorWriter.println(string);
    }

    // Get year to add to the end of new patient files
    private int getYear() {

        return Calendar.getInstance().get(Calendar.YEAR);

    }

    // Get date function for the date at the top of the output files
    private String getDate() {

        SimpleDateFormat date = new SimpleDateFormat("MMddyyyy");
        // Setting the time zone
        return date.format(new Date());

    }

    // Close the files
    private void closeFiles() {

        newWriter.close();
        if (errorCheck == true) {
            errorWriter.close();
        }

    }

    // Close scanner objects
    private void closeScanners() {
        originalPatient.close();
        newPatient.close();
        removePatient.close();
    }

    // Driver function
    public void driver() throws FileNotFoundException {

        createFolderPath();
        loadPatientFile();
        loopFiles();
        closeFiles();
        closeScanners();

    }
}
