package ProgramsAPI;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.SwingUtilities;

public class JFreeChartTester {

	
	
	
	/**
	 * Opens each chart all at once and creats a csv file for each.
	 * @param args
	 */
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                	
                	new JFreeChartPlotter().setVisible(true);
                	new JFreeChartSalter().setVisible(true);
					new JFreeChartSmoother().setVisible(true);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
	
}
