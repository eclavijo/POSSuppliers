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
	private static final SupplierCsvFileReader csvReader = new SupplierCsvFileReader();
	private static final SystemHelper sysHelper = new SystemHelper();

	public static void writeCsvFile(String fileName,
			List<SupplierPOJO> listSuppliers) {

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(fileName);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(FILE_HEADER);
			for (SupplierPOJO supplier : listSuppliers) {
				List supplierDataRecord = new ArrayList();
				supplierDataRecord
						.add(String.valueOf(supplier.getSupplierId()));
				supplierDataRecord.add(supplier.getSupplierName());
				supplierDataRecord.add(supplier.getSupplierAddress());
				supplierDataRecord.add(supplier.getSupplierEmail());
				supplierDataRecord.add(supplier.getSupplierPhone());
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

	public static void writeCsvFileAdd(String fileName, SupplierPOJO newSupplier) {

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(fileName, true);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			List supplierDataRecord = new ArrayList();
			supplierDataRecord.add(String.valueOf(newSupplier.getSupplierId()));
			supplierDataRecord.add(newSupplier.getSupplierName());
			supplierDataRecord.add(newSupplier.getSupplierAddress());
			supplierDataRecord.add(newSupplier.getSupplierEmail());
			supplierDataRecord.add(newSupplier.getSupplierPhone());
			csvFilePrinter.printRecord(supplierDataRecord);
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

	public SupplierPOJO deleteSupplierById(Long id, String csvFilename) {
		List<SupplierPOJO> listSuppliers = csvReader
				.returnSuppliersList(csvFilename);
		SupplierPOJO toDeleteSupplier = new SupplierPOJO();
		for (SupplierPOJO supplier : listSuppliers) {
			if (supplier.getSupplierId().equals(id)) {
				toDeleteSupplier = supplier;
			}
		}
		if (toDeleteSupplier != null) {
			listSuppliers.remove(toDeleteSupplier);
			writeCsvFile(csvFilename, listSuppliers);
			sysHelper.printSupplier(toDeleteSupplier);
			sysHelper.println("\n");
			sysHelper.println("Supplier: [" + id + "] Deleted.");
		} else {
			sysHelper.println("Imposible to Find Supplier: [" + id + "].");
		}
		return toDeleteSupplier;
	}

	public void modifySupplierById(Long id, String csvFilename) {
		List<SupplierPOJO> listSuppliers = csvReader
				.returnSuppliersList(csvFilename);
		SupplierPOJO toUpdateSupplier = new SupplierPOJO();
		boolean finded = false;
		for (SupplierPOJO supplierIndexer : listSuppliers) {
			long suppId = supplierIndexer.getSupplierId();
			if (suppId == id) {
				toUpdateSupplier = supplierIndexer;
				finded = true;
			}
		}
		if (finded) {
			SupplierPOJO newSupplier = toUpdateSupplier;
			newSupplier.setSupplierName(sysHelper
					.readln("\nEnter New Supplier's Name\n"));
			newSupplier.setSupplierAddress(sysHelper
					.readln("\nEnter New Supplier's Address\n"));
			newSupplier.setSupplierEmail(sysHelper
					.readln("\nEnter New Supplier's Email\n"));
			newSupplier.setSupplierPhone(sysHelper
					.readln("\nEnter New Supplier's Phone\n"));
			listSuppliers.set(listSuppliers.indexOf(toUpdateSupplier),
					newSupplier);
			writeCsvFile(csvFilename, listSuppliers);
			sysHelper.println("\n");
			sysHelper.println("****** Suppliers List Updated. ******");
		}
	}
}
