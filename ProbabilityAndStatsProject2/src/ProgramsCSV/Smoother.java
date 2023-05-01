package ProgramsCSV;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class Smoother {
	
	
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
			
			int test = 0;
			
			while((readingLine = reader.readLine()) != null) {
				
				String[] row = readingLine.split(",");
				
				for (int k = 0; k < row.length; k++) {
					
					saltedData.add(row[k].trim());
					
				}
				
				


			}
			System.out.println(saltedData);
			System.out.println(saltedData.size());
			for (int i = 1; i < saltedData.size(); i += 2) {
				
				int mean;
				int total = 0;
				int count = 0;
				
				if (i-(window*2) <= 0) {
					System.out.println("testing: " + test);
					
					for(int j = i-(window*2);j < i+(window*2)+1; j+=2) {
						if (j >= 0) {
							System.out.println(j);
							
							total += Integer.parseInt(saltedData.get(j));
							count++;
							System.out.println("total: " + total);
							System.out.println("count: " + count);
							
						}
						
					}
					System.out.println(saltedData);
					mean = total/count;
					
					saltedData.set(i, String.valueOf(mean));
					count = 0;
					total=0;
					test++;
				}
				
				else if(i+(window*2) > saltedData.size()-1) {
					System.out.println("testing: " + test);
					
					for(int j = i-(window*2);j <= saltedData.size()-1; j+=2) {
						if (j >= 0) {
							System.out.println(j);
							
							total += Integer.parseInt(saltedData.get(j));
							count++;
							System.out.println("total: " + total);
							System.out.println("count: " + count);
							
						}
						
					}
					mean = total/count;

					saltedData.set(i, String.valueOf(mean));
					count = 0;
					total=0;
					test++;
				}
				
				else {
					System.out.println("testing: " + test);
					for (int j = i-(window*2); j< i+(window*2)+1; j+=2) {
						System.out.println(j);
						
						
						total += Integer.parseInt(saltedData.get(j));
						count++;
						System.out.println("total: " + total);
						System.out.println("count: " + count);
						
					}
					
					mean = total/count;

					saltedData.set(i, String.valueOf(mean));
					count = 0;
					total=0;
					test++;
					
				}
				

			}
			System.out.println(saltedData);
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
	
	
	
	public static void main (String[] args) throws IOException{
		
		SmoothGraph(3);
		
	}
	
	
	
	
}