package com.eclavijo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtils{
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public static void main(String[] args) {
		
		logger.debug("[MAIN] Current Date : {}", getCurrentDate());
		generateCsvFile("c:\\date.csv");
		System.out.println(getCurrentDate());
		readCsvFile("c:\\date.csv");
		
	}
	
	private static void readCsvFile(String location) {


		String csvFile = location;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// , as separator
				String[] newLine = line.split(cvsSplitBy);

				System.out.println(" Name = " + newLine[0] + " , age= ["
						+ newLine[1] + "]"+" Day of Birth : [" + newLine[2] +"]");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");

	}
		
	

	private static Date getCurrentDate(){
		
		return new Date();
		
	}
	
	private static void generateCsvFile(String location) {

		try {
			FileWriter writer = new FileWriter(location);

			writer.append("Name");
			writer.append(',');
			writer.append("Age");
			writer.append(',');
			writer.append("Date of Birth");
			writer.append('\n');

			writer.append("Esteban");
			writer.append(',');
			writer.append("25");
			writer.append(',');
			writer.append("25-4-1989");
			writer.append('\n');

			

			// generate whatever data you want

			writer.flush();
			writer.close();
			
			System.out.println(" RDY! ");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}