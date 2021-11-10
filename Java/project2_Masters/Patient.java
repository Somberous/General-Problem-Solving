
/*
    * Name: Ayden Masters 
    * Date: Friday, January 29, 2021 10:27:20
    * Exercise: Project 2
    * Patient class
    * Class: COP2552 
    * File Name: Patient.java
*/

public class Patient {

    // Variable declarations
    private int id;
    private String name;
    private int dob;
    private int year;

    public int getId() {

        return id;

    }

    public String getName() {

        return name;

    }

    public int getDOB() {

        return dob;

    }

    public int getYear() {

        return year;

    }

    // Constructor for the patient object
    public Patient(int id, String name, int dob, int year) {

        this.id = id;
        this.name = name;
        this.dob = dob;
        this.year = year;

    }

    // Method to handle writing the object to a file
    @Override
    public String toString() {
        return id + "\n" + name + "\n" + dob + "\n" + year;
    }

}