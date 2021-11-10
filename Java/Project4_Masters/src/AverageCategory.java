/*
	Name:           Ayden Masters
    Date:           Thursday, March 4, 2021 00:45:44
    Exercise:       Project 4 Hurricane Tool
    Class:          COP2552
	File Name:      AverageCategory.java
	Synopsis:		This class will be responsible for displaying the average storm categories
*/

import javax.swing.JOptionPane;

public class AverageCategory {

    Hurricane[] hurricanes = new CreateHurricaneObjects().getHurricanesArray();

    private double getTotal() {
        int total = 0;
        for (int i = 0; i < hurricanes.length; i++) {
            total = total + hurricanes[i].getCategory();
        }

        return total;

    }

    private String calculateAverage() {
        double d = getTotal() / hurricanes.length;
        return String.format("The Average Storm Category was: %.1f", d);

    }

    private void displayAverage() {

        JOptionPane.showMessageDialog(null, "Major Florida Hurricanes 1950 - 2020\n\n" + calculateAverage(),
                "Average Storm Category", JOptionPane.INFORMATION_MESSAGE);

    }

    public void driver() {

        displayAverage();

    }

}
