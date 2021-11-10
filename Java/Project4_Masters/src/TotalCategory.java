/*
	Name:           Ayden Masters
    Date:           Thursday, March 4, 2021 00:45:44
    Exercise:       Project 4 Hurricane Tool
    Class:          COP2552
	File Name:      TotalCategory.java
	Synopsis:		This class will be responsible for displaying the total amount of storms of each category
*/


import java.util.Arrays;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class TotalCategory {

    Hurricane[] hurricanes = new CreateHurricaneObjects().getHurricanesArray();
    // This will never go above 6 because the scale for the category is only to 5
    String[] str = new String[6];

    private void getCategoryCount() {
        // Get the amount of times that a specific category storm occurs, then set the
        // values to a hash map for manipulation
        Arrays.stream(hurricanes).collect(Collectors.groupingBy(hurricaneObj -> hurricaneObj.getCategory()))
                .forEach((occurrence,
                        count) -> str[occurrence] = "Total category " + occurrence + " hurricanes: " + count.size());
    }

    private void displayOutput() {

        // Build out an easy string to add to our dialog message
        StringBuilder builder = new StringBuilder(str.length);
        for (int i = 1; i < str.length; builder.append(str[i++]))
            builder.append("\n");
        JOptionPane.showMessageDialog(
                null, "Major Florida Hurricanes 1950 - 2020\n\n" + "Total Number of Hurricanes listed: "
                        + hurricanes.length + "\n" + builder.toString(),
                "Aggregate Totals by Category", JOptionPane.INFORMATION_MESSAGE);
    }

    public void driver() {
        getCategoryCount();
        displayOutput();
    }

}
