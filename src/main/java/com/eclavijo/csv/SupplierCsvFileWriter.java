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
	private String filePath;
	private static final SystemHelper sysHelper = new SystemHelper();

	public SupplierCsvFileWriter(String filePath) {
		this.filePath = filePath;
	}

	public void writeCsvFile(List<SupplierPOJO> listSuppliers) {

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(filePath);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			csvFilePrinter.printRecord(FILE_HEADER);
			for (SupplierPOJO supplier : listSuppliers) {
				//

				printRecord(supplier, csvFilePrinter);
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

	private List<String> printRecord(SupplierPOJO supplier,
			CSVPrinter csvFilePrinter) throws IOException {
		List<String> supplierDataRecord = new ArrayList<String>();
		supplierDataRecord.add(String.valueOf(supplier.getId()));
		supplierDataRecord.add(supplier.getName());
		supplierDataRecord.add(supplier.getAddress());
		supplierDataRecord.add(supplier.getEmail());
		supplierDataRecord.add(supplier.getPhone());
		csvFilePrinter.printRecord(supplierDataRecord);

		return supplierDataRecord;

	}

	public List<String> writeCsvFileAdd(String fileName,
			SupplierPOJO newSupplier) {

		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(fileName, true);
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
			List<String> supplierDataRecord = printRecord(newSupplier,
					csvFilePrinter);
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

	public SupplierPOJO deleteSupplierById(Long id,
			List<SupplierPOJO> listSuppliers) {
		SupplierPOJO toDeleteSupplier = new SupplierPOJO();
		for (SupplierPOJO supplier : listSuppliers) {
			if (supplier.getId().equals(id)) {
				toDeleteSupplier = supplier;
			}
		}
		if (toDeleteSupplier != null) {
			listSuppliers.remove(toDeleteSupplier);
			writeCsvFile(listSuppliers);
			return toDeleteSupplier;
		}
		sysHelper.println("Imposible to Find Supplier: [" + id + "].");
		return null;
	}

	public SupplierPOJO modifySupplier(SupplierPOJO supplier,
			List<SupplierPOJO> listSuppliers) {
		SupplierPOJO toUpdateSupplier = new SupplierPOJO();

		for (SupplierPOJO supplierIndexer : listSuppliers) {
			long suppId = supplierIndexer.getId();
			if (suppId == supplier.getId()) {
				toUpdateSupplier = supplierIndexer;

			}
		}
		
		listSuppliers.set(listSuppliers.indexOf(toUpdateSupplier), supplier);
		writeCsvFile(listSuppliers);
		sysHelper.println("\n");
		sysHelper.println("****** Suppliers List Updated. ******");

		return supplier;

	}
}
