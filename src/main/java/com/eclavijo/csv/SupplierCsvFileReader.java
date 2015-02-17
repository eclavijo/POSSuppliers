package com.eclavijo.csv;

import java.io.FileReader;
import java.io.IOException;
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
	private String filePath = "";
	private final SystemHelper sysHelper = new SystemHelper();

	public SupplierCsvFileReader(String filePath) {
		this.filePath = filePath;
	}

	public String getLocation() {
		return filePath;
	}

	public void setLocation(String location) {
		this.filePath = location;
	}

	public List<SupplierPOJO> getSuppliersList() {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withHeader(FILE_HEADER_MAPPING);
		try {
			List<SupplierPOJO> suppliers = new ArrayList<SupplierPOJO>();
			fileReader = new FileReader(filePath);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();
			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
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

	public long getLastSupplierId() {

		List<SupplierPOJO> suppliers = getSuppliersList();
		long lastId = 0;
		for (SupplierPOJO supplier : suppliers) {
			lastId = supplier.getId();
		}
		return lastId;
	}

	public SupplierPOJO getSupplierById(Long id) {
		List<SupplierPOJO> suppliers = getSuppliersList();
		for (SupplierPOJO supplier : suppliers) {
			if (supplier.getId().equals(id)) {
				return supplier;

			}
		}

		sysHelper.println("Can't find the supplier with the ID :[" + id + "].");

		return null;
	}
}
