/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    EmptyPasswordException.java
* 
* Synopsis:     This class will be responsible for informing
                the user that they tried to submit an empty 
                Password field.
*/

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EmptyPasswordException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 5246660089625120698L;

    public EmptyPasswordException() {

        Alert emptyPasswordFieldAlert = new Alert(AlertType.ERROR);
        emptyPasswordFieldAlert.setHeaderText("Invalid Password!");
        emptyPasswordFieldAlert.setContentText("Password field must not be empty!");
        emptyPasswordFieldAlert.showAndWait();

    }

}
