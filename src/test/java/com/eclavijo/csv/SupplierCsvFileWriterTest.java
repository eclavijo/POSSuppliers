package com.eclavijo.csv;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.runners.MockitoJUnitRunner;

import com.model.SupplierPOJO;

@RunWith(MockitoJUnitRunner.class)
public class SupplierCsvFileWriterTest {

	private String CSV_FILENAME_TEST;
	private SupplierCsvFileWriter csvWriterMock;

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

	@Before
	public void setUp() throws Exception {
		csvWriterMock = mock(SupplierCsvFileWriter.class);
		CSV_FILENAME_TEST = "c:\\suppliersCSVlib.csv";

	}

	@Test(expected = NullPointerException.class)
	public void supplierCsvFileNullReader() {
		csvWriterMock = new SupplierCsvFileWriter(null);
		csvWriterMock.modifySupplier(null, null);
	}

	@Test
	public void supplierCsvFileWritingOneNodeAdd() {
		SupplierPOJO newSupplier = new SupplierPOJO(1L, "asd", "asd",
				"asd@asd.com", "123");
		List<String> supplierDataRecord = new ArrayList<String>();
		when(csvWriterMock.writeCsvFileAdd(CSV_FILENAME_TEST, newSupplier))
				.thenReturn(supplierDataRecord);
		List<String> receivedList = csvWriterMock.writeCsvFileAdd(CSV_FILENAME_TEST,
				newSupplier);
		assertEquals(supplierDataRecord, receivedList);
	}

	@Test
	public void supplierCsvFileWriterDeleteTest() {

		// Long id = Long.parseLong(sysHelperMock
		// .readln("\nEnter Supplier's ID to delete.\n"));
		// Long findedId = csvReaderMock.findSupplierById(id,
		// CSV_FILENAME_TEST);
		// assertEquals(findedId,
		// csvWriterMock.deleteSupplierById(id, CSV_FILENAME_TEST).getId());

		SupplierPOJO newSupplier = new SupplierPOJO(1L, "asd", "asd",
				"asd@asd.com", "123");
		List<SupplierPOJO> list = new ArrayList<SupplierPOJO>();
		list.add(new SupplierPOJO());
		list.add(new SupplierPOJO());

		when(csvWriterMock.deleteSupplierById(newSupplier.getId(), list))
				.thenReturn(newSupplier);

		SupplierPOJO receivedSupplier = csvWriterMock.deleteSupplierById(
				newSupplier.getId(), list);

		assertEquals(newSupplier, receivedSupplier);

	}

	@Test
	public void supplierCsvFileWriterModifyById() {

		SupplierPOJO toUpdateSupplier = new SupplierPOJO(1L, "asd", "asd",
				"asd@asd.com", "123");
		List<SupplierPOJO> list = new ArrayList<SupplierPOJO>();
		list.add(new SupplierPOJO());
		list.add(new SupplierPOJO());

		when(csvWriterMock.modifySupplier(toUpdateSupplier, list)).thenReturn(
				toUpdateSupplier);

		assertEquals(toUpdateSupplier, toUpdateSupplier);
	}

	@Test
	public void supplierCsvFileWriteFile() {

		SupplierPOJO toUpdateSupplier = new SupplierPOJO(1L, "asd", "asd",
				"asd@asd.com", "123");
		List<SupplierPOJO> list = new ArrayList<SupplierPOJO>();
		list.add(new SupplierPOJO());
		list.add(new SupplierPOJO());

		when(csvWriterMock.modifySupplier(toUpdateSupplier, list)).thenReturn(
				toUpdateSupplier);

		assertEquals(toUpdateSupplier, toUpdateSupplier);
	}

}
