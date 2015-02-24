package com.eclavijo;

import java.util.List;

import com.eclavijo.dao.CsvSupplierDao;
import com.eclavijo.dao.InterfaceSupplierDao;
import com.eclavijo.dao.MyBatisSupplierDao;
import com.model.SupplierPOJO;

public class SupplierManagerHelper {

	private static final SystemHelper sysHelper = new SystemHelper();
	private static final InterfaceSupplierDao myBatisDao = new MyBatisSupplierDao(
			MyBatisConnectionFactory.getSqlSessionFactory());
	private static final InterfaceSupplierDao csvDao = new CsvSupplierDao();

	void addOnDB() throws Exception {
		sysHelper
				.println("*************** WELCOME *************** \n You have enter to the suplier maintainer.\n");

		SupplierPOJO supplier = new SupplierPOJO(2L,
				sysHelper.readln("\nEnter Supplier's Name\n"),
				sysHelper.readln("\nEnter Supplier's Address\n"),
				sysHelper.readln("\nEnter Supplier's Email\n"),
				sysHelper.readln("\nEnter Supplier's Phone\n"));

		myBatisDao.add(supplier);
	}

	void listOnDB() {
		sysHelper
				.println("*************** Suppliers List *************** \n Showing all suppliers.\n");
		List<SupplierPOJO> suppliers = myBatisDao.getList();
		printSuppliersList(suppliers);
		sysHelper.println("*************** EOF *************** \n\n");
	}

	void findOnDB() {
		sysHelper
				.println("*************** Find Supplier *************** \n Please enter the id of the supplier to show the info.\n");
		Long id = 0L;

		String input = sysHelper.readln("\nEnter Supplier's ID to find.\n");

		try {
			id = Long.parseLong(input);
			SupplierPOJO supplier = myBatisDao.getById(id);
			sysHelper.printSupplier(supplier);
		} catch (NumberFormatException e) {
			sysHelper.println("Wrong Number Format");
		}

	}

	void deleteOnDB() {
		sysHelper
				.println("*************** Delete Supplier *************** \n Please enter the id of the supplier to delete.\n");
		Long id = 0L;
		String input = sysHelper.readln("\nEnter Supplier's ID to delete.\n");
		try {
			id = Long.parseLong(input);
			SupplierPOJO supplier = myBatisDao.getById(id);
			myBatisDao.deleteById(id);
			sysHelper.printSupplier(supplier);
			sysHelper.println(" Deleted.");
		} catch (NumberFormatException e) {
			sysHelper.println("Wrong Number Format");
		}
	}

	void modifyOnDB() {
		sysHelper
				.println("*************** Modify Supplier *************** \n Please enter the id of the supplier to Modify.\n");
		Long id = 0L;
		String input = sysHelper.readln("\nEnter Supplier's ID to modify.\n");
		try {
			id = Long.parseLong(input);
			SupplierPOJO supplier = myBatisDao.getById(id);
			sysHelper.println("\n Supplier's ID to modify.\n");
			sysHelper.printSupplier(supplier);
			String name = sysHelper.readln("\nEnter Supplier's new Name\n");
			String address = sysHelper
					.readln("\nEnter Supplier's new Address\n");
			String email = sysHelper.readln("\nEnter Supplier's new Email\n");
			String phone = sysHelper.readln("\nEnter Supplier's new Phone\n");
			if (name.equals(""))
				name = supplier.getName();
			if (address.equals(""))
				address = supplier.getAddress();
			if (email.equals(""))
				email = supplier.getEmail();
			if (phone.equals(""))
				phone = supplier.getPhone();
			supplier.setName(name);
			supplier.setAddress(address);
			supplier.setEmail(email);
			supplier.setPhone(phone);
			myBatisDao.update(supplier);
		} catch (NumberFormatException e) {
			sysHelper.println("Wrong Number Format");
		}
	}

	void help() {
		sysHelper
				.println("*************** Suppliers Help Info *************** \n Showing Info for Suppliers Options.\n");

		sysHelper
				.println("You can use:\n 'help'   : to get this message again. \n 'list'   : to get a list of all the suppliers on the file.\n 'delete' : to delete a supplier from the file but you need his ID. \n 'find'   : to get all the information about a supplier but you need his ID. \n 'modify' : to change the information on a supplier but you need his ID. ");

	}

