/*
* Name:         Ayden Masters 
* Date:         Thursday, April 22, 2021 10:20:48
* Exercise:     Project 7 Intro to Database queries
* Class:        COP2552 
* File Name:    Continent.java
* 
* Synopsis:     This class is responsible for building the continent
                objects that will be displayed in a table view
*/

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Continent {

    // Private variables for the Continent Object
    private SimpleStringProperty continentName;
    private SimpleIntegerProperty countryCount;
    private SimpleIntegerProperty cityCount;
    private SimpleIntegerProperty languageCount;

    // Empty Constructor
    public Continent() {
    }

    // Constructor
    public Continent(String continentName, int countryCount, int cityCount, int languageCount) {
        this.continentName = new SimpleStringProperty(continentName);
        this.countryCount = new SimpleIntegerProperty(countryCount);
        this.cityCount = new SimpleIntegerProperty(cityCount);
        this.languageCount = new SimpleIntegerProperty(languageCount);
    }

    /**
     * Accessor and mutator methods
     */
    public String getContinentName() {
        return continentName.get();
    }

    public void setContinentName(String continentName) {
        this.continentName.set(continentName);
    }

    public int getCountryCount() {
        return this.countryCount.get();
    }

    public void setCountryCount(int countryCount) {
        this.countryCount.set(countryCount);
    }

    public int getCityCount() {
        return this.cityCount.get();
    }

    public void setCityCount(int cityCount) {
        this.cityCount.set(cityCount);
    }

    public int getLanguageCount() {
        return this.languageCount.get();
    }

    public void setLanguageCount(int languageCount) {
        this.languageCount.set(languageCount);
    }

    @Override
    public String toString() {
        return getContinentName() + getCountryCount() + getCityCount() + getLanguageCount();
    }
}