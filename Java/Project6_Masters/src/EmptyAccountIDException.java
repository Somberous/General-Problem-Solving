/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    EmptyAccountIDException.java
* 
* Synopsis:     This class will be responsible for informing
                the user that they tried to submit an empty 
                account id field.
*/

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EmptyAccountIDException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4812859824257561485L;

    public EmptyAccountIDException() {

        Alert emptyAccountFieldAlert = new Alert(AlertType.ERROR);
        emptyAccountFieldAlert.setHeaderText("Invalid ID!");
        emptyAccountFieldAlert.setContentText("Account ID field must not be empty!");
        emptyAccountFieldAlert.showAndWait();

    }

}
