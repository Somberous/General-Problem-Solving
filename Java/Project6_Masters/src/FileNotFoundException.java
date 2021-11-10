/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    FileNotFoundException.java
* 
* Synopsis:     This class will be responsible for informing
                the user that the file they selected was not
                accessible and tell them the file the application
                tried to find.
*/

import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

// Extend IOException rather than exception because FileNOtFound extends IOException
public class FileNotFoundException extends IOException {

    /**
     *
     */
    private static final long serialVersionUID = 5428201684034271940L;

    public FileNotFoundException(String file) {

        Alert fileNotFoundAlert = new Alert(AlertType.ERROR);
        fileNotFoundAlert.setHeaderText("Data file not found!");
        fileNotFoundAlert.setContentText("The data file could not be found at the path " + file
                + ". Please select a valid file from the file system.");
        fileNotFoundAlert.showAndWait();

    }

}