	void addOneOnCsv() throws Exception {

		sysHelper
				.println("*************** WELCOME *************** \n You have enter to the suplier maintainer.\n");

		long lastId = csvDao.getLastId();
		long id = lastId + 1;// make it easy to understand
		SupplierPOJO supplier = new SupplierPOJO(id,
				sysHelper.readln("\nEnter Supplier's Name\n"),
				sysHelper.readln("\nEnter Supplier's Address\n"),
				sysHelper.readln("\nEnter Supplier's Email\n"),
				sysHelper.readln("\nEnter Supplier's Phone\n"));

		csvDao.add(supplier);

		sysHelper
				.println("\n--- The following entry was successfully added --- \n");
		sysHelper.printSupplier(supplier);
	}

	void listOnCsv() {

		sysHelper
				.println("*************** Suppliers List *************** \n Showing all suppliers.\n");
		List<SupplierPOJO> suppliers = csvDao.getList();
		printSuppliersList(suppliers);
		sysHelper.println("*************** EOF *************** \n\n");
	}

	void findInCsv() {

		sysHelper
				.println("*************** Find Supplier *************** \n Please enter the id of the supplier to show the info.\n");
		Long id = 0L;
		String received = sysHelper.readln("\nEnter Supplier's ID to find.\n");
		try {
			id = Long.parseLong(received);
			SupplierPOJO supplier = csvDao.getById(id);
			sysHelper.printSupplier(supplier);
		} catch (NumberFormatException e) {
			sysHelper.println("Wrong Number Format");
		}
	}

	void deleteInCsv() {

		sysHelper
				.println("*************** Delete Supplier *************** \n Please enter the id of the supplier to delete.\n");
		Long id = 0L;

		String input = sysHelper.readln("\nEnter Supplier's ID to delete.\n");

		try {
			id = Long.parseLong(input);
			List<SupplierPOJO> listSuppliers = csvDao.getList();

			SupplierPOJO toDeleteSupplier = null;

			for (SupplierPOJO supplier : listSuppliers) {
				if (supplier.getId().equals(id)) {
					toDeleteSupplier = supplier;
				}
			}
			if (toDeleteSupplier != null) {
				listSuppliers.remove(toDeleteSupplier);
				csvDao.add(listSuppliers);
				sysHelper.printSupplier(toDeleteSupplier);
				sysHelper.println("\n" + "Supplier: [" + id + "] Deleted.");
			} else {
				sysHelper.println("Imposible to Find Supplier: [" + id + "].");
			}

		} catch (NumberFormatException e) {
			sysHelper.println("Error:" + e.toString());
		}
	}

	void modifyInCsv() {
		sysHelper
				.println("*************** Modify Supplier *************** \n Please enter the id of the supplier to modify.\n");
		Long id = 0L;
		String input = sysHelper.readln("\nEnter Supplier's ID to modify.\n");
		try {
			id = Long.parseLong(input);

			SupplierPOJO supplier = csvDao.getById(id);

			if (supplier != null) {
				List<SupplierPOJO> listSuppliers = csvDao.getList();
				sysHelper.println("Supplier to Modify: \n");
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
					address = supplier.getAddress();
				if (email.equals(""))
					email = supplier.getEmail();
				if (phone.equals(""))
					phone = supplier.getPhone();

				supplier.setAddress(address);
				supplier.setName(name);
				supplier.setEmail(email);
				supplier.setPhone(phone);

				SupplierPOJO toUpdateSupplier = new SupplierPOJO();

				for (SupplierPOJO supplierIndexer : listSuppliers) {
					if (supplierIndexer.getId() == supplier.getId()) {
						toUpdateSupplier = supplierIndexer;
						
						listSuppliers.set(
								listSuppliers.indexOf(toUpdateSupplier),
								supplier);
					}
				}

				csvDao.add(listSuppliers);
				sysHelper.println("\n");
				sysHelper.println("****** Suppliers List Updated. ******");

			}
		} catch (NumberFormatException e) {
			sysHelper.println("Wrong Number Format");
		}

	}

	public void printSuppliersList(List<SupplierPOJO> suppliers) {

		sysHelper
				.println("[ ID ]	[  Name  ]	[ Address ]	  [ Email ]	  [ Phone ]");
		for (SupplierPOJO supplier : suppliers) {
			sysHelper.println("[" + supplier.getId() + "]["
					+ supplier.getName() + "][" + supplier.getAddress() + "]["
					+ supplier.getEmail() + "][" + supplier.getPhone()
					+ "] \n ");
		}
		sysHelper.println("------------------------------------------------");
	}

	public void printSupplier(SupplierPOJO supplier) {

		sysHelper.println(" ID :[" + supplier.getId() + "] \n Name : ["
				+ supplier.getName() + "]" + "  \n Address: ["
				+ supplier.getAddress() + "]\n Email: [" + supplier.getEmail()
				+ "]\n Phone: [" + supplier.getPhone() + "]. \n   -o-   ");

	}

}
