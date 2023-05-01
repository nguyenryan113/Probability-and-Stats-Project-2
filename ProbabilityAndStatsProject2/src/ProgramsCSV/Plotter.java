package ProgramsCSV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


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
