
package StatsLibrary;

import java.util.ArrayList;
import java.math.BigInteger;
import java.util.Collections;

/**
 * @author ryannguyen
 *
 */
public class StatsLibrary {
	
	
	/**
	 * Calculates the mean from a given ArrayList of integers
	 * @param arr a given array list
	 * @return the mean as a double
	 */
	public double findMean(ArrayList<Integer> arr) {
		
		double total = 0;
		for (int i = 0; i < arr.size();i++) {
			
			total += arr.get(i);
			
		}
		
		return total / arr.size();
		
	}
	
	/**
	 * Finds the median of a given array list
	 * @param arr a given array list
	 * @return the median
	 */
	public double findMedian(ArrayList<Integer> arr) {
		

		double result = 0;
		ArrayList<Integer> tempArr = new ArrayList<Integer>();
		
		
		for (int i = 0; i < arr.size(); i++) {				
			
			tempArr.add(arr.get(i));
		}
		
		//https://www.geeksforgeeks.org/how-to-sort-an-arraylist-in-ascending-order-in-java/
		Collections.sort(tempArr);
		
		if (tempArr.size()%2 == 1) {
			result = tempArr.get(arr.size()/2);
		}
		else
			result = (((tempArr.get(tempArr.size()/2))+(tempArr.get((tempArr.size()/2)-1)))/2.0);
			
			
		return result;
		
	}
	
	
	//Should return mode, but if there are multiple modes
	//program should return null
	
	/**
	 * Finds the mode of the given array list
	 * @param arr a given arraylist
	 * @return the mode
	 */
	public Integer findMode(ArrayList<Integer> arr) {
		
	
		int highCount = 0;
		Integer result = 0;
		
		for (int i = 0; i < arr.size(); i++) {
			
			int count = 0;
			for (int g = 0; g < arr.size(); g++) {
				if (arr.get(i)==arr.get(g)) {
					count++;
					
				}
				
			}
			
			if (count > highCount) {
				highCount = count;
				result = arr.get(i);
			}
			else if (count == highCount & arr.get(i)!= result) {
				result = null;
			}
			
			
			
		}
		
		return result;
		
	}
	
	/**
	 * Calculates the standard deviation of a given array list
	 * @param arr the given array list
	 * @param mean the mean of the array list
	 * @return the standard deviation
	 */
	public double standardDeviation(ArrayList<Integer> arr, double mean) {
		
		double count = 0.0;

		
		for(int i = 0; i < arr.size(); i++) {
			
			count += (Math.pow(arr.get(i)-mean, 2));
			
		}
		
		count = count/arr.size();
		count = Math.sqrt(count);
		
		return count;
		
	}
	
	/**
	 * Calculates the factorial of a number
	 * @param n is the inputed number that we want to find the factorial of
	 * @return the factorial of the number
	 */
	public BigInteger findFactorial (BigInteger n) {
		
		BigInteger count = BigInteger.valueOf(1);
		
		for (int i = 1; i <= n.intValue(); i++) {
			
			count = count.multiply(BigInteger.valueOf(i));
			
		}
		
		return count;
	
	}
	
	/**
	 * Calculates the permutation
	 * @param n the number of elements
	 * @param r how many taken at a time
	 * @return the permutation of n, r 
	 */
	public BigInteger findPermutation(BigInteger n, BigInteger r){
		
		BigInteger total = (findFactorial(n)).divide(findFactorial(n.subtract(r)));
				
		return total;
	}
	
	/**
	 * Calculates the combination
	 * @param n number of elements
	 * @param y how many taken at a time
	 * @return the combination of n, y
	 */
	public BigInteger findCombination(BigInteger n, BigInteger y){
		
		BigInteger total = (findFactorial(n)).divide((findFactorial(y)).multiply(findFactorial(n.subtract(y))));
		
		return total;
		
	}

