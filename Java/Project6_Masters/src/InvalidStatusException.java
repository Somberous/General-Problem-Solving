/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    InvalidStatusException.java
* 
* Synopsis:     This class will be responsible for informing
                the user that their account status is invalid.
*/

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidStatusException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 5504809175544751147L;

    public InvalidStatusException() {

        Alert invalidAccountStatusAlert = new Alert(AlertType.ERROR);
        invalidAccountStatusAlert.setHeaderText("Invalid Account Satus!");
        invalidAccountStatusAlert
                .setContentText("Your account status is incorrect, please contact us to get this issue resolved!");
        invalidAccountStatusAlert.showAndWait();

    }

}
