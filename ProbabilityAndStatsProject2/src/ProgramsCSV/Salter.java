package ProgramsCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Uses the Plotter's csv file to be salted and outputs a csv file of the results.
 * @author ryannguyen
 *
 */
public class Salter {
	
	
	public static void SaltGraph(int range) throws IOException {
		
		
		String file = "/Users/ryannguyen/git/Probability-and-Stats-Project-2/ProbabilityAndStatsProject2/LinearDataGraph.csv";
		BufferedReader reader = null;
		String readingLine;
		int tmpSalting;
		
		try {
		
			File saltedCsv = new File("SaltedLinearGraph.csv");
			PrintWriter out = new PrintWriter(saltedCsv);
			ArrayList<Integer> tempArr = new ArrayList<Integer>();
			reader = new BufferedReader(new FileReader(file));
			Random rand = new Random();
			
			while((readingLine = reader.readLine()) != null) {
			
				String[] row = readingLine.split(",");
				
				
				for (int i = 1; i < row.length; i += 2) {
				
					tmpSalting = Integer.parseInt(row[i].trim()) + rand.nextInt(range*2+1) - range;
					row[i] = String.valueOf(tmpSalting);
					
				}
				
				for(int i = 0; i < row.length; i++) {
				
					tempArr.add(Integer.parseInt(row[i]));
					
				}
				
			}
			for(int j = 0; j < tempArr.size(); j+=2) {
				out.printf("%d, %d\n", tempArr.get(j),tempArr.get(j+1));
			}
			out.close();
		}
		catch(Exception e) {
		
			e.printStackTrace();
			reader.close();
		}
		
	}
	
}
