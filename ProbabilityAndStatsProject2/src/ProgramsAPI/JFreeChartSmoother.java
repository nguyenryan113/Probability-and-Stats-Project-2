package ProgramsAPI;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Uses JFreeChart to make a smoother class for the equation y=2x+5 and outputs a csv file of the results.
 * @author ryannguyen
 *
 */
public class JFreeChartSmoother extends JFrame{
	
	/**
	 * The constructor for the smoother to create the graph.
	 * @throws IOException
	 */
	public JFreeChartSmoother() throws IOException {
        
		
		JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
	
	/**
	 * Creates the chart panel to have labels and dataset
	 * @return The newly created chart of the XYLine graph
	 * @throws IOException
	 */
    private JPanel createChartPanel() throws IOException {
    	
    	String chartTitle = "X vs Y JFreeChart Smoother";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";
     
        XYDataset dataset = createDataset(3);
     
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);
     
        return new ChartPanel(chart);
        
    }
    
    /**
     * Creates the data set.
     * @param window the range for averaging out to change each value to make it more smooth
     * @return the data set after being smoothed within the window.
     * @throws IOException
     */
    private XYDataset createDataset(int window) throws IOException {
		
    	XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("X vs Y");
        String file = "/Users/ryannguyen/git/Probability-and-Stats-Project-2/ProbabilityAndStatsProject2/JFreeChartSaltedLinearGraph.csv";
		BufferedReader reader = null;
		String readingLine;
		ArrayList<String> saltedData = new ArrayList<String>();

    	
    	
		try {
			
			File smoothedCsv = new File("JFreeChartSmoothedLinearGraph.csv");
			PrintWriter out = new PrintWriter(smoothedCsv);
			ArrayList<Integer> tmpArr = new ArrayList<Integer>();
			reader = new BufferedReader(new FileReader(file));
			Random rand = new Random();
			
			while((readingLine = reader.readLine()) != null) {
				
				String[] row = readingLine.split(",");
				
				for (int k = 0; k < row.length; k++) {
					
					saltedData.add(row[k].trim());
					
				}
				
				


			}
			
			for (int i = 1; i < saltedData.size(); i += 2) {
				
				int mean;
				int total = 0;
				int count = 0;
				
				if (i-(window*2) <= 0) {
					
					
					for(int j = i-(window*2);j < i+(window*2)+1; j+=2) {
						if (j >= 0) {
							
							total += Integer.parseInt(saltedData.get(j));
							count++;
							
							
						}
						
					}
					mean = total/count;
					
					saltedData.set(i, String.valueOf(mean));
					count = 0;
					total=0;
				}
				
				else if(i+(window*2) > saltedData.size()-1) {
					
					
					for(int j = i-(window*2);j <= saltedData.size()-1; j+=2) {
						if (j >= 0) {
							
							total += Integer.parseInt(saltedData.get(j));
							count++;
							
						}
						
					}
					mean = total/count;

					saltedData.set(i, String.valueOf(mean));
					count = 0;
					total=0;
				}
				
				else {
					for (int j = i-(window*2); j< i+(window*2)+1; j+=2) {
						
						total += Integer.parseInt(saltedData.get(j));
						count++;
						
					}
					
					mean = total/count;

					saltedData.set(i, String.valueOf(mean));
					count = 0;
					total=0;
					
					
				}
				

			}
			for(int l = 0; l < saltedData.size(); l+=2) {
			
				out.printf("%d, %d\n", Integer.parseInt(saltedData.get(l)), Integer.parseInt(saltedData.get(l+1)));
				series1.add(Integer.parseInt(saltedData.get(l)), Integer.parseInt(saltedData.get(l+1)));
				
			}
			
			dataset.addSeries(series1);
			out.close();
			return dataset;
		}
		catch(Exception e) {
		
			e.printStackTrace();
			reader.close();
			return dataset;
		}
		
		
        
    }
 
	
}