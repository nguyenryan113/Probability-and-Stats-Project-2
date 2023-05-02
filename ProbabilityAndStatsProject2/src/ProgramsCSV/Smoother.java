package ProgramsCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Uses the Salter csv file to smooth the data and outputs a csv file of the results.
 * @author ryannguyen
 *
 */
public class Smoother {
	
	/**
	 * Reads the csv of the SaltedLinearGraph and uses a window to average each value within the window to make the graph smoother
	 * @param window the range of averaging around the index
	 * @throws IOException
	 */
	public static void SmoothGraph(int window) throws IOException {
		
		
		String file = "/Users/ryannguyen/git/Probability-and-Stats-Project-2/ProbabilityAndStatsProject2/SaltedLinearGraph.csv";
		BufferedReader reader = null;
		String readingLine;
		ArrayList<String> saltedData = new ArrayList<String>();
		
		try {
		
			File smoothedCsv = new File("SmoothedLinearGraph.csv");
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
				
			}
			
			out.close();
		}
		catch(Exception e) {
		
			e.printStackTrace();
			reader.close();
		}
		
	}
	
}