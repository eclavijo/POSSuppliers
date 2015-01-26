package com.eclavijo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//para slf4j
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.Locale;

//para log4j
import org.apache.log4j.Logger;

public class DateUtils {

	// para slf4j
	// private static final Logger logger =
	// LoggerFactory.getLogger(DateUtils.class);

	// para log4j
	private static final Logger LOGGER = Logger.getLogger(DateUtils.class);

	public static void main(String[] args) {

		// para slf4j
		// logger.debug("[MAIN] Current Date : {}", getCurrentDate());

		// para log4j
		LOGGER.info("\n [MAIN] Current Date :[" + getCurrentDate() + "]");

		generateCsvFile("c:\\date.csv");
//		System.out.println(getCurrentDate());
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

				System.out.println(" Name :[ " + newLine[0] + "] \n Date of Birth : ["
						+ newLine[1] + "]" + "  \n Age: [" + newLine[2]
						+ "]\n					-o-						");

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

	private static Date getCurrentDate() {

		return new Date();

	}

	private static void generateCsvFile(String location) {

		
		try {
			FileWriter writer = new FileWriter(location);
			DateFormat format = new SimpleDateFormat("dd-MM-yyyy",
					Locale.ENGLISH);
			Date userBdate =new Date();
			
			writer.append("Felipe");
			writer.append(',');
			writer.append("12-08-1994");
			userBdate= format.parse("12-08-1994");// Calculating age
			writer.append(',');
			writer.append(String.valueOf(ageCalculator(userBdate.getYear(), userBdate.getMonth(),
					userBdate.getDay(), getCurrentDate().getYear(),
					getCurrentDate().getMonth(), getCurrentDate().getDay())));
			writer.append('\n');

			writer.append("Esteban");
			writer.append(',');
			writer.append("25-4-1989");
			writer.append(',');
			userBdate= format.parse("25-4-1989");// Calculating age
			writer.append(String.valueOf(ageCalculator(userBdate.getYear(), userBdate.getMonth(),
					userBdate.getDay(), getCurrentDate().getYear(),
					getCurrentDate().getMonth(), getCurrentDate().getDay())));
			writer.append('\n');


			writer.flush();
			writer.close();

			System.out.println(" RDY! ");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	 static int ageCalculator(int birthYear, int birthMonth,
			int birthDay, int curYear, int curMonth, int curDay) {
		String answer;
		int current = curYear * 10000 + curMonth * 100 + curDay;
		int birth = birthYear * 10000 + birthMonth * 100 + birthDay;
		answer = Integer.toString(current - birth);
		answer = answer.substring(0, 2);
		return (Integer.parseInt(answer));
	}

}