package ProgramsCSV;

import java.io.IOException;

public class CSVTester {
	public static void main (String[] args) throws IOException{
		
		Plotter plot = new Plotter();
		Salter salt = new Salter();
		Smoother smooth = new Smoother();
		
		plot.PlotGraph(0, 10, 2, 5);
		salt.SaltGraph(30);
		smooth.SmoothGraph(3);
		
	}
}
