package com.eclavijo.csv;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.eclavijo.SystemHelper;
import com.model.SupplierPOJO;

public class SupplierCsvFileReader {

	// CSV file header
	private static final String[] FILE_HEADER_MAPPING = { "id", "name",
			"address", "email", "phone" };
	private final SystemHelper sysHelper = new SystemHelper();

	public List<SupplierPOJO> returnSuppliersList(String fileName) {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withHeader(FILE_HEADER_MAPPING);
		try {
			List<SupplierPOJO> suppliers = new ArrayList<SupplierPOJO>();
			fileReader = new FileReader(fileName);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List csvRecords = csvFileParser.getRecords();
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = (CSVRecord) csvRecords.get(i);
				SupplierPOJO supplier = new SupplierPOJO(Long.valueOf(record
						.get("id")), record.get("name"), record.get("address"),
						record.get("email"), record.get("phone"));
				suppliers.add(supplier);
			}
			return suppliers;
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
			return null;
		} finally {
			try {
				fileReader.close();
				csvFileParser.close();
			} catch (IOException e) {
				System.out
						.println("Error while closing fileReader/csvFileParser !!!");
				e.printStackTrace();
			}
		}
	}

	public long getLastSupplierId(String location) {

		List<SupplierPOJO> suppliers = returnSuppliersList(location);
		long lastId = 0;
		for (SupplierPOJO supplier : suppliers) {
			lastId = supplier.getSupplierId();
		}
		return lastId;
	}

	public void printSuppliersList(String location) {
		List<SupplierPOJO> suppliers = returnSuppliersList(location);
		for (SupplierPOJO supplier : suppliers) {
			sysHelper.println(" ID :[" + supplier.getSupplierId()
					+ "] \n Name : [" + supplier.getSupplierName() + "]"
					+ "  \n Address: [" + supplier.getSupplierAddress()
					+ "]\n Email: [" + supplier.getSupplierEmail()
					+ "]\n Phone: [" + supplier.getSupplierPhone()
					+ "]. \n   -o-   ");
		}
	}

	public Long findSupplierById(Long id, String csvFilename) {
		List<SupplierPOJO> suppliers = returnSuppliersList(csvFilename);
		boolean finded = false;
		for (SupplierPOJO supplier : suppliers) {
			if (supplier.getSupplierId().equals(id)) {
				sysHelper.println("Supplier Founded for ID :["
						+ supplier.getSupplierId() + "] \n Name : ["
						+ supplier.getSupplierName() + "]" + "  \n Address: ["
						+ supplier.getSupplierAddress() + "]\n Email: ["
						+ supplier.getSupplierEmail() + "]\n Phone: ["
						+ supplier.getSupplierPhone() + "]. \n   -o-   ");
				finded = true;
			}
		}
		if (finded) {
			return id;
		} else {
			sysHelper.println("Can't find the supplier with the ID :[" + id
					+ "].");
			return (long) 0;
		}
	}
}
