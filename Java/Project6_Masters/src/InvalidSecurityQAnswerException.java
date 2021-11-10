/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    InvalidSecurityQAnswerException.java
* 
* Synopsis:     This class will be responsible for informing
                the user that the security answer they input
                is incorrect.
*/

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidSecurityQAnswerException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 8083344077972297551L;

    public InvalidSecurityQAnswerException() {

        Alert invalidSecurityAnswerAlert = new Alert(AlertType.ERROR);
        invalidSecurityAnswerAlert.setHeaderText("Invalid security question answer!");
        invalidSecurityAnswerAlert.setContentText("Your security question answer was incorrect!");
        invalidSecurityAnswerAlert.showAndWait();

    }

}
