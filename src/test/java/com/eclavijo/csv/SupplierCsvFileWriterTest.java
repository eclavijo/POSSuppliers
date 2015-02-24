package com.eclavijo.csv;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import com.model.SupplierPOJO;

@RunWith(MockitoJUnitRunner.class)
public class SupplierCsvFileWriterTest {

	FileWriter fileWriter = null;
	CSVPrinter csvFilePrinter = null;
	private String CSV_FILENAME_TEST;
	private SupplierCsvWriter csvWriter;
	private SupplierPOJO supplierMock;
	private ArrayList<SupplierPOJO> supplierList;
	private CSVPrinter csvFilePrinterMock;
	private CSVFormat csvFileFormat;
	private CsvPrintRecordHelper printHelperMock;
	private static final String NEW_LINE_SEPARATOR = "\n";

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		CSV_FILENAME_TEST = "c:\\suppliersCSVlib.csv";
		csvWriter = new SupplierCsvWriter(CSV_FILENAME_TEST);
		supplierMock = mock(SupplierPOJO.class);
		supplierList = new ArrayList<SupplierPOJO>();
		printHelperMock = mock(CsvPrintRecordHelper.class);
		fileWriter = mock(FileWriter.class);
		csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

		supplierList.add(supplierMock);
		supplierList.add(supplierMock);
		supplierList.add(supplierMock);
	}

	@Test
	public void supplierCsvFileWriteFile() {

		SupplierPOJO toUpdateSupplier = new SupplierPOJO(1L, "asd", "asd",
				"asd@asd.com", "123");
		List<SupplierPOJO> list = new ArrayList<SupplierPOJO>();
		list.add(new SupplierPOJO());
		list.add(new SupplierPOJO());
		//
		// when(mock).thenReturn(
		// toUpdateSupplier);

		csvWriter.writeCsvFile(supplierList);

		assertEquals(toUpdateSupplier, toUpdateSupplier);
	}

	@Test(expected = NullPointerException.class)
	public void writeCsvFileWhenSupplierCsvFileWriterIsNull() {
		csvWriter = new SupplierCsvWriter(null);
		csvWriter.writeCsvFile(supplierList);
	}

	@Test(expected = NullPointerException.class)
	public void writeCsvFileAddWhenSupplierCsvFileWriterIsNull() {
		csvWriter = new SupplierCsvWriter(null);
		csvWriter.writeCsvFileAdd(supplierMock);
	}

	@Test(expected = NullPointerException.class)
	public void writeCsvFileWhenSupplierListIsNull() {
		csvWriter.writeCsvFile(null);
	}

	@Test(expected = NullPointerException.class)
	public void writeCsvFileAddWhenSupplierIsNull() {
		csvWriter.writeCsvFileAdd(null);
	}

	@Test
	public void writingOneNodeAddTest() {
		SupplierPOJO newSupplier = new SupplierPOJO(1L, "name", "address",
				"asd@asd.com", "123123123");
		List<String> supplierDataRecord = new ArrayList<String>();
		when(csvWriter.writeCsvFileAdd(newSupplier)).thenReturn(
				supplierDataRecord);
		List<String> receivedList = csvWriter.writeCsvFileAdd(newSupplier);
		assertEquals(supplierDataRecord, receivedList);
		CSVFormat csvFileFormat = CSVFormat.DEFAULT
				.withRecordSeparator(NEW_LINE_SEPARATOR);
	}

	// @Test(expected = NullPointerException.class)
	// public void modifyWhenSupplierIsNull() {
	// csvWriter.modify(null, supplierList);
	// }
	// @Test(expected = NullPointerException.class)
	// public void modifyWhenSupplierListIsNull() {
	// csvWriter.modify(supplierMock, null);
	// }
	//
	// @Test(expected = NullPointerException.class)
	// public void deleteByIdWhenSupplierListIsNull() {
	// csvWriter.deleteById(supplierMock.getId(), null);
	// }

	// @Test
	// public void supplierCsvFileWriterDeleteTest() {
	//
	// Long id = Long.parseLong(sysHelperMock
	// .readln("\nEnter Supplier's ID to delete.\n"));
	// Long findedId = csvReaderMock.findSupplierById(id,
	// CSV_FILENAME_TEST);
	// assertEquals(findedId,
	// csvWriterMock.deleteSupplierById(id, CSV_FILENAME_TEST).getId());

	// SupplierPOJO newSupplier = new SupplierPOJO(1L, "asd", "asd",
	// "asd@asd.com", "123");
	// List<SupplierPOJO> list = new ArrayList<SupplierPOJO>();
	// list.add(new SupplierPOJO());
	// list.add(new SupplierPOJO());
	//
	// when(csvWriter.deleteById(newSupplier.getId(), list)).thenReturn(
	// newSupplier);
	//
	// SupplierPOJO receivedSupplier = csvWriter.deleteById(
	// newSupplier.getId(), list);
	//
	// assertEquals(newSupplier, receivedSupplier);
	//
	// }

	// @Test
	// public void supplierCsvFileWriterModifyById() throws IOException {
	//
	// SupplierPOJO toUpdateSupplier = supplierMock;
	// List<String> supplierDataRecord = null;
	//
	//
	// doReturn(supplierDataRecord).when(printHelperMock).printRecord(
	// supplierMock, csvFilePrinter);
	// assertEquals(toUpdateSupplier, csvWriter.modify(toUpdateSupplier,
	// supplierList));
	// }

	// @Test(expected = NullPointerException.class)
	// public void modifyWhenSupplierCsvFileWriterIsNull() {
	// csvWriter = new SupplierCsvWriter(null);
	// csvWriter.modify(null, null);
	// }
	//
	// @Test(expected = NullPointerException.class)
	// public void deleteByIdWhenSupplierCsvFileWriterIsNull() {
	// csvWriter = new SupplierCsvWriter(null);
	// csvWriter.deleteById(supplierMock.getId(), supplierList);
	// }

}
