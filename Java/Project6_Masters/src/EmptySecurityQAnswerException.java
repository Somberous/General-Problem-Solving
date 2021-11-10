/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    EmptySecurityQAnswerException.java
* 
* Synopsis:     This class will be responsible for informing
                the user that they tried to submit an empty 
                security question field.
*/

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class EmptySecurityQAnswerException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4813859824257561485L;

    public EmptySecurityQAnswerException() {

        Alert emptyAccountFieldAlert = new Alert(AlertType.ERROR);
        emptyAccountFieldAlert.setHeaderText("Invalid Security Question Answer!");
        emptyAccountFieldAlert
                .setContentText("Security Question field must not be empty upon submission, please try again!");
        emptyAccountFieldAlert.showAndWait();

    }

}
