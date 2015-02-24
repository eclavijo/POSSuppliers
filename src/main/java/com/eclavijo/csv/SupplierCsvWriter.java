package com.eclavijo.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.eclavijo.SystemHelper;
import com.model.SupplierPOJO;

public class SupplierCsvWriter {

	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";
	// CSV file header
	private static final Object[] FILE_HEADER = { "id", "name", "adress",
			"email", "phone" };
	private String filePath;
	private CsvPrintRecordHelper printHelper = new CsvPrintRecordHelper();
	private static final SystemHelper sysHelper = new SystemHelper();

	public SupplierCsvWriter(String filePath) {
		this.filePath = filePath;
	}

	public List<SupplierPOJO> writeCsvFile(List<SupplierPOJO> listSuppliers) {

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(filePath);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(FILE_HEADER);
			for (SupplierPOJO supplier : listSuppliers) {
				printHelper.printRecord(supplier, csvFilePrinter);
			}
			return listSuppliers;
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


	public List<String> writeCsvFileAdd(SupplierPOJO newSupplier) {

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(filePath, true);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			List<String> supplierDataRecord = printHelper.printRecord(
					newSupplier, csvFilePrinter);
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

//	public SupplierPOJO deleteById(Long id, List<SupplierPOJO> listSuppliers) {
//		SupplierPOJO toDeleteSupplier = null;
//		for (SupplierPOJO supplier : listSuppliers) {
//			if (supplier.getId().equals(id)) {
//				toDeleteSupplier = supplier;
//			}
//		}
//		if (toDeleteSupplier != null) {
//			listSuppliers.remove(toDeleteSupplier);
//			writeCsvFile(listSuppliers);
//			return toDeleteSupplier;
//		}
//		sysHelper.println("Imposible to Find Supplier: [" + id + "].");
//		return null;
//	}
//
//	public SupplierPOJO modify(SupplierPOJO supplier,
//			List<SupplierPOJO> listSuppliers) {
//		SupplierPOJO toUpdateSupplier = new SupplierPOJO();
//
//		for (SupplierPOJO supplierIndexer : listSuppliers) {
//			long suppId = supplierIndexer.getId();
//			if (suppId == supplier.getId()) {
//				toUpdateSupplier = supplierIndexer;
//				listSuppliers.set(listSuppliers.indexOf(toUpdateSupplier),
//						supplier);
//			}
//		}
//
//		writeCsvFile(listSuppliers);
//		sysHelper.println("\n");
//		sysHelper.println("****** Suppliers List Updated. ******");
//
//		return supplier;
//
//	}
}
