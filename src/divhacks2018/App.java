
package divhacks2018;

import java.io.*;
import java.util.*;

/**
 * This is my application for analyzing a dataset of MNIST
 * @author Laurence Ininda
 *
 */
public class App {
	public static void main (String[]args) throws UnsupportedEncodingException {
		Scanner in = new Scanner(System.in);
		System.out.println("What threshold do you want to analyze the  dataset?");
		int threshold = in.nextInt();
		
		String testSet = "MNIST_test.csv";
		String trainSet = "MNIST_train.csv";
		
		System.out.println("Testing Accuracy with selected Threshold:");
		analyzeData(testSet, threshold);

		System.out.println("\n\nTraining Accuracy with selected Threshold:");
		analyzeData(trainSet, threshold);
	}
	
	/**
	 * This method analyzes the test data set using a given threshold value by the user
	 * @param threshold - A given threshold (As a percentage value) to determine which digits are "EASY" versus which values are "HARD"
	 * 
	 */
	public static void analyzeData(String dataset, int threshold) throws UnsupportedEncodingException {

		File file = new File(dataset);//A new file object to hold the file being analyzed
		
		double digits = 0;//variable to count total digits analyzed
		
		double countEASY = 0;//Variable to count how many digits are "EASY" to identify
		
		double countHARD = 0;//Variable to count how many digits are "HARD" to identify
		
		Scanner in = null;
		
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			//If digit is above threshold
			if ((value/21)*100>threshold) {
				countEASY++;
			}
			//If digit is below threshold
			else {
				countHARD++;
			}
			digits ++;
		}
		
		System.out.printf("With a threshold of %d gives:\n%.2f - easily identifiable digits\n%.2f - ohardly identifiable digits", threshold, (countEASY/digits)*100, (countHARD/digits)*100);
		
	}
}

