package divhacks2018;

import java.util.*;
import java.lang.String;
import java.io.*;

public class App{
	public static void main(String[]args){
	
		/*
		 * We will store our data in a two dimensional Array, with the rows representing the digits
		 * and the columns representing the array of results such that say we pick dataset[4], 
		 * the first element in that array, i.e. dataset[4][0] represents the number of times digit 4 was predicted correct,
		 * and dataset[4][1] will represent the number of times digit 4 was hard to identify(predicted wrongly)
		 */
		int[][] results = new int[10][];
		for(int j = 0;j<results.length;j++) {
			results[j] = new int[2];
			for (int k=0;k<2;k++) {
				//results[j][];
			}
		}
		Scanner in = new Scanner(System.in);
		System.out.println("What threshold do you want to analyze the  dataset?");
		int threshold = in.nextInt();
		
		String testSet = "MNIST_test.csv";
		String trainSet = "MNIST_train.csv";
		
		System.out.println("Testing Accuracy with selected Threshold:");
		analyzeData(testSet, threshold, results);

		//System.out.println("\n\nTraining Accuracy with selected Threshold:");
		//analyzeData(trainSet, threshold, results);
		
		//Print out the results
		System.out.printf("With a threshold of %d%% here are the results:\n",threshold);
		System.out.printf("%-7s%-22s%-22s%-12s%-15s\n", "Digit", "Correct Predictions","False predictions","Percentage", "Classification");
		for(int i=0;i<results.length;i++) {
			double predictionvalue = (results[i][0]/(results[i][1]*1.0+results[i][0]*1.0))*100;
			String classification = "EASY";
			if(predictionvalue<threshold) {
				classification = "HARD";
			}
			System.out.printf("%-7d%-22d%-22d%-12f%-15s\n", i, results[i][0], results[i][1], predictionvalue,classification);
		}
		
		in.close();
	
	}
	public static void analyzeData(String dataset, int threshold, int[][] results) {

		
		File file = new File(dataset);//A new file object to hold the file being analyzed
		Scanner in = null;
		
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Bypass the first line that contains the headers
		in.nextLine();

		//Begin Analyzing the dataset
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String [] s = line.split(",");
			int predictionValue = 0;
			//The first index contains the label of the MNIST value
			int id = Integer.parseInt(s[1]);
			
			//See how predictable the digit is by analyzing how the digit performs against the 21 Algorithms
			for (int i = 2; i<s.length;i++) {
				
				predictionValue += Integer.parseInt(s[i]);

			}
			//If digit is above threshold mark the digit predictability as easy otherwise mark as false
			if ((predictionValue/21.0)*100>threshold) {
				results[id][0] = results[id][0]+1;
				}
			else{
				results[id][1] = results[id][1]+1;
				}
			}
		}
	}
	
