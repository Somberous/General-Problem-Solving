/*
* Name:         Ayden Masters 
* Date:         Tuesday, March 23, 2021 13:39:09
* Exercise:     Project 5 Movie Genre Sort / Testing Inheritance
* Class:        COP2552 
* File Name:    GenerateMovies.java
* 
* Synopsis:     This class will be responsible for most of the application,
                it will read data from the file and create movie objects.
                From there it will format the lists of objects and display
                the movies to the user in an individual dialog box for each
                genre of movie in the list.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

public class GenerateMovies {

    // Static reference of the movie file name
    private File movieFile = new File("MovieListing.txt");
    // Store Director and Composer
    ArrayList<String> productionTeam = new ArrayList<>();

    // Store for each ArrayList this will allow for easier manipulation of each
    // array list
    ArrayList<ArrayList<Movie>> genreStore = new ArrayList<>();

    // Genre Storage lists
    ArrayList<Movie> scifi = new ArrayList<>();
    ArrayList<Movie> adventure = new ArrayList<>();
    ArrayList<Movie> drama = new ArrayList<>();
    ArrayList<Movie> war = new ArrayList<>();
    ArrayList<Movie> romance = new ArrayList<>();
    ArrayList<Movie> thriller = new ArrayList<>();
    ArrayList<Movie> fantasy = new ArrayList<>();

    /*
     * Read values from the file and store each movie object into the movieInfo
     * array list
     */
    private void readMovieInfo() {

        try (Scanner scanner = new Scanner(movieFile);) {

            // step through the file
            while (scanner.hasNext()) {

                String line = scanner.nextLine();

                String[] tokens = line.split("[,]");

                // If no commas are detected it is a name, so store this name
                if (!line.contains(",")) {

                    productionTeam.add(line);

                }
                // If file format is proper the movie objects will start to be created
                else if (line.contains(",") && tokens.length == 4) {

                    setGenres(new Movie(productionTeam.get(0), productionTeam.get(1), tokens[0], tokens[1], tokens[2],
                            tokens[3]));

                }
                // Invalid data in file, throw custom exception
                else {

                    throw new InvalidMovieData();

                }

            }

        } catch (FileNotFoundException e) {

            System.out.println("Files do not exist in the current directory, please locate the files then try again.");
            System.exit(20);

        } catch (InvalidMovieData e) {

            System.out.println(e);

        }

    }

    /*
     * Receive a movie object from the reader function for movie data sort to proper
     * list based on the genre of the movie.
     * 
     * Once added to the proper genre list the list reference will be added to the
     * genre store ArrayList.
     * 
     * @param movie receive movie object to be sorted
     */
    private void setGenres(Movie movie) {

        switch (movie.getMovieGenre().replaceAll("\\s", "").toLowerCase()) {

        case "scifi":
            scifi.add(movie);
            break;

        case "adventure":
            adventure.add(movie);
            break;

        case "drama":
            drama.add(movie);
            break;

        case "war":
            war.add(movie);
            break;

        case "romance":
            romance.add(movie);
            break;

        case "thriller":
            thriller.add(movie);
            break;

        case "fantasy":
            fantasy.add(movie);
            break;
        default:
            JOptionPane.showMessageDialog(null, "Genre type specified does not exist!", "Error!", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            break;

        }

    }

    /*
     * Order every list stored within the genre store ArrayList
     * 
     * @param movie receive movie object to be sorted
     */
    private void orderGenreLists() {

        for (ArrayList<Movie> movie : genreStore) {

            sortListObject(movie);

        }

    }

    /*
     * Order lists then display the lists to the user
     * 
     * @param movie receive movie object to be sorted
     */
    private void displayGenreLists() {

        for (ArrayList<Movie> movie : genreStore) {

            formatMovieInfo(movie);

        }

    }

    /*
     * Add each genre ArrayList to the Genre Storage list for manipulation
     */
    private void createGenreList() {

        genreStore.add(scifi);
        genreStore.add(adventure);
        genreStore.add(drama);
        genreStore.add(war);
        genreStore.add(romance);
        genreStore.add(thriller);
        genreStore.add(fantasy);

    }

    /*
     * Receive a movie object from the reader function for movie data sort to proper
     * list based on the genre of the movie
     * 
     * @param aList receive ArrayList of Movie objects to be sorted
     */
    private void sortListObject(ArrayList<Movie> aList) {

        aList.sort((Movie m1, Movie m2) -> Integer.compare(Integer.parseInt(m1.getMovieYear().replaceAll("\\s", "")),
                Integer.parseInt(m2.getMovieYear().replaceAll("\\s", ""))));

    }

    /*
     * Format the movie output of a given genre array list, this seems to just be a
     * mess but unsure how I could clean this up, any help would be appreciated.
     * 
     * @param genreArrayList receive ArrayList of Genre ArrayList's to be formatted
     *                       for output
     */
    private void formatMovieInfo(ArrayList<Movie> genreArrayList) {

        // Constants for the JEditorPane
        JEditorPane display = new JEditorPane();
        display.setContentType("text/html");
        display.setEditable(false);

        // Initialize the output variable
        String output = "";

        // Get composer and director data and add to the output
        String productionString = "<HTML><H2>" + "Director: " + genreArrayList.get(0).getDirector() + "</H2>";
        productionString += "\n<H2>" + "Composer: " + genreArrayList.get(0).getComposer() + "</H2>";

        // Get current genre for the display
        String genreString = "\n\n<H2>Genre: " + genreArrayList.get(0).getMovieGenre() + "</H2>\n";

        // Add the headers for the data
        String dataHeaderString = "<table> <tr> <th align = 'left'><H2>Movie Title</H2></th> <th align = 'left'><H2>Year Released</H2></th> <th align = 'left'><H2>Rating</H2></th> </tr>";

        // Concatenate all data
        output += productionString + genreString + dataHeaderString;

        // Loop through all entries in the current genre array list then add to output
        for (int i = 0; i < genreArrayList.size(); i++) {
            String movieInfo = genreArrayList.get(i).outMovieFields();
            output += movieInfo;
        }

        // Display formatted output
        display.setText(output + "</table>");
        JOptionPane.showMessageDialog(null, display, genreArrayList.get(0).getMovieGenre() + " Genre Movies",
                JOptionPane.INFORMATION_MESSAGE);

    }

    /*
     * Control execution of the methods in the class
     */
    public void driver() {

        readMovieInfo();
        createGenreList();
        orderGenreLists();
        displayGenreLists();

    }

}
