
package divhacks2018;

import java.io.*;
import java.util.*;

/**
 * This is my application for analyzing a dataset of MNIST
 * @author Laurence Ininda
 *
 */
public class App {
	public static void main (String[]args) {

	}
	
	/**
	 * This method analyzes the test data set using a given threshold value by the user
	 * @param threshold - A given threshold to determine which digits are "EASY" versus which values are "HARD"
	 * 
	 */
	public void analyzeTestData(int threshold) {
		
		/*
		 * First Analyze the sample dataset for thresholds of above 50%, 60%, 70%, 80% and report the results in a txt file
		 */
		String fname = "MNIST_test.csv";
		File file = new File(fname);
		
		try {
			Scanner in = new Scanner(file);
			
			//Bypass the first line that has labels
			in.nextLine();
			
			
			//Begin Analyzing the dataset
			
			while (in.hasNextLine()) {				
				String line = in.nextLine();
				String [] s = line.split(",");
				int value = 0;
				
				for (int i = 0; i<s.length;i++) {
					int id = 0;
					if (i>0) {
						value += Integer.parseInt(s[i]);
						}//Add the predictions of the current digit
					else {
						id = Integer.parseInt(s[i]);
						}
				}
			}
		}
		catch(FileNotFoundException c){
			System.out.println("File Not found!!");
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

