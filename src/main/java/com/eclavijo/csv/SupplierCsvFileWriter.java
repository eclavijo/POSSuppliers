package com.eclavijo.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.eclavijo.SystemHelper;
import com.model.SupplierPOJO;

public class SupplierCsvFileWriter {

	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";
	// CSV file header
	private static final Object[] FILE_HEADER = { "id", "name", "adress",
			"email", "phone" };
	private  static  SupplierCsvFileReader csvReader;
	private static final SystemHelper sysHelper = new SystemHelper();
	
	public SupplierCsvFileWriter(SupplierCsvFileReader csvReader) {
		this.csvReader = csvReader;
	}

	public static void writeCsvFile(
			List<SupplierPOJO> listSuppliers) {

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(csvReader.getLocation());
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(FILE_HEADER);
			for (SupplierPOJO supplier : listSuppliers) {
				List supplierDataRecord = new ArrayList();
				supplierDataRecord.add(String.valueOf(supplier.getId()));
				supplierDataRecord.add(supplier.getName());
				supplierDataRecord.add(supplier.getAddress());
				supplierDataRecord.add(supplier.getEmail());
				supplierDataRecord.add(supplier.getPhone());
				csvFilePrinter.printRecord(supplierDataRecord);
			}

		} catch (Exception e) {
			sysHelper.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				sysHelper
						.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}

	public List writeCsvFileAdd(String fileName, SupplierPOJO newSupplier) {

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(fileName, true);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			List supplierDataRecord = new ArrayList();
			supplierDataRecord.add(String.valueOf(newSupplier.getId()));
			supplierDataRecord.add(newSupplier.getName());
			supplierDataRecord.add(newSupplier.getAddress());
			supplierDataRecord.add(newSupplier.getEmail());
			supplierDataRecord.add(newSupplier.getPhone());
			csvFilePrinter.printRecord(supplierDataRecord);
			return supplierDataRecord;
		} catch (Exception e) {
			sysHelper.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
			return null;
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				sysHelper
						.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}

	public SupplierPOJO deleteSupplierById(Long id, String csvFilename) {
		List<SupplierPOJO> listSuppliers = csvReader
				.returnSuppliersList();
		SupplierPOJO toDeleteSupplier = new SupplierPOJO();
		for (SupplierPOJO supplier : listSuppliers) {
			if (supplier.getId().equals(id)) {
				toDeleteSupplier = supplier;
			}
		}
		if (toDeleteSupplier != null) {
			listSuppliers.remove(toDeleteSupplier);
			writeCsvFile(listSuppliers);
			sysHelper.printSupplier(toDeleteSupplier);
			sysHelper.println("\n" + "Supplier: [" + id + "] Deleted.");
			return toDeleteSupplier;
		} else {
			sysHelper.println("Imposible to Find Supplier: [" + id + "].");
			return null;
		}
		
	}

	public SupplierPOJO modifySupplierById(Long id) {
		List<SupplierPOJO> listSuppliers = csvReader
				.returnSuppliersList();
		SupplierPOJO toUpdateSupplier = new SupplierPOJO();
		boolean finded = false;
		for (SupplierPOJO supplierIndexer : listSuppliers) {
			long suppId = supplierIndexer.getId();
			if (suppId == id) {
				toUpdateSupplier = supplierIndexer;
				finded = true;
			}
		}
		if (finded) {
			SupplierPOJO newSupplier = toUpdateSupplier;
			String name = sysHelper
					.readln("\nEnter Supplier's new Name\n");
			String address = sysHelper
					.readln("\nEnter Supplier's new Address\n");
			String email= sysHelper
					.readln("\nEnter Supplier's new Email\n");
			String phone= sysHelper
					.readln("\nEnter Supplier's new Phone\n");
			if(name.equals(""))name=toUpdateSupplier.getName();
			if(address.equals(""))name=toUpdateSupplier.getAddress();
			if(email.equals(""))name=toUpdateSupplier.getEmail();
			if(phone.equals(""))name=toUpdateSupplier.getPhone();
			newSupplier.setName(name);
			newSupplier.setAddress(address);
			newSupplier.setEmail(email);
			newSupplier.setPhone(phone);
			listSuppliers.set(listSuppliers.indexOf(toUpdateSupplier),
					newSupplier);
			writeCsvFile(listSuppliers);
			sysHelper.println("\n");
			sysHelper.println("****** Suppliers List Updated. ******");
		return newSupplier;
		}
		else return null;
	}
}
