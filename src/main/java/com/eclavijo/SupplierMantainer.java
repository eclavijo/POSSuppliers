package com.eclavijo;

//para slf4j
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//para slf4j
// private static final Logger logger =
// LoggerFactory.getLogger(DateUtils.class);

import java.util.List;

//para log4j
import org.apache.log4j.Logger;

import com.eclavijo.csv.SupplierCsvFileReader;
import com.eclavijo.csv.SupplierCsvFileWriter;
import com.model.SupplierPOJO;
import com.eclavijo.dao.SupplierDao;

public class SupplierMantainer {

	private static final String CSV_FILENAME = "c:\\suppliersCSVlib.csv";
	private static final SupplierCsvFileReader csvReader = new SupplierCsvFileReader(
			CSV_FILENAME);
	private static final SupplierCsvFileWriter csvWriter = new SupplierCsvFileWriter(
			CSV_FILENAME);
	private static final SupplierDao supplierDao = new SupplierDao(
			MyBatisConnectionFactory.getSqlSessionFactory());

	private static CsvSupplierHelper csvSupplierHelper = new CsvSupplierHelper(
			csvReader);

	public static void main(String[] args) throws Exception {
		SystemHelper sysHelper = new SystemHelper();

		// para slf4j
		// private static final Logger logger =
		// LoggerFactory.getLogger(DateUtils.class);
		// para log4j

		final Logger LOGGER = Logger.getLogger(DateUtils.class);
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
			Long id = 0L;

			String input = sysHelper.readln("\nEnter Supplier's ID to find.\n");

			try {
				id = Long.parseLong(input);
				SupplierPOJO supplier = supplierDao.getById(id);
				sysHelper.printSupplier(supplier);
			} catch (NumberFormatException e) {
				sysHelper.println("Wrong Number Format");
			}

		}

		if (args[0].equals("deleteDB")) {
			sysHelper
					.println("*************** Delete Supplier *************** \n Please enter the id of the supplier to delete.\n");
			Long id = 0L;
			String input = sysHelper
					.readln("\nEnter Supplier's ID to delete.\n");
			try {
				id = Long.parseLong(input);
				SupplierPOJO supplier = supplierDao.getById(id);
				supplierDao.deleteById(id);
				sysHelper.printSupplier(supplier);
				sysHelper.println(" Deleted.");
			} catch (NumberFormatException e) {
				sysHelper.println("Wrong Number Format");
			}

		}

		if (args[0].equals("modifyDB")) {

			sysHelper
					.println("*************** Modify Supplier *************** \n Please enter the id of the supplier to Modify.\n");
			Long id = 0L;
			String input = sysHelper
					.readln("\nEnter Supplier's ID to modify.\n");
			try {
				id = Long.parseLong(input);
				SupplierPOJO supplier = supplierDao.getById(id);
				sysHelper.println("\n Supplier's ID to modify.\n");
				sysHelper.printSupplier(supplier);
				String name = sysHelper.readln("\nEnter Supplier's new Name\n");
				String address = sysHelper
						.readln("\nEnter Supplier's new Address\n");
				String email = sysHelper
						.readln("\nEnter Supplier's new Email\n");
				String phone = sysHelper
						.readln("\nEnter Supplier's new Phone\n");
				if (name.equals(""))
					name = supplier.getName();
				if (address.equals(""))
					name = supplier.getAddress();
				if (email.equals(""))
					name = supplier.getEmail();
				if (phone.equals(""))
					name = supplier.getPhone();
				supplier.setName(name);
				supplier.setAddress(address);
				supplier.setEmail(email);
				supplier.setPhone(phone);
				supplierDao.update(supplier);
			} catch (NumberFormatException e) {
				sysHelper.println("Wrong Number Format");
			}

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

			long lastId = csvReader.getLastSupplierId();
			long id = lastId + 1;// make it easy to understand
			SupplierPOJO supplier = new SupplierPOJO(id,
					sysHelper.readln("\nEnter Supplier's Name\n"),
					sysHelper.readln("\nEnter Supplier's Address\n"),
					sysHelper.readln("\nEnter Supplier's Email\n"),
					sysHelper.readln("\nEnter Supplier's Phone\n"));

			csvWriter.writeCsvFileAdd(CSV_FILENAME, supplier);

			sysHelper
					.println("\n--- The following entry was successfully added --- \n");
			sysHelper.printSupplier(supplier);

		}
		if (args[0].equals("list")) {

			sysHelper
					.println("*************** Suppliers List *************** \n Showing all suppliers.\n");
			List<SupplierPOJO> suppliers = csvReader.getSuppliersList();
			csvSupplierHelper.printSuppliersList(suppliers);
			sysHelper.println("*************** EOF *************** \n\n");
		}
		if (args[0].equals("find")) {

			sysHelper
					.println("*************** Find Supplier *************** \n Please enter the id of the supplier to show the info.\n");
			Long id = 0L;
			String received = sysHelper
					.readln("\nEnter Supplier's ID to find.\n");
			try {
				id = Long.parseLong(received);
				SupplierPOJO supplier = csvReader.getSupplierById(id);
				sysHelper.printSupplier(supplier);
			} catch (NumberFormatException e) {
				sysHelper.println("Wrong Number Format");
			}

		}
		if (args[0].equals("delete")) {

			sysHelper
					.println("*************** Delete Supplier *************** \n Please enter the id of the supplier to delete.\n");
			Long id = 0L;

			String input = sysHelper
					.readln("\nEnter Supplier's ID to delete.\n");

			try {
				id = Long.parseLong(input);
				List<SupplierPOJO> listSuppliers = csvReader.getSuppliersList();
				SupplierPOJO toDeleteSupplier = csvWriter.deleteSupplierById(
						id, listSuppliers);

				sysHelper.printSupplier(toDeleteSupplier);
				sysHelper.println("\n" + "Supplier: [" + id + "] Deleted.");
			} catch (NumberFormatException e) {
				sysHelper.println("Wrong Number Format");
			}

		}

		if (args[0].equals("modify")) {

			sysHelper
					.println("*************** Modify Supplier *************** \n Please enter the id of the supplier to modify.\n");
			Long id = 0L;
			String input = sysHelper
					.readln("\nEnter Supplier's ID to modify.\n");
			try {
				id = Long.parseLong(input);

				SupplierPOJO supplier = csvReader.getSupplierById(id);

				if (supplier != null) {
					List<SupplierPOJO> listSuppliers = csvReader.getSuppliersList();
					sysHelper.println("Supplier to Modify: \n");
					sysHelper.printSupplier(supplier);

					String name = sysHelper
							.readln("\nEnter Supplier's new Name\n");
					String address = sysHelper
							.readln("\nEnter Supplier's new Address\n");
					String email = sysHelper
							.readln("\nEnter Supplier's new Email\n");
					String phone = sysHelper
							.readln("\nEnter Supplier's new Phone\n");

					if (name.equals(""))
						name = supplier.getName();
					if (address.equals(""))
						address = supplier.getAddress();
					if (email.equals(""))
						email = supplier.getEmail();
					if (phone.equals(""))
						phone = supplier.getPhone();

					csvWriter.modifySupplier(supplier, listSuppliers);
				}
			} catch (NumberFormatException e) {
				sysHelper.println("Wrong Number Format");
			}

		}
		// else {
		// sysHelper
		// .println("\nRecibed :[ "
		// + args[0]
		// + " ] as parameter. Don't know what to do with that shiet! \n ");
		// }

	}
}