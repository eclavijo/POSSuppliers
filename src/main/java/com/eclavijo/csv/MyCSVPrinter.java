package com.eclavijo.csv;

import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class MyCSVPrinter{
	
	private CSVPrinter csvPrinter;
	
	

	public MyCSVPrinter(Appendable out, CSVFormat format) throws IOException {
		csvPrinter = new CSVPrinter(out, format);
	}
	
	public void printRecord(Object... values) throws IOException {
		csvPrinter.printRecords(values);
	}

}
