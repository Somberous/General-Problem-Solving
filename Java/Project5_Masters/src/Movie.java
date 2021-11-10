/*
* Name:         Ayden Masters 
* Date:         Tuesday, March 23, 2021 13:39:09
* Exercise:     Project 5 Movie Genre Sort / Testing Inheritance
* Class:        COP2552 
* File Name:    Movie.java
* 
* Synopsis:     This class will be responsible for the movie objects and the data
                that is needed to create them.
*/

public class Movie extends Production {

    private String movieTitle;
    private String movieYear;
    private String movieGenre;
    private String movieRating;

    /*
     * Constructor for the class
     */
    public Movie(String dir, String comp, String title, String year, String genre, String rating) {

        super(dir, comp);
        movieTitle = title;
        movieYear = year;
        movieGenre = genre;
        movieRating = rating;

    }

    /*
     * Initialize the object with default values
     */
    Movie() {

        movieTitle = "";
        movieYear = "";
        movieGenre = "";
        movieRating = "";

    }

    /*
     * Methods for accessing and retrieving info about the movie
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieYear() {
        return movieYear;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public String getMovieRating() {
        return movieRating;
    }

    public String outMovieFields() {

        return "<tr><td align = 'left'>" + movieTitle + "</td><td align = 'center'>" + movieYear + "</td><td align = 'center'>" + movieRating + "</td></tr>";

    }

    /*
     * Print out the Movie Object
     */
    @Override
    public String toString() {

        return "Director Name: " + directorName + "\n" + "Composer Name: " + composerName + "\n\n" + "Genre: "
                + movieGenre + "\n" + movieTitle + "\t" + movieYear + "\t" + movieRating;
    }

}
