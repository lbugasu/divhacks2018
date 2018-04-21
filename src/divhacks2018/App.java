package divhacks2018;

import java.util.*;
import java.lang.String;
import java.io.*;

public class App{
	public static void main(String[]args){
	
		
		String testSet = "MNIST_test.csv";
		String trainSet = "MNIST_train.csv";
		
		Scanner in = new Scanner(System.in);
		
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
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("results.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println("What threshold do you want to analyze the  dataset?");
		double threshold = in.nextInt();

		System.out.println("Testsing Accuracy with selected Threshold:");
		analyzeData(writer, testSet, threshold, results);

	
		boolean exit = true;
		while (exit) {
			System.out.println("Would you like to test a different threshold value?(type \"1\" for this option)\" or proceed to use the training dataset(type \"2\" for this option)");
			int choice = in.nextInt();
			//Reset the data values
			for(int m =0;m<results.length;m++) {
				for(int n=0;n<results[m].length;n++) {
					results[m][n]=0;
				}
			}
			if (choice==1) {
	
				System.out.println("What threshold do you want to analyze the testing dataset?");
				threshold = in.nextInt();

				writer.printf("\nTesting with a threshold of %.2f\n",threshold);
				analyzeData(writer, testSet, threshold, results);
			}
			else if (choice==2) {
				System.out.println("\n\nApplying Binary Classifier to training dataset");
				writer.println("\n\nApplying Binary Classifier to training dataset");
				analyzeData(writer, trainSet, threshold, results);
				exit=false;
			}
		}


		writer.close();
		
		in.close();
	
	}
	public static void analyzeData(PrintWriter w, String dataset, double threshold, int[][] results) {

		
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
		printResults(w, threshold, results);
		
		}
	
	public static void printResults(PrintWriter w, double threshold, int[][] results) {
		//Print out the results
		System.out.printf("With a threshold of %.2f%% here are the results:\n",threshold);
		w.printf("With a threshold of %.2f%% here are the results:\n",threshold);
		System.out.printf("%-7s%-22s%-22s%-12s%-15s\n", "Digit", "Correct Predictions","False predictions","Percentage", "Classification");
		w.printf("%-7s%-22s%-22s%-12s%-15s\n", "Digit", "Correct Predictions","False predictions","Percentage", "Classification");
		for(int i=0;i<results.length;i++) {
			double predictionvalue = (results[i][0]/(results[i][1]*1.0+results[i][0]*1.0))*100;
			String classification = "EASY";
			if(predictionvalue<threshold) {
				classification = "HARD";
			}
			System.out.printf("%-7d%-22d%-22d%-12f%-15s\n", i, results[i][0], results[i][1], predictionvalue,classification);
			w.printf("%-7d%-22d%-22d%-12f%-15s\n", i, results[i][0], results[i][1], predictionvalue,classification);
			}
		}
	}
	