	/**
	 * Converts an array to a string and tells the user what is in the array and the size.
	 * @param arr the array that wants to be outputted.
	 * @return the array as a String and tells us the size.
	 */
	public String toString(ArrayList<Integer> arr) {
		
		return ("The array is " + arr + " Arr.size(): " + arr.size());
		
	}
	
	/**
	 * Finds the binomial of the inputted variables
	 * @param n is the number of trials
	 * @param y is the number of successes
	 * @param p is the probability of success
	 * @return returns the binomial distribution
	 */
	public double binomialDistribution(int n, int y, double p) {
		
		//Combination
		int combin = findCombination(BigInteger.valueOf(n),BigInteger.valueOf(y)).intValue();

		//p^y
		double pToPower = Math.pow(p, y);

		//(1-p)^(n-y)
		double qToPower = Math.pow((1-p), (n-y));

		
		double total = ((double)combin * pToPower * qToPower);
		return total;
		
		
	}
	
	/**
	 * Finds the geometric distribution of the inputted variables
	 * @param p is the probability of success
	 * @param y is the number of trials up to and including the number of success
	 * @return returns the geometric distribution 
	 */
	public double geometricDistribution(double p, double y) {
		
		double total = (Math.pow((1-p), (y-1))* p);
		return total;
		
		
	}
	
	
	public double hypergeometricDistribution(int y, int r, int n, int N) {
		
		//Combination of (r over y)
		double firstComb = findCombination(BigInteger.valueOf(r),BigInteger.valueOf(y)).doubleValue();
		
		//Combination of (N-r over n-y)
		double secondComb = findCombination(BigInteger.valueOf(N-r),BigInteger.valueOf(n-y)).doubleValue();
		
		//Combination of (N over n)
		double thirdComb = findCombination(BigInteger.valueOf(N),BigInteger.valueOf(n)).doubleValue();
		
		
		double total = (firstComb * secondComb)/thirdComb;
		
		
		return total;
	}
	
	
	/**
	 * Finds the Poisson Distribution of the inputted variables
	 * @param y is the number of occurences
	 * @param lambda the 'rate' at which successes occur in a continuous
	 * approximation to the experiment
	 * @return the probability of mass for a Poisson experiment
	 */
	public double poissonDistribution(double y, int lambda) {
		
		double total = 0.0;
		
		//e^lambda * lambda^y
		double numerator = (Math.pow(Math.E, (lambda*-1)))*(Math.pow(lambda, y));
		// y!
		double denominator = (findFactorial(BigInteger.valueOf(lambda)).doubleValue());
		
		total = numerator * denominator;
		
		return total;
		
	}
	
	/**
	 * Tests all the methods in the class
	 * @return confirms that the testing is finished
	 */
	public String testAll() {
		
		ArrayList<Integer> someNumbers = new ArrayList<>();
		
		

		
		someNumbers.add(5);
		someNumbers.add(2);
		someNumbers.add(2);
		someNumbers.add(20);
		someNumbers.add(4);
		someNumbers.add(10);
		someNumbers.add(3);
		someNumbers.add(17);
		someNumbers.add(8);
		someNumbers.add(8);
		
		
		System.out.println(toString(someNumbers));
		
		System.out.println("Average of input: " + findMean(someNumbers));
	
		System.out.println("The median is: " + findMedian(someNumbers));
		System.out.println("The mode is: " + findMode(someNumbers));
		System.out.println("The standard deviation is: " + standardDeviation(someNumbers, findMean(someNumbers)));
		
		System.out.println("The factorial of 50 is: " + findFactorial(BigInteger.valueOf(50)));
		System.out.println("The permutation is: " + findPermutation(BigInteger.valueOf(2),BigInteger.valueOf(3)));
		System.out.println("The combination is: " + findCombination(BigInteger.valueOf(9),BigInteger.valueOf(7)));
		System.out.println("The binomial distribution is : " + binomialDistribution(5,3,.5));
		System.out.println("The geometric distribution is: " + geometricDistribution(.35,10));
		
		return "Finished Testing";
		
	}
	
	
	
	
}
