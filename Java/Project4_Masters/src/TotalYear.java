/*
	Name:           Ayden Masters
    Date:           Thursday, March 4, 2021 00:45:44
    Exercise:       Project 4 Hurricane Tool
    Class:          COP2552
	File Name:      TotalYear.java
	Synopsis:		This class will be responsible for displaying the total amount of storms that occurred in each year
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class TotalYear {

    Hurricane[] hurricanes = new CreateHurricaneObjects().getHurricanesArray();
    Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    private void getYearCount() {
        // Get the amount of times that a storm entry occurs in a given year, then set
        // the values to a hash map for manipulation
        Arrays.stream(hurricanes).collect(Collectors.groupingBy(hurricaneObj -> hurricaneObj.getYear()))
                .forEach((occurrence, count) -> hashMap.put(occurrence, count.size()));
        displayOutput();
    }

    // Display the output in a Jtable for the user
    private void displayOutput() {

        JFrame frame = new JFrame("Aggregate totals by Year");
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(0, 0);
        String[] header = { "Year", "Number of Storms" };

        frame.add(new JScrollPane(table));
        tableModel.setColumnIdentifiers(header);

        table.setModel(tableModel);
        for (java.util.Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            int occurrence = entry.getKey();
            int count = entry.getValue();

            tableModel.addRow(new Object[] { occurrence, count });

        }

        setProperties(frame);
        setEventListener(frame);

    }

    private void setProperties(JFrame frame) {

        // Format the frame
        frame.setSize(300, 300);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    }

    private void setEventListener(JFrame frame) {

        // This was much more difficult to achieve then I had initially imagined.
        // Here is the link to what helped me solve this
        // LINK:
        // https://stackoverflow.com/questions/1341699/how-do-i-make-a-thread-wait-for-jframe-to-close-in-java
        Object pause = new Object();

        Thread eventThread = new Thread() {
            @Override
            public void run() {
                synchronized (pause) {
                    while (frame.isVisible())
                        try {
                            pause.wait();
                        } catch (InterruptedException e) {
                            JOptionPane.showMessageDialog(null, "An error has occurred!", "Error!",
                                    JOptionPane.INFORMATION_MESSAGE);
                            Thread.currentThread().interrupt();
                        }
                }
            }
        };
        eventThread.start();

        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent arg0) {
                synchronized (pause) {
                    frame.setVisible(false);
                    pause.notify();
                }
            }

        });

        try {
            eventThread.join();
        } catch (InterruptedException e) {

            JOptionPane.showMessageDialog(null, "An error has occurred!", "Error!", JOptionPane.INFORMATION_MESSAGE);
            Thread.currentThread().interrupt();
        }

    }

    public void driver() {
        getYearCount();

    }
}
