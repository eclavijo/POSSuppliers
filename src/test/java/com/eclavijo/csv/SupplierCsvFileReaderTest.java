package com.eclavijo.csv;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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

	private final String CSV_FILENAME = "c:\\suppliersCSVlib.csv";
	private SupplierCsvFileReader csvReaderMock;

	@Before
	public void setUp() throws Exception {

		csvReaderMock = mock(SupplierCsvFileReader.class);

	}

	@Test
	public void supplierCsvFileReaderGetListOfSuppliers() {

		SupplierPOJO testSupplier = new SupplierPOJO(1L, "asd", "asd", "asd",
				"sad");
		List<SupplierPOJO> expectedList = new ArrayList<SupplierPOJO>();
		when(csvReaderMock.returnSuppliersList()).thenReturn(expectedList);

		expectedList.add(testSupplier);
		expectedList.add(testSupplier);
		expectedList.add(testSupplier);

		List<SupplierPOJO> receivedList = csvReaderMock
				.returnSuppliersList();

		assertEquals(expectedList, receivedList);
	}

	@Test(expected = NullPointerException.class)
	public void supplierCsvFileNotFound() {
		csvReaderMock = new SupplierCsvFileReader(null);
		csvReaderMock.returnSuppliersList();
	}
	
	
	
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

	@Test
	public void supplierCsvFileReaderFindSupplierById() throws IOException,
			ParseException {

		SystemHelper sysHelper = new SystemHelper();
		Long id = 13L;
		when(csvReaderMock.findSupplierById(id)).thenReturn(id);

		assertEquals(id, csvReaderMock.findSupplierById(id));

	}
}
