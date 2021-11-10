/*
	Name:           Ayden Masters
    Date:           Thursday, March 4, 2021 00:45:44
    Exercise:       Project 4 Hurricane Tool
    Class:          COP2552
	File Name:      ActiveYear.java
	Synopsis:		This class will be responsible for showing the most active hurricane years
*/

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class ActiveYear {

    Hurricane[] hurricanes = new CreateHurricaneObjects().getHurricanesArray();
    Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    private void getYearCount() {

        // Get the amount of times that a storm entry occurs in a given year, then set
        // the values to a hash map for manipulation
        Arrays.stream(hurricanes).collect(Collectors.groupingBy(hurricaneObj -> hurricaneObj.getYear()))
                .forEach((occurrence, count) -> hashMap.put(occurrence, count.size()));

    }

    private void getLargestValue() {
        // Always ensure we have enough space for the values in the storage array
        String[] tempStr = new String[hashMap.size()];
        // Get Max value from hash map
        int maxVal = Collections.max((hashMap.values()));
        // iterator
        int i = 0;
        // Loop through the hashmap
        for (java.util.Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() == maxVal) {
                tempStr[i] = entry.getKey().toString();
                i++;
            }
        }
        displayOutput(tempStr);
    }

    private void displayOutput(String[] str) {

        // Format the output based on how many years there are tied for named storms
        StringBuilder builder = new StringBuilder(str.length);
        for (int i = 0; i < str.length && str[i] != null; builder.append(str[i++]))
            if (i > 0) {
                builder.append(" and ");
            } else if (i > 1) {
                builder.append(", ");
            }
        JOptionPane.showMessageDialog(null,
                "Major Florida Hurricanes 1950 - 2020\n\n" + "Most active year(s) were " + builder.toString() + "\n"
                        + "totaling at " + Collections.max((hashMap.values())) + " Named Storms.",
                "Aggregate Totals by Category", JOptionPane.INFORMATION_MESSAGE);
    }

    public void driver() {
        getYearCount();
        getLargestValue();
    }

}
