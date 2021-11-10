/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    InvalidAccountNumberException.java
* 
* Synopsis:     This class will be responsible for informing
                the user that the user ID they input does not
                exist.
*/
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class InvalidAccountNumberException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 48759290703579212L;

    public InvalidAccountNumberException(String string) {
        Alert invalidAccountNumberAlert = new Alert(AlertType.ERROR);
        invalidAccountNumberAlert.setHeaderText("Invalid Account Number!");
        invalidAccountNumberAlert.setContentText("The Account ID " + string + " does not exist!");
        invalidAccountNumberAlert.showAndWait();
    }
}
