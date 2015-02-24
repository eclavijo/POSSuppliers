//package com.eclavijo.dao;
//
//import java.io.IOException;
//import java.text.ParseException;
//import java.util.List;
//
//import org.junit.Test;
//
//import com.eclavijo.CsvSupplierHelper;
//import com.model.SupplierPOJO;
//import com.eclavijo.csv.SupplierCsvFileReader;
//import com.eclavijo.dao.SupplierCsvDao;
//
//import junit.framework.TestCase;
//
//public class SupplierCsvDaoTest extends TestCase {
//	
//	private static final String CSV_FILENAME = "c:\\suppliers.csv";
//	
//	@Test
//	public void supplierCsvDaoTestAdd() throws IOException, ParseException {
//		SupplierCsvFileReader csvReader = new SupplierCsvFileReader(CSV_FILENAME);
//		CsvSupplierHelper helper = new CsvSupplierHelper(csvReader);
//		
//		SupplierPOJO supplier = helper.generateSupplierCsvEntry();
//		SupplierCsvDao supplierDao = new SupplierCsvDao();
//		assertEquals(true, supplierDao.addSupplier(CSV_FILENAME, supplier));
//
//	}
//
//	@Test
//	public void supplierCsvDaoTestDelete() throws IOException, ParseException {
//
//		SupplierCsvDao supplierDao = new SupplierCsvDao();
//		assertEquals(true, supplierDao.deleteSupplier(126, CSV_FILENAME));
//
//	}
//
////	@Test
////	public void supplierCsvDaoTestFindById() throws IOException, ParseException {
////
////		SupplierPOJO supplier = new SupplierPOJO((long) 126, "devio",
////				"la pincoya", "qweqweqwe@qweqwe.com", "123123");
////		SupplierCsvDao supplierDao = new SupplierCsvDao();
////		assertEquals(supplier, supplierDao.getSupplierById(126, CSV_FILENAME));
////
////	}
//
//	@Test
//	public void supplierCsvDaoTestList() throws IOException, ParseException {
//
//		SupplierCsvDao supplierDao = new SupplierCsvDao();
//		List<SupplierPOJO> suppliersList = supplierDao
//				.listSuppliers(CSV_FILENAME);
//		assertEquals(suppliersList, supplierDao.listSuppliers(CSV_FILENAME));
//	}
//
////	@Test
////	public void supplierCsvDaoTestModify() throws IOException, ParseException {
////
////		SupplierCsvDao supplierDao = new SupplierCsvDao();
////		List<SupplierPOJO> suppliersList = supplierDao
////				.listSuppliers(CSV_FILENAME);
////		
////		SupplierPOJO supplier = new SupplierPOJO((long)133,"nameeeee","ass","asasasasas","11111111111111");
////		
////		assertEquals(supplier, supplierDao.modSupplier(133, CSV_FILENAME));
////	}
//
//}
