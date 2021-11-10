/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    InvalidPasswordException.java
* 
* Synopsis:     This class will be responsible for informing
                the user that the password the input was
                incorrect.
*/

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidPasswordException extends Exception {

    /**
    *
    */
    private static final long serialVersionUID = 3246660089625120698L;

    public InvalidPasswordException() {

        Alert incorrectPasswordAlert = new Alert(AlertType.ERROR);
        incorrectPasswordAlert.setHeaderText("Invalid Password!");
        incorrectPasswordAlert.setContentText("The password you input is not correct!");
        incorrectPasswordAlert.showAndWait();

    }

}
