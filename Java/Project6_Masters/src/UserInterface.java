/*
* Name:         Ayden Masters 
* Date:         Friday, April 9, 2021 14:15:12
* Exercise:     Project 6 Intro to JavaFX
* Class:        COP2552 
* File Name:    UserInterface.java
* 
* Synopsis:     This class will be responsible for the entire user interface.
                it will handle all errors, setup the scenes and control the 
                scene transitions. This is the main part of the application 
*/

import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserInterface extends Application {

    // #region Search Term: Application Setup

    /**
     * local variables for validation
     */
    PasswordField passwordInput;
    TextField accountInput;
    TextInputDialog securityAnswer;
    File dataFile;
    Stage primaryStage;
    Accounts dataAccount; // store this after user validated

    /**
     * Launch the application
     * 
     * @param args from the start method
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Build the gui on application launch
     * 
     * @param primaryStage current application Stage
     */
    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        setFileChooser();

        setLoginSceneProperties();

    }

    // #endregion

    // #region Search Term: Login GUI Setup

    /**
     * Add the buttons to the GridPane
     * 
     * @param grid current GridPane being modified
     */
    private void setLoginSceneButtonProperties(GridPane grid) {
        setCancelButton(grid);

        setClearButton(grid);

        setLoginButton(grid);

    }

    /**
     * Setup the cancel button properties and its on click event
     * 
     * @param grid current GridPane being modified
     */
    private void setCancelButton(GridPane grid) {
        // Cancel button properties
        Button cancelButton = new Button("Cancel");
        HBox cancelButtonBounds = new HBox(10);
        cancelButtonBounds.setAlignment(Pos.BOTTOM_LEFT);
        cancelButtonBounds.getChildren().add(cancelButton);
        grid.add(cancelButtonBounds, 0, 7);

        // Cancel button action
        cancelButton.setOnAction(e -> {
            System.out.println("Login operation canceled by user.");
            System.exit(0);
        });
    }

    /**
     * Setup the login button properties and its on click event
     * 
     * @param grid current GridPane being modified
     */
    private void setLoginButton(GridPane grid) {
        // Login button properties
        Button loginButton = new Button("Sign in");
        HBox loginButtonBounds = new HBox(10);
        loginButtonBounds.setAlignment(Pos.BOTTOM_RIGHT);
        loginButtonBounds.getChildren().add(loginButton);
        grid.add(loginButtonBounds, 2, 7);

        // Login button action
        loginButton.setOnAction(e -> readFileData(dataFile.getAbsolutePath()));
    }

    /**
     * Setup the clear button properties and its on click event
     * 
     * @param grid current GridPane being modified
     */
    private void setClearButton(GridPane grid) {
        // Login button properties
        Button clearButton = new Button("Clear");
        HBox clearButtonBounds = new HBox(10);
        clearButtonBounds.setAlignment(Pos.BOTTOM_CENTER);
        clearButtonBounds.getChildren().add(clearButton);
        grid.add(clearButtonBounds, 1, 7);

        // Login button action
        clearButton.setOnAction(e -> clearForm());
    }

    /**
     * Set the input fields on the grid
     * 
     * @param grid current GridPane being modified
     */
    private void setLoginInputFields(GridPane grid) {
        setAccountInput(grid);

        setPasswordInput(grid);

        setSecurityQTextInputDialog();
    }

    /**
     * Configure the Account ID input field
     * 
     * @param grid current GridPane being modified
     */
    private void setAccountInput(GridPane grid) {
        // Account input label and text box settings
        Label accountLabel = new Label("Account ID:");
        grid.add(accountLabel, 0, 3);
        accountInput = new TextField();
        grid.add(accountInput, 1, 3);

        // Only allow numerical input into the TextField

        accountInput.textProperty()
                .addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                    if (!newValue.matches("\\d*")) {
                        accountInput.setText(newValue.replaceAll("[^\\d]", ""));
                    }

                });
    }

    /**
     * Configure the Password input field
     * 
     * @param grid current GridPane being modified
     */
    private void setPasswordInput(GridPane grid) {
        // Password input label and text box settings
        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 4);
        passwordInput = new PasswordField();
        grid.add(passwordInput, 1, 4);
    }

    /**
     * Configure the Security question popup
     * 
     */
    private void setSecurityQTextInputDialog() {
        securityAnswer = new TextInputDialog("Security Answer");
        securityAnswer.setHeaderText("Validating it's you");
        securityAnswer.setTitle("Enter your Security Answer");
    }

    /**
     * Set the title of the GridPane and logo image then configure their properties
     * 
     * @param grid current GridPane being modified
     */
    private void setLoginTitleProperties(GridPane grid) {
        // Title for the UI Window
        Label sceneTitle = new Label("Please Login");
        ImageView image = new ImageView("logo.png");
        sceneTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        HBox logo = new HBox(image);
        HBox title = new HBox(sceneTitle);
        logo.setAlignment(Pos.CENTER);
        title.setAlignment(Pos.CENTER);
        grid.add(logo, 0, 0, 3, 1);
        grid.add(title, 0, 1, 3, 1);
    }

    /**
     * Set all properties for the grid then pass it back to the scene to be
     * displayed.
     * 
     * @return loginGridPane formatted GridPane
     */
    private GridPane loginGridSetup() {
        // Configuration for Grid layout
        GridPane loginGridPane = new GridPane();
        loginGridPane.setHgap(15);
        loginGridPane.setVgap(15);
        loginGridPane.setPadding(new Insets(25, 25, 25, 25));
        loginGridPane.setAlignment(Pos.CENTER);

        setLoginTitleProperties(loginGridPane);

        setLoginInputFields(loginGridPane);

        setLoginSceneButtonProperties(loginGridPane);

        return loginGridPane;
    }

    /**
     * Build scene object to be passed to the application
     * 
     * @return built scene to the application
     */
    private Scene getLoginScene() {
        Scene loginScene = new Scene(loginGridSetup(), 700, 400);
        loginScene.getStylesheets().add("login.css");

        return loginScene;
    }

    /**
     * This method will be utilized to set the configuration of the application
     * scene and display them to the user.
     * 
     */
    private void setLoginSceneProperties() {

        primaryStage.setTitle("Login");
        primaryStage.setScene(getLoginScene());
        primaryStage.show();
    }

    // #endregion

    // #region Search Term: File Methods

    /**
     * Allows for the user to select a data file from their file system and use it
     * within the application. Also sets parameters for the accepted file types then
     * send the selected file to the rest of the application for validation.
     * 
     * @param primaryStage show file dialog for current applications Stage
     */
    private void setFileChooser() {
        // File dialog properties
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File for Login Database");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text File or Data File", "*.txt", "*.dat"));
        setDataFile(fileChooser.showOpenDialog(primaryStage));
    }

    /**
     * Test to see of the user selected a file, if they do not the application will
     * default to its local database of Accounts.dat.
     * 
     * @param file selected file from the users file system
     */
    private void setDataFile(File file) {
        // Ask user for file, if no selection default to local data file
        if (file != null) {
            dataFile = file;
        } else {
            dataFile = new File("Accounts.dat");
        }
        getDataFile();
    }

    /**
     * Check data file exists, if it does not prompt the user for a file selection
     * again
     */
    private void getDataFile() {
        // if File not found error occurs
        try {
            if (!dataFile.exists()) {
                throw new FileNotFoundException(dataFile.getAbsolutePath());
            }
        } catch (FileNotFoundException e1) {
            // Prompt user for a new file
            setFileChooser();
        }
    }

    /**
     * Read in the file as a FileInputStream, then use an ObjectInputStream to
     * extract the objects from the file. Pass the objects to be deserialized so
     * that the file data can be accessed as an Accounts object.
     * 
     * @param file receive a file path from user selection
     * @throws FileNotFoundException
     */
    private void readFileData(String file) {
        // Initialize local variables
        Accounts account = null;
        boolean match = false;

        if (validatePositiveID()) {
            // Create the stream objects from the file
            try (FileInputStream inStream = new FileInputStream(file)) {

                ObjectInputStream objectInputStream = new ObjectInputStream(inStream);
                // Read tot eh end of the file
                while (inStream.available() > 0) {
                    account = Accounts.deserializeData(objectInputStream);

                    // Search the entire file for a matching Account
                    match = getAccountRecord(account, match);
                    // Exit loop if match
                    if (match) {
                        break;
                    }

                }
                // Close the input stream
                objectInputStream.close();

                // ensure that the account exists
                validateAccountID(account, match);

            } catch (IOException e) {

                // Backup catch for invalid file just in case
                getDataFile();

            } catch (EmptyAccountIDException e) {

                // Focus the account input box
                accountInput.requestFocus();

            } catch (InvalidAccountNumberException e) {
                // Clear and focus account input
                accountInput.setText("");
                accountInput.requestFocus();
            }
        }
    }

    // #endregion

    // #region Search Term: Validation

    /**
     * This is redundant as the user will be physically unable to input a negative
     * number but if somehow they do this function will catch that improper input.
     * 
     * @return boolean which validates a positive number
     */
    private boolean validatePositiveID() {

        try {
            if (Integer.parseInt(accountInput.getText()) < 0) {
                throw new NegativeAccountIDException(accountInput.getText());
            } else {
                return true;
            }
        } catch (NegativeAccountIDException e) {
            // Clear input then focus
            accountInput.setText("");
            accountInput.requestFocus();
            return false;
        } catch (NumberFormatException e) {
            // Just continue because the account validation will catch and empty account ID
            return true;
        }

    }

    /**
     * External method used to validate if the account entered by the user is an
     * exact match to one located within the data file. This will be called at the
     * end of the loop which will read the data file. If a match was found then it
     * will be validated as true and a match was found this will then proceed to
     * check the password input by the user for the corelating account ID object.
     * 
     * @param account get the account object which correlated to the entered ID
     * @param match   boolean to determine if a match was found within the data file
     * @throws InvalidAccountNumberException if not match then it was an invalid
     *                                       account number, inform the user
     */
    private void validateAccountID(Accounts account, boolean match) throws InvalidAccountNumberException {
        if (!match) {
            // Throw invalid account error
            throw new InvalidAccountNumberException(accountInput.getText());
        } else {
            validatePassword(account);
        }
    }

    /**
     * Next we validate that the password entered by the user is an exact match. If
     * the password is a match then the user will be prompted to answer the security
     * question associated with their account.
     * 
     * @param account used to get password from account object
     * @throws InvalidPasswordException if passwords do not match
     * @throws EmptyPasswordException   if password field is empty
     */
    private void validatePassword(Accounts account) {
        /**
         * This line below is used to show the account username and password just for
         * easier use of testing the application. Just uncomment for the application to
         * print you out username, passwords, and security questions
         */
        // System.out.println(account);
        try {
            if (account.getPassword().equals(passwordInput.getText())) {
                getSecurityQuestion(account);
            } else if (passwordInput.getText().equals("")) {
                // Throw empty password exception
                throw new EmptyPasswordException();
            } else {
                // Throw invalid password
                throw new InvalidPasswordException();
            }
        } catch (InvalidPasswordException e) {
            // Clear password field and focus
            passwordInput.clear();
            passwordInput.requestFocus();
        } catch (EmptyPasswordException e) {
            // Focus password field
            passwordInput.requestFocus();
        }
    }

    /**
     * Ensure user puts in the correct security answer to their security question.
     * If invalid reset the input in the prompt and ask user to try again.
     * 
     * @param account used to get security question, and validate answer
     * @throws InvalidSecurityQAnswerException if security answer is wrong
     * @throws EmptySecurityQAnswerException   if security answer is empty
     */
    private void validateSecurityAns(Accounts account) {
        try {
            if (account.getSecurityA().equals(securityAnswer.getResult())) {
                // show account info to the user
                dataAccount = account;
                setInfoSceneProperties();
            } else if (securityAnswer.getResult().equals("") || securityAnswer.getResult() == null) {
                // Empty security answer
                throw new EmptySecurityQAnswerException();
            } else {
                // invalid security answer
                throw new InvalidSecurityQAnswerException();
            }
        } catch (InvalidSecurityQAnswerException | EmptySecurityQAnswerException e) {
            // Re-prompt for security question answer
            getSecurityQuestion(account);
        }
    }

    // #endregion

    // #region Search Term: Get Methods

    /**
     * Prompt user to answer their security question if they cancel the operation
     * log to the console that they canceled but do not inform the user.
     * 
     * @param account account object associated with the security question
     */
    private void getSecurityQuestion(Accounts account) {
        try {
            securityAnswer.setContentText(account.getSecurityQ());
            securityAnswer.showAndWait();
            validateSecurityAns(account);
        } catch (Exception e) {
            /**
             * This will only fire if the user submits an invalid security question, then on
             * the new prompt they hit cancel it throws a nullPointerException and I am
             * unsure why but decided after bug testing this catches that error
             */
            System.out.println("Security Question prompt canceled by user.");
        }
    }

    /**
     * If account input is empty throw error, else if a match is found in the file
     * return true
     * 
     * @param account account object
     * @param match   bool on if there is a match
     * @return match if match found true else false
     * @throws EmptyAccountIDException empty account input
     */
    private boolean getAccountRecord(Accounts account, boolean match) throws EmptyAccountIDException {
        if (accountInput.getText() == null || (accountInput.getText()).equals("")) {
            throw new EmptyAccountIDException();
        } else if (account.getAcctID() == Integer.parseInt(accountInput.getText())) {
            match = true;
        }
        return match;
    }

    // #endregion

    // #region Search Term: Scene Control

    /**
     * Clear the input form
     *
     */
    private void clearForm() {
        securityAnswer.setResult("");
        accountInput.setText("");
        passwordInput.setText("");
        accountInput.requestFocus();
    }

    /**
     * Clear info from the account info view
     * 
     */
    private void clearDataAccount() {
        // Reset data account
        dataAccount = null;
        // Back to login scene
        setLoginSceneProperties();
    }

    // #endregion

    // #region Search Term: Account Info GUI Setup

    /**
     * Set all properties for the grid then pass it back to the scene to be
     * displayed.
     * 
     * @return infoGridPane formatted GridPane
     * @throws InvalidStatusException
     */
    private GridPane infoGridSetup() throws InvalidStatusException {
        // Configuration for Grid layout
        GridPane infoGridPane = new GridPane();
        infoGridPane.setHgap(15);
        infoGridPane.setVgap(15);
        infoGridPane.setPadding(new Insets(25, 25, 25, 25));
        infoGridPane.setAlignment(Pos.CENTER);

        setAccountDataProperties(infoGridPane);

        setInfoTitleProperties(infoGridPane);

        setInfoSceneButtonProperties(infoGridPane);

        return infoGridPane;
    }

    /**
     * Set the button properties for the buttons within the info scene
     * 
     * @param grid grid object
     */
    private void setInfoSceneButtonProperties(GridPane grid) {
        setLogoutButton(grid);

        setExitButton(grid);
    }

    /**
     * Set the properties and the on click event of the exit button on the info
     * scene
     * 
     * @param grid grid object
     */
    private void setExitButton(GridPane grid) {

        // Exit button properties
        Button exitButton = new Button("Exit");
        HBox exitButtonBounds = new HBox(10);
        exitButtonBounds.setAlignment(Pos.BOTTOM_RIGHT);
        exitButtonBounds.getChildren().add(exitButton);
        grid.add(exitButtonBounds, 1, 7);

        // Exit button action
        exitButton.setOnAction(e -> System.exit(0));
    }

    /**
     * Set the properties of the logout button for the login scene
     * 
     * @param grid grid object
     */
    private void setLogoutButton(GridPane grid) {

        // Logout button properties
        Button logoutButton = new Button("Logout");
        HBox logoutButtonBounds = new HBox(10);
        logoutButtonBounds.setAlignment(Pos.BOTTOM_LEFT);
        logoutButtonBounds.getChildren().add(logoutButton);
        grid.add(logoutButtonBounds, 0, 7);

        // Logout button action
        logoutButton.setOnAction(e -> clearDataAccount());

    }

    /**
     * Set the properties for the account view page
     * 
     * @param grid grid object
     * @throws InvalidStatusException thrown if invalid status was found in file
     */
    private void setAccountDataProperties(GridPane grid) throws InvalidStatusException {
        setBalanceProperties(grid);

        setLimitProperties(grid);

        setStatusProperties(grid);
    }

    /**
     * Set the properties for the account status labels
     * 
     * @param grid
     * @throws InvalidStatusException thrown if invalid status was found in file
     */
    private void setStatusProperties(GridPane grid) throws InvalidStatusException {
        Label accountStatus = new Label();
        accountStatus.setText("Account Status:");
        grid.add(accountStatus, 0, 5);
        Label accountStatusValue = new Label();
        accountStatusValue.setText(dataAccount.getStatusMessage());
        HBox value = new HBox(accountStatusValue);
        value.setAlignment(Pos.BASELINE_RIGHT);
        grid.add(value, 1, 5);
    }

    /**
     * Set the properties for the account limit label and currency output
     * 
     * @param grid grid object
     */
    private void setLimitProperties(GridPane grid) {
        Label accountLimit = new Label();
        accountLimit.setText("Account Limit:");
        grid.add(accountLimit, 0, 4);
        Label accountLimitValue = new Label();
        accountLimitValue.setText(dataAccount.getDollarLimit());
        HBox value = new HBox(accountLimitValue);
        value.setAlignment(Pos.BASELINE_RIGHT);
        grid.add(value, 1, 4);
    }

    /**
     * Set the properties of the balance labe and the currency output
     * 
     * @param grid grid object
     */
    private void setBalanceProperties(GridPane grid) {
        Label accountBalance = new Label();
        accountBalance.setText("Account Balance:");
        grid.add(accountBalance, 0, 3);
        Label accountBalanceValue = new Label();
        accountBalanceValue.setText(dataAccount.getDollarBalance());
        HBox value = new HBox(accountBalanceValue);
        value.setAlignment(Pos.BASELINE_RIGHT);
        grid.add(value, 1, 3);
    }

    /**
     * Setup the scene object to be shown to the user
     * 
     * @return the scene object
     * @throws InvalidStatusException thrown if invalid status was found in file
     */
    private Scene getInfoScene() throws InvalidStatusException {
        Scene accountInfoScene = new Scene(infoGridSetup(), 700, 400);
        accountInfoScene.getStylesheets().add("info.css");

        return accountInfoScene;
    }

    /**
     * This method will be utilized to set the configuration of the info scene and
     * display it to the user.
     * 
     */
    private void setInfoSceneProperties() {
        try {
            primaryStage.setTitle("Account Info");
            primaryStage.setScene(getInfoScene());
            primaryStage.show();
        } catch (InvalidStatusException e) {
            // Go back to login
            clearDataAccount();
        }
    }

    /**
     * Set the title of the GridPane and the logo for the page, then set properties
     * for the values
     * 
     * @param grid current GridPane being modified
     */
    private void setInfoTitleProperties(GridPane grid) {
        // Title for the UI Window
        Label sceneTitle = new Label("Here is your account details");
        ImageView image = new ImageView("logo.png");
        sceneTitle.setFont(Font.font("System", FontWeight.BOLD, 18));
        HBox logo = new HBox(image);
        HBox title = new HBox(sceneTitle);
        title.setAlignment(Pos.CENTER);
        logo.setAlignment(Pos.CENTER);
        grid.add(logo, 0, 0, 2, 1);
        grid.add(title, 0, 1, 2, 1);
    }

    // #endregion

}