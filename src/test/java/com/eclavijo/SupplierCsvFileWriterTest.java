package com.eclavijo;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.eclavijo.csv.SupplierCsvFileReader;
import com.eclavijo.csv.SupplierCsvFileWriter;
import com.eclavijo.dao.SupplierCsvDao;
import com.model.SupplierPOJO;

public class SupplierCsvFileWriterTest extends TestCase {

	private static final SystemHelper sysHelper = new SystemHelper();
	private static final String CSV_FILENAME = "c:\\suppliersCSVlib.csv";
	private static final SupplierCsvFileReader csvReader = new SupplierCsvFileReader();
	private static final SupplierCsvFileWriter csvWriter = new SupplierCsvFileWriter();

	//
	// @Test
	// public void supplierCsvFileWritingTest() throws IOException,
	// ParseException {
	// SupplierCsvFileWriter writer = new SupplierCsvFileWriter();
	// SupplierCsvDao csvOldDao= new SupplierCsvDao();
	// List<SupplierPOJO> suppliersList =
	// csvOldDao.listSuppliers("c:\\suppliers.csv");
	// writer.writeCsvFile(CSV_FILENAME, suppliersList);
	// }
	//

	@Test
	public void supplierCsvFileWritingOneNodeAdd() throws IOException,
			ParseException {
		SupplierCsvFileWriter writer = new SupplierCsvFileWriter();
		long lastId = csvReader.getLastSupplierId(CSV_FILENAME);
		long id = lastId + 1;
		SupplierPOJO supplier = new SupplierPOJO(id,
				sysHelper.readln("\nEnter Supplier's Name\n"),
				sysHelper.readln("\nEnter Supplier's Address\n"),
				sysHelper.readln("\nEnter Supplier's Email\n"),
				sysHelper.readln("\nEnter Supplier's Phone\n"));
		writer.writeCsvFileAdd(CSV_FILENAME, supplier);

	}

	@Test
	public void supplierCsvFileWriterDeleteTest() throws IOException,
			ParseException {

		Long id = Long.parseLong(sysHelper
				.readln("\nEnter Supplier's ID to delete.\n"));
		Long findedId = csvReader.findSupplierById(id, CSV_FILENAME);
		assertEquals(findedId, csvWriter.deleteSupplierById(id, CSV_FILENAME).getSupplierId());

	}
}
