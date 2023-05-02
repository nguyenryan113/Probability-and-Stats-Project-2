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
 * Uses JFreeChart to make a salter class for the equation y=2x+5 and outputs a csv file of the results.
 * @author ryannguyen
 *
 */
public class JFreeChartSalter extends JFrame{
	
	/**
	 * The constructor for the salter to create the graph.
	 * @throws IOException
	 */
	public JFreeChartSalter() throws IOException {
        
		
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
    	
    	String chartTitle = "X vs Y JFreeChart Salter";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";
     
        XYDataset dataset = createDataset(30);
     
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);
     
        return new ChartPanel(chart);
        
    }
    /**
     * Creates the data set
     * @param range determines how far you are randomly salting the data set.
     * @return the full data set after salting each y value.
     * @throws IOException
     */
    private XYDataset createDataset(int range) throws IOException {
		
    	XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("X vs Y");
        String file = "/Users/ryannguyen/git/Probability-and-Stats-Project-2/ProbabilityAndStatsProject2/JFreeChartLinearDataGraph.csv";
		BufferedReader reader = null;
		String readingLine;
		int tmpSalting;
    	
    	
		try {
			
			File saltedCsv = new File("JFreeChartSaltedLinearGraph.csv");
			PrintWriter out = new PrintWriter(saltedCsv);
			ArrayList<Integer> tmpArr = new ArrayList<Integer>();
			reader = new BufferedReader(new FileReader(file));
			Random rand = new Random();
			
			while((readingLine = reader.readLine()) != null) {
			
				String[] row = readingLine.split(",");
				
				
				for (int i = 1; i < row.length; i += 2) {
				
					tmpSalting = Integer.parseInt(row[i].trim()) + rand.nextInt(range*2+1) - range;
					
					row[i] = String.valueOf(tmpSalting);
					
				}
				
				for(int i = 0; i < row.length; i++) {
				
					tmpArr.add(Integer.parseInt(row[i]));
				}
				
				
				
				
			}
			
			for(int j = 0; j < tmpArr.size(); j+=2) {
				series1.add(tmpArr.get(j),tmpArr.get(j+1));
				out.printf("%d, %d\n", tmpArr.get(j),tmpArr.get(j+1));
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