package com.eclavijo.csv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVPrinter;

import com.model.SupplierPOJO;

public class CsvPrintRecordHelper {
	
	List<String> printRecord(SupplierPOJO supplier,
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
	
}
