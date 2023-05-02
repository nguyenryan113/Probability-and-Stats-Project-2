package ProgramsCSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Creates the plotter for the equation y=2x+5 and outputs a csv file of the results.
 * @author ryannguyen
 *
 */
public class Plotter {

	
	public static void PlotGraph(int min, int max, int slope, int constant) throws FileNotFoundException {
		
		File csvFile = new File("LinearDataGraph.csv");
		PrintWriter out = new PrintWriter(csvFile);
		
		for(int i = min; i <= max; i++) {
			
			int xValue = i;
			int yValue;
			
			yValue = (xValue * slope) + constant;
			
			out.printf("%d, %d\n", xValue, yValue);
			
		}
		
		out.close();
		
		
	}
	
	public static void main (String[] args) throws FileNotFoundException{
		
		PlotGraph(0, 10, 2, 5);
		
	}
	
}
