package com.eclavijo;

//para slf4j
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//para slf4j
// private static final Logger logger =
// LoggerFactory.getLogger(DateUtils.class);

import java.io.IOException;
import java.text.ParseException;

import java.util.List;

//para log4j
import org.apache.log4j.Logger;

import com.eclavijo.csv.SupplierCsvFileReader;
import com.eclavijo.csv.SupplierCsvFileWriter;
import com.model.SupplierPOJO;
import com.eclavijo.dao.SupplierDao;

public class SupplierMantainer {

	private static final String CSV_FILENAME = "c:\\suppliersCSVlib.csv";
	private static final SupplierCsvFileReader csvReader = new SupplierCsvFileReader();
	private static final SupplierCsvFileWriter csvWriter = new SupplierCsvFileWriter();
	private static final SupplierDao supplierDao = new SupplierDao();

	public static void main(String[] args) throws Exception {
		SystemHelper sysHelper = new SystemHelper();

		// para slf4j
		// private static final Logger logger =
		// LoggerFactory.getLogger(DateUtils.class);
		// para log4j

		final Logger LOGGER = Logger.getLogger(DateUtils.class);
		CsvSupplierHelper supplierCsvHelper = new CsvSupplierHelper();
		DateUtils dateUtils = new DateUtils();

		if (args[0].equals("addDB")) {
			sysHelper
					.println("*************** WELCOME *************** \n You have enter to the suplier maintainer.\n");

			SupplierPOJO supplier = new SupplierPOJO(2L,
					sysHelper.readln("\nEnter Supplier's Name\n"),
					sysHelper.readln("\nEnter Supplier's Address\n"),
					sysHelper.readln("\nEnter Supplier's Email\n"),
					sysHelper.readln("\nEnter Supplier's Phone\n"));

			supplierDao.insertSupplier(supplier);
		}

		if (args[0].equals("listDB")) {
			sysHelper
					.println("*************** Suppliers List *************** \n Showing all suppliers.\n");
			List<SupplierPOJO> suppliers = supplierDao.getList();
			for (SupplierPOJO supplier : suppliers) {
				sysHelper.printSupplier(supplier);
			}
		}

		if (args[0].equals("findDB")) {

			sysHelper
					.println("*************** Find Supplier *************** \n Please enter the id of the supplier to show the info.\n");
			Long id = Long.parseLong(sysHelper
					.readln("\nEnter Supplier's ID to find.\n"));
			SupplierPOJO supplier = supplierDao.getById(id);
			sysHelper.printSupplier(supplier);
		}

		if (args[0].equals("deleteDB")) {
			sysHelper
					.println("*************** Delete Supplier *************** \n Please enter the id of the supplier to delete.\n");
			Long id = Long.parseLong(sysHelper
					.readln("\nEnter Supplier's ID to delete.\n"));

			supplierDao.deleteById(id);

		}

		if (args[0].equals("modifyDB")) {

			sysHelper
					.println("*************** Modify Supplier *************** \n Please enter the id of the supplier to Modify.\n");
			Long id = Long.parseLong(sysHelper
					.readln("\nEnter Supplier's ID to delete.\n"));
			SupplierPOJO supplier = supplierDao.getById(id);
			sysHelper.println("\n Supplier's ID to modify.\n");
			sysHelper.printSupplier(supplier);
			supplier.setSupplierName(sysHelper
					.readln("\nEnter Supplier's new Name\n"));
			supplier.setSupplierAddress(sysHelper
					.readln("\nEnter Supplier's new Address\n"));
			supplier.setSupplierEmail(sysHelper
					.readln("\nEnter Supplier's new Email\n"));
			supplier.setSupplierPhone(sysHelper
					.readln("\nEnter Supplier's new Phone\n"));

			supplierDao.update(supplier);

		}

		if (args[0].equals("help")) {
			sysHelper
					.println("*************** Suppliers Help Info *************** \n Showing Info for Suppliers Options.\n");

			sysHelper
					.println("You can use:\n 'help'   : to get this message again. \n 'list'   : to get a list of all the suppliers on the file.\n 'delete' : to delete a supplier from the file but you need his ID. \n 'find'   : to get all the information about a supplier but you need his ID. \n 'modify' : to change the information on a supplier but you need his ID. ");
		}

		if (args[0].equals("add")) {

			sysHelper
					.println("*************** WELCOME *************** \n You have enter to the suplier maintainer.\n");
			LOGGER.info("\n [MAIN] Current Date :["
					+ dateUtils.getCurrentDate() + "]\n");

			SupplierCsvFileWriter writer = new SupplierCsvFileWriter();

			long lastId = csvReader.getLastSupplierId(CSV_FILENAME);
			long id = lastId + 1;
			SupplierPOJO supplier = new SupplierPOJO(id,
					sysHelper.readln("\nEnter Supplier's Name\n"),
					sysHelper.readln("\nEnter Supplier's Address\n"),
					sysHelper.readln("\nEnter Supplier's Email\n"),
					sysHelper.readln("\nEnter Supplier's Phone\n"));

			writer.writeCsvFileAdd(CSV_FILENAME, supplier);

			sysHelper
					.println("\n--- The following entry was successfully added --- \n");
			sysHelper.printSupplier(supplier);

		}
		if (args[0].equals("list")) {

			sysHelper
					.println("*************** Suppliers List *************** \n Showing all suppliers.\n");
			csvReader.printSuppliersList(CSV_FILENAME);
			sysHelper.println("*************** EOF *************** \n\n");
		}
		if (args[0].equals("find")) {

			sysHelper
					.println("*************** Find Supplier *************** \n Please enter the id of the supplier to show the info.\n");
			Long id = Long.parseLong(sysHelper
					.readln("\nEnter Supplier's ID to find.\n"));
			csvReader.findSupplierById(id, CSV_FILENAME);

		}
		if (args[0].equals("delete")) {

			sysHelper
					.println("*************** Delete Supplier *************** \n Please enter the id of the supplier to delete.\n");
			Long id = Long.parseLong(sysHelper
					.readln("\nEnter Supplier's ID to delete.\n"));
			csvWriter.deleteSupplierById(id, CSV_FILENAME);

		}

		if (args[0].equals("modify")) {

			sysHelper
					.println("*************** Modify Supplier *************** \n Please enter the id of the supplier to modify.\n");
			Long id = Long.parseLong(sysHelper
					.readln("\nEnter Supplier's ID to delete.\n"));
			csvWriter.modifySupplierById(id, CSV_FILENAME);

		}
		// else {
		// sysHelper
		// .println("\nRecibed :[ "
		// + args[0]
		// + " ] as parameter. Don't know what to do with that shiet! \n ");
		// }

	}

}