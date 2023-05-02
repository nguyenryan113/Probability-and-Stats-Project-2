package ProgramsAPI;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

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
 * Uses JFreeChart to make a plotter class for the equation y=2x+5 and outputs a csv file of the results.
 * @author ryannguyen
 *
 */
public class JFreeChartPlotter extends JFrame{
	
	
	/**
	 * The constructor for the plotter to create the graph.
	 * @throws IOException
	 */
	public JFreeChartPlotter() throws FileNotFoundException {
        
		
		JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);
 
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
 
	/**
	 * Creates the chart panel to have labels and dataset
	 * @return The newly created chart of the XYLine graph
	 * @throws FileNotFoundException
	 */
    private JPanel createChartPanel() throws FileNotFoundException {
    	
    	String chartTitle = "X vs Y JFreeChart Plotter";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";
     
        XYDataset dataset = createDataset();
     
        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);
     
        return new ChartPanel(chart);
        
    }
    
    /**
     * Creates the new data set
     * @return the data set of the equation: 2x+5
     * @throws FileNotFoundException
     */
    private XYDataset createDataset() throws FileNotFoundException {
		
    	File csvFile = new File("JFreeChartLinearDataGraph.csv");
		PrintWriter out = new PrintWriter(csvFile);
    	XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("X vs Y");
    	
    	for(int i = 0; i <= 10; i++) {
			
			int xValue = i;
			int yValue;
			
			yValue = (xValue * 2) + 5;
			series1.add(i,yValue);
			
			out.printf("%d, %d\n", xValue, yValue);
			
    	}
    	dataset.addSeries(series1);
    	out.close();
    	return dataset;
        
    }
 
    
}
