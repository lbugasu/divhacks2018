package divhacks2018;

import java.io.*;
import java.util.*;

public class App {
	public static void main (String[]args) {
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
			
			System.out.println("File Not found!!");
		}
	}
}
