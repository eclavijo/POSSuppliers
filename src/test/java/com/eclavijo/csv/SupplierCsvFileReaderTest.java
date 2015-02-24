package com.eclavijo.csv;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import com.eclavijo.SystemHelper;
import com.model.SupplierPOJO;

@RunWith(MockitoJUnitRunner.class)
public class SupplierCsvFileReaderTest {

	private final String CSV_FILEPATH = "c:\\suppliersCSVlib.csv";
	private SupplierCsvReader csvReader;
	private String CSV_FILENAME_TEST;
	private SupplierPOJO supplierMock;
	private static final Object[] FILE_HEADER = { "id", "name", "adress",
			"email", "phone" };

	@Before
	public void setUp() throws Exception {

		CSV_FILENAME_TEST = "c:\\suppliersCSVlib.csv";

		FileReader fileReader = mock(FileReader.class);
//		CSVParser csvFileParser = mock(CSVParser.class);
//		CSVFormat csvFileFormat = mock(CSVFormat.class);
		csvReader = new SupplierCsvReader(CSV_FILENAME_TEST);
		supplierMock = mock(SupplierPOJO.class);

	}

	@Test(expected = NullPointerException.class)
	public void getLastIdWhenSupplierCsvFileReaderIsNull() {
		csvReader = new SupplierCsvReader(null);
		csvReader.getLastId();
	}

	@Test(expected = NullPointerException.class)
	public void getListWhenSupplierCsvFileReaderIsNull() {
		csvReader = new SupplierCsvReader(null);
		csvReader.getList();
	}

	@Test(expected = NullPointerException.class)
	public void getSupplierByIdWhenSupplierCsvFileReaderIsNull() {
		csvReader = new SupplierCsvReader(null);
		csvReader.getSupplierById(anyLong());
	}

	@Test(expected = NullPointerException.class)
	public void getSupplierByIdWhenIdParameterIsNull() {
		csvReader.getSupplierById(null);
	}

	@Test
	public void getListSuccessTest() {

		SupplierPOJO testSupplier = new SupplierPOJO(1L, "asd", "asd", "asd",
				"sad");
		List<SupplierPOJO> expectedList = new ArrayList<SupplierPOJO>();
		when(csvReader.getList()).thenReturn(expectedList);

		expectedList.add(testSupplier);
		expectedList.add(testSupplier);
		expectedList.add(testSupplier);

		List<SupplierPOJO> receivedList = csvReader.getList();

		assertEquals(expectedList, receivedList);
	}

//	@Test
//	public void FindByIdSuccessTest() throws IOException, ParseException {
//
//		SupplierPOJO supplier = new SupplierPOJO();
//		when(csvReader.getSupplierById(10L)).thenReturn(supplier);
//
//		assertEquals(supplier.getId(), csvReader.getSupplierById(10L));
//
//	}

	// @Test
	// public void supplierCsvFileReaderPrintSuppliers() throws IOException,
	// ParseException {
	//
	// reader.printSuppliersList(CSV_FILENAME);
	//
	// }

	// @Test
	// public void supplierCsvFileReaderGetLastSupplierId() throws IOException,
	// ParseException {
	//
	// Long id = reader.getLastSupplierId(CSV_FILENAME);
	// assertEquals(id, reader.getLastSupplierId(CSV_FILENAME));
	//
	// }

}
