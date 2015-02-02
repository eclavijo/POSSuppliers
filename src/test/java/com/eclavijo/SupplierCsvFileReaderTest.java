package com.eclavijo;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import com.eclavijo.csv.SupplierCsvFileReader;
import com.model.SupplierPOJO;

import junit.framework.TestCase;

public class SupplierCsvFileReaderTest extends TestCase {

	private static final String CSV_FILENAME = "c:\\suppliersCSVlib.csv";
	private static final SupplierCsvFileReader reader = new SupplierCsvFileReader();

	@Test
	public void supplierCsvFileReaderGetListOfSuppliers() throws IOException,
			ParseException {

		List<SupplierPOJO> suppliersList = reader
				.returnSuppliersList(CSV_FILENAME);

	}

	@Test
	public void supplierCsvFileReaderPrintSuppliers() throws IOException,
			ParseException {

		reader.printSuppliersList(CSV_FILENAME);

	}

//	@Test
//	public void supplierCsvFileReaderGetLastSupplierId() throws IOException,
//			ParseException {
//
//		Long id = reader.getLastSupplierId(CSV_FILENAME);
//		assertEquals(id, reader.getLastSupplierId(CSV_FILENAME));
//
//	}

	@Test
	public void supplierCsvFileReaderFindSupplierById() throws IOException,
			ParseException {

		SystemHelper sysHelper= new SystemHelper();
		Long id =(long)sysHelper.readInt("Please enter an ID: ");
		assertEquals(id, reader.findSupplierById(id,CSV_FILENAME));

	}
}
