/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    UserInterface.java
* 
* Synopsis:     This class is responsible for building the main application GUI
                and handling what the user sees.
*/

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class UserInterface extends Application {
	// Constants for the GUI
	ContinentQuery continentList = new ContinentQuery();
	TableView<Continent> continentTable = new TableView<>();
	GridPane applicationGridPane = new GridPane();
	ListView<String> ccListView;
	ToggleGroup continentToggle = new ToggleGroup();
	Label ccHeaderLabel = new Label();
	CheckBox displayPopulation = new CheckBox();
	CheckBox displayLifeExpectancy = new CheckBox();
	CheckBox displayCCLanguages = new CheckBox();
	TextArea ccDataTextArea = new TextArea();

	/**
	 * Start function to create the scene that the user will see
	 */
	@Override
	public void start(Stage stage) {

		// Create the pane objects
		HBox logoHBox = setLogoHBox();
		HBox headerHBox = setHeaderHBox();
		HBox continentTableHBox = setContinentTable();
		HBox radioButtonHBox = setRadioButtonHBox();
		VBox checkBoxVBox = setCheckBoxVBox();
		HBox ccListHBox = setCCListHBox();
		HBox ccHeaderHBox = setCCHeaderHBox();
		HBox ccDataTextAreaHBox = setDataTextAreaHBox();

		applicationGridPane.setPadding(new Insets(5, 10, 10, 10));
		applicationGridPane.setAlignment(Pos.CENTER);
		applicationGridPane.setVgap(10);
		applicationGridPane.setHgap(30);

		// Add GUI elements to the grid pane
		applicationGridPane.add(logoHBox, 0, 0, 2, 1);
		applicationGridPane.add(headerHBox, 0, 0, 2, 1);
		applicationGridPane.add(continentTableHBox, 0, 1, 2, 1);
		applicationGridPane.add(radioButtonHBox, 0, 2, 2, 1);
		applicationGridPane.add(ccHeaderHBox, 0, 5, 2, 1);
		applicationGridPane.add(ccListHBox, 0, 6, 2, 1);
		applicationGridPane.add(checkBoxVBox, 0, 7);
		applicationGridPane.add(ccDataTextAreaHBox, 1, 7);

		// Setup the scene for the user to see
		Scene scene = new Scene(applicationGridPane, 1200, 800);
		stage.setTitle("Continent Data Table");
		stage.setScene(scene);
		stage.setResizable(false);
		scene.getStylesheets().add("continent.css");
		stage.show();
	}

	/**
	 * Update the label value of the checkboxes to represent the users selection
	 * 
	 * @param ccSelection currently selected item in the ListView
	 */
	private void updateCheckBoxText(String ccSelection) {
		if (ccSelection != null && !continentToggle.getSelectedToggle().toString().contains("Languages")) {
			displayPopulation.setText("Get the population in " + ccSelection.trim());
			displayLifeExpectancy.setText("Get the Life Expectancy in " + ccSelection.trim());
			displayCCLanguages.setText("Get the languages spoken in " + ccSelection.trim());
			showCheckBoxes();
			if (!continentToggle.getSelectedToggle().toString().contains("Country")) {
				displayLifeExpectancy.setVisible(false);
			}
		} else {
			hideCheckBoxes();
		}

	}

	/**
	 * Switch statement for updating the elements displayed in the list view based
	 * on user selection of a specific radio button
	 * 
	 * @param i references a specific radio button
	 */
	private void updateCCList(int i) {

		switch (i) {
		case 0:
			// City view
			ccListView.setItems(FXCollections.observableList(
					new CityQuery().getCity(continentTable.getSelectionModel().getSelectedItem().getContinentName())));
			ccHeaderLabel.setText("Here are the cities located in "
					+ continentTable.getSelectionModel().getSelectedItem().getContinentName().trim());
			break;
		case 1:
			// Country view
			ccListView.setItems(FXCollections.observableList(new CountryQuery()
					.getCountry(continentTable.getSelectionModel().getSelectedItem().getContinentName())));
			ccHeaderLabel.setText("Here are the countries located in "
					+ continentTable.getSelectionModel().getSelectedItem().getContinentName().trim());
			break;
		case 2:
			// Languages view
			ccListView.setItems(FXCollections.observableList(new ContinentLanguageQuery()
					.getLanguage(continentTable.getSelectionModel().getSelectedItem().getContinentName())));
			ccHeaderLabel.setText("Here are the languages spoken in "
					+ continentTable.getSelectionModel().getSelectedItem().getContinentName().trim());
			break;
		default:
			new Alert(AlertType.ERROR, "Invalid Selection!").showAndWait();
		}
		// Show values
		ccHeaderLabel.setVisible(true);
		ccListView.setVisible(true);

	}

	/**
	 * Hide all check boxes
	 */
	private void hideCheckBoxes() {
		displayPopulation.setVisible(false);
		displayLifeExpectancy.setVisible(false);
		displayCCLanguages.setVisible(false);
		ccDataTextArea.clear();
	}

	/**
	 * Clear all check boxes
	 */
	private void clearCheckBoxes() {
		displayPopulation.setSelected(false);
		displayLifeExpectancy.setSelected(false);
		displayCCLanguages.setSelected(false);
		ccDataTextArea.clear();
	}

	/**
	 * Show all the check boxes
	 */
	private void showCheckBoxes() {
		displayPopulation.setVisible(true);
		displayLifeExpectancy.setVisible(true);
		displayCCLanguages.setVisible(true);
	}

	/**
	 * Add all the listeners to the check boxes
	 */
	private void setCheckBoxListeners() {
		// Population check box listener
		displayPopulation.selectedProperty().addListener((observable, oldSelection, newSelection) -> {
			if (Boolean.TRUE.equals(newSelection)) {
				if (continentToggle.getSelectedToggle().toString().contains("Country")) {
					updateCCData(0);
				} else {
					updateCCData(3);
				}
			}
			if (Boolean.FALSE.equals(newSelection)) {
				updateCCData(5);
			}
		});
		// Languages checkbox listener
		displayCCLanguages.selectedProperty().addListener((observable, oldSelection, newSelection) -> {
			if (Boolean.TRUE.equals(newSelection)) {
				if (continentToggle.getSelectedToggle().toString().contains("Country")) {
					updateCCData(1);
				} else {
					updateCCData(4);
				}
			}
			if (Boolean.FALSE.equals(newSelection)) {
				updateCCData(6);
			}
		});
		// Languages checkbox listener
		displayLifeExpectancy.selectedProperty().addListener((observable, oldSelection, newSelection) -> {
			if (Boolean.TRUE.equals(newSelection)) {
				updateCCData(2);
			}
			if (Boolean.FALSE.equals(newSelection)) {
				updateCCData(7);
			}
		});
	}

	/**
	 * Switch statement for updating the elements displayed in the Text Area based
	 * on user selection of a check box
	 * 
	 * @param i references a specific check box
	 */
	private void updateCCData(int i) {

		switch (i) {
		case 0:
			// Country pop
			ccDataTextArea
					.appendText("The population in " + ccListView.getSelectionModel().getSelectedItem().trim() + " is "
							+ (new CountryPopulationQuery()
									.getPopulation(ccListView.getSelectionModel().getSelectedItem().trim()))
							+ " people.\n");
			break;
		case 1:
			// Country languages
			ccDataTextArea.appendText("Language(s) spoken in " + ccListView.getSelectionModel().getSelectedItem().trim()
					+ " are "
					+ new CountryLanguageQuery().getLanguage(ccListView.getSelectionModel().getSelectedItem().trim())
					+ ".\n");
			break;
		case 2:
			// Life expectancy
			ccDataTextArea.appendText("The Life expectancy in "
					+ ccListView.getSelectionModel().getSelectedItem().trim() + " is " + new LifeExpectancyQuery()
							.getLifeExpectancy(ccListView.getSelectionModel().getSelectedItem().trim())
					+ " years.\n");
			break;
		case 3:
			// City pop
			ccDataTextArea.appendText("The population in " + ccListView.getSelectionModel().getSelectedItem().trim()
					+ " is "
					+ new CityPopulationQuery().getPopulation(ccListView.getSelectionModel().getSelectedItem().trim())
					+ " people.\n");
			break;
		case 4:
			// City Languages
			ccDataTextArea.appendText("Languages spoken in " + ccListView.getSelectionModel().getSelectedItem().trim()
					+ " are "
					+ new CityLanguageQuery().getLanguage(ccListView.getSelectionModel().getSelectedItem().trim())
					+ ".\n");
			break;
		case 5:
			if (ccListView.getSelectionModel().getSelectedItem() != null) {
				// Clear Country population
				ccDataTextArea.setText(ccDataTextArea.getText()
						.replace("The population in " + ccListView.getSelectionModel().getSelectedItem().trim() + " is "
								+ (new CountryPopulationQuery()
										.getPopulation(ccListView.getSelectionModel().getSelectedItem().trim()))
								+ " people.\n", ""));
				// CLear City population
				ccDataTextArea
						.setText(ccDataTextArea.getText().replace(
								"The population in " + ccListView.getSelectionModel().getSelectedItem().trim() + " is "
										+ new CityPopulationQuery().getPopulation(
												ccListView.getSelectionModel().getSelectedItem().trim())
										+ " people.\n",
								""));
			}
			break;
		case 6:
			if (ccListView.getSelectionModel().getSelectedItem() != null) {
				// Clear Country Languages
				ccDataTextArea.setText(ccDataTextArea.getText().replace(
						"Language(s) spoken in " + ccListView.getSelectionModel().getSelectedItem().trim() + " are "
								+ new CountryLanguageQuery()
										.getLanguage(ccListView.getSelectionModel().getSelectedItem().trim())
								+ ".\n",
						""));
				// Clear City Languages
				ccDataTextArea.setText(ccDataTextArea.getText().replace(
						"Languages spoken in " + ccListView.getSelectionModel().getSelectedItem().trim() + " are "
								+ new CityLanguageQuery()
										.getLanguage(ccListView.getSelectionModel().getSelectedItem().trim())
								+ ".\n",
						""));
			}
			break;
		case 7:
			// Clear Life Expectancy
			if (ccListView.getSelectionModel().getSelectedItem() != null) {
				ccDataTextArea
						.setText(ccDataTextArea.getText().replace(
								"The Life expectancy in " + ccListView.getSelectionModel().getSelectedItem().trim()
										+ " is "
										+ new LifeExpectancyQuery().getLifeExpectancy(
												ccListView.getSelectionModel().getSelectedItem().trim())
										+ " years.\n",
								""));
			}
			break;
		default:
			new Alert(AlertType.ERROR, "Invalid Selection!").showAndWait();
		}
		ccDataTextArea.setVisible(true);
	}

	/**
	 * HBox for the data text area
	 * 
	 * @return formatted HBox
	 */
	private HBox setDataTextAreaHBox() {

		ccDataTextArea.setVisible(false);
		ccDataTextArea.setEditable(false);

		HBox dataTextArea = new HBox(15, ccDataTextArea);
		ccDataTextArea.setMaxSize(350, 150);
		dataTextArea.setAlignment(Pos.CENTER_RIGHT);

		return dataTextArea;
	}

	/**
	 * HBox for the List View
	 * 
	 * @return formatted HBox
	 */
	private HBox setCCListHBox() {
		ObservableList<String> ccList = FXCollections.<String>observableArrayList();
		ccListView = new ListView<>(ccList);
		ccListView.setVisible(false);

		HBox ccListHBox = new HBox(15, ccListView);
		ccListHBox.setAlignment(Pos.CENTER);
		ccListHBox.setMaxHeight(140);
		ccListHBox.setMinHeight(140);

		/**
		 * Add the event listeners
		 */
		ccListView.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
			clearCheckBoxes();
			ccDataTextArea.clear();
			ccDataTextArea.setVisible(false);
			updateCheckBoxText(newSelection);
		});

		return ccListHBox;
	}

	/**
	 * HBox for the list view header
	 * 
	 * @return formatted HBox
	 */
	private HBox setCCHeaderHBox() {
		HBox headerHBox = new HBox(15);
		headerHBox.setAlignment(Pos.CENTER);
		headerHBox.getChildren().add(ccHeaderLabel);
		return headerHBox;
	}

	/**
	 * HBox for the header element
	 * 
	 * @return formatted HBox
	 */
	private HBox setHeaderHBox() {
		Label headerLabel = new Label("Continent Data");
		headerLabel.setStyle("-fx-font-size: 24pt;");
		headerLabel.setTextAlignment(TextAlignment.CENTER);
		HBox headerHBox = new HBox(15, headerLabel);
		headerHBox.setAlignment(Pos.BASELINE_CENTER);

		return headerHBox;
	}

	/**
	 * VBox for the check boxes
	 * 
	 * @return formatted VBox
	 */
	private VBox setCheckBoxVBox() {

		VBox checkBoxVBox = new VBox(15, displayPopulation, displayCCLanguages, displayLifeExpectancy);
		checkBoxVBox.setAlignment(Pos.CENTER_LEFT);
		hideCheckBoxes();
		setCheckBoxListeners();

		return checkBoxVBox;
	}

	/**
	 * HBox for the radio buttons
	 * 
	 * @return formatted HBox
	 */
	private HBox setRadioButtonHBox() {

		/**
		 * Define the radio buttons
		 */
		RadioButton displayCities = new RadioButton("Display City names for selected continent");
		RadioButton displayCountry = new RadioButton("Display Country names for selected continent");
		RadioButton displayContinentLanguages = new RadioButton("Display Languages spoken on selected continent");

		/**
		 * Set the button toggle group
		 */
		displayCities.setToggleGroup(continentToggle);
		displayCountry.setToggleGroup(continentToggle);
		displayContinentLanguages.setToggleGroup(continentToggle);

		continentToggle.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
			/**
			 * Actions for each radio button will only execute if there is a selection
			 * within the continent table.
			 */
			if (continentTable.getSelectionModel().getSelectedIndex() != -1) {
				if (newVal == displayCities) {
					updateCCList(0);
				} else if (newVal == displayCountry) {
					updateCCList(1);
				} else if (newVal == displayContinentLanguages) {
					updateCCList(2);
				}
				hideCheckBoxes();
			} else {
				/**
				 * This if statement prevent the error from printing twice due to the value
				 * change initiated within this code block.
				 */
				if (newVal != null) {
					new Alert(AlertType.ERROR, "Please select a continent to view this data!").showAndWait();
					continentToggle.selectToggle(null);
				}
			}

		});

		/**
		 * Add to HBox to keep them grouped and add to the GridPane
		 */
		HBox radioButtonHBox = new HBox(40, displayCities, displayCountry, displayContinentLanguages);
		radioButtonHBox.setAlignment(Pos.BASELINE_CENTER);

		return radioButtonHBox;
	}

	/**
	 * HBox for the continent table view
	 * 
	 * @return formatted HBox
	 */
	private HBox setContinentTable() {

		/**
		 * Get the table contents
		 */
		final ObservableList<Continent> data = FXCollections.observableArrayList(continentList.getContinents());

		/**
		 * Create the columns for the table
		 */
		TableColumn<Continent, String> continentColumn = new TableColumn<>("Continent");
		continentColumn.setCellValueFactory(new PropertyValueFactory<>("continentName"));
		TableColumn<Continent, Integer> countriesColumn = new TableColumn<>("Number of Countries");
		countriesColumn.setCellValueFactory(new PropertyValueFactory<>("countryCount"));
		TableColumn<Continent, Integer> citiesColumn = new TableColumn<>("Number of Cities");
		citiesColumn.setCellValueFactory(new PropertyValueFactory<>("cityCount"));
		TableColumn<Continent, Integer> languagesColumn = new TableColumn<>("Number of Languages Spoken");
		languagesColumn.setCellValueFactory(new PropertyValueFactory<>("languageCount"));

		/**
		 * Add data to the table
		 */
		continentTable.setItems(data);
		continentTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		continentTable.getColumns().add(continentColumn);
		continentTable.getColumns().add(countriesColumn);
		continentTable.getColumns().add(citiesColumn);
		continentTable.getColumns().add(languagesColumn);

		/**
		 * Set size properties for the table
		 */
		continentTable.setMaxSize(950, 170);
		continentTable.setMinSize(950, 170);
		continentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		/**
		 * Create the event listeners for the table
		 */
		continentTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldSelection, newSelection) -> {
					// Clear the radio button selection
					continentToggle.selectToggle(null);
					// Set the view of the Country, City, or language box to hidden
					ccListView.setVisible(false);
					// Set the visibility of the data header to false
					ccHeaderLabel.setVisible(false);
					// Hide the check boxes
					hideCheckBoxes();
					// Hide data
					ccDataTextArea.setVisible(false);
					ccDataTextArea.clear();
				});

		/**
		 * Encapsulate the table within an HBox
		 */
		HBox continentTableHBox = new HBox();
		continentTableHBox.setSpacing(5);
		continentTableHBox.setPadding(new Insets(50, 50, 50, 50));
		continentTableHBox.getChildren().addAll(continentTable);
		continentTableHBox.setAlignment(Pos.TOP_CENTER);

		return continentTableHBox;
	}

	/**
	 * Hbox for the header logo
	 * 
	 * @return formated HBox
	 */
	private HBox setLogoHBox() {
		ImageView image = new ImageView("logo.png");
		HBox logo = new HBox(image);
		logo.setAlignment(Pos.TOP_LEFT);
		image.setFitHeight(100);
		image.setFitWidth(100);
		return logo;
	}

	/**
	 * Main function to start the application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
