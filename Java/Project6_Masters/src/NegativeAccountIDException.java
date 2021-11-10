/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    NegativeAccountIDException.java
* 
* Synopsis:     This class will be responsible for informing
                the user that the input a negative account ID.
*/

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class NegativeAccountIDException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1575951481707889815L;

    public NegativeAccountIDException(String string) {

        Alert negativeAccountFieldAlert = new Alert(AlertType.ERROR);
        negativeAccountFieldAlert.setHeaderText("Invalid ID!");
        negativeAccountFieldAlert.setContentText("Please enter a positive number! you entered " + string + ".");
        negativeAccountFieldAlert.showAndWait();

    }

}
