package com.eclavijo.dao;

import java.util.List;

import com.eclavijo.SystemHelper;
import com.eclavijo.csv.SupplierCsvReader;
import com.eclavijo.csv.SupplierCsvWriter;
import com.model.SupplierPOJO;

public class CsvSupplierDao implements InterfaceSupplierDao {

	private static final String CSV_FILENAME = "c:\\suppliersCSVlib.csv";

	private static final SystemHelper sysHelper = new SystemHelper();

	private static final SupplierCsvReader csvReader = new SupplierCsvReader(
			CSV_FILENAME);
	private static final SupplierCsvWriter csvWriter = new SupplierCsvWriter(
			CSV_FILENAME);

	public SupplierPOJO add(SupplierPOJO supplier) {

		List<String> records = csvWriter.writeCsvFileAdd(supplier);
		if (records != null)
			return supplier;
		else
			return null;
	}

	public List<SupplierPOJO> getList() {

		List<SupplierPOJO> suppliers = csvReader.getList();
		if (suppliers != null) {
			return suppliers;
		} else {
			return null;
		}
	}

	public SupplierPOJO getById(long id) {

		try {
			SupplierPOJO supplier = csvReader.getSupplierById(id);
			if (supplier != null) {
				return supplier;
			} else
				return null;
		} catch (Exception e) {
			sysHelper.println("Cant Find the supplier with id:" + id);
			return null;
		}
	}

	public boolean deleteById(long id) {

		try {
			List<SupplierPOJO> listSuppliers = csvReader.getList();

			SupplierPOJO toDeleteSupplier = null;

			for (SupplierPOJO supplier : listSuppliers) {
				if (supplier.getId().equals(id)) {
					toDeleteSupplier = supplier;
				}
			}
			if (toDeleteSupplier != null) {
				listSuppliers.remove(toDeleteSupplier);
				csvWriter.writeCsvFile(listSuppliers);
				sysHelper.printSupplier(toDeleteSupplier);
				sysHelper.println("\n" + "Supplier: [" + id + "] Deleted.");
				return true;
			} else {
				sysHelper.println("Imposible to Find Supplier: [" + id + "].");
				return false;
			}

		} catch (NumberFormatException e) {
			sysHelper.println("Error:" + e.toString());
			return false;
		}
	}

	public void update(SupplierPOJO supplier) {
		try {

			List<SupplierPOJO> listSuppliers = csvReader.getList();
			sysHelper.println("Supplier to Modify: \n");
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

			supplier.setAddress(address);
			supplier.setName(name);
			supplier.setEmail(email);
			supplier.setPhone(phone);

			SupplierPOJO toUpdateSupplier = new SupplierPOJO();

			for (SupplierPOJO supplierIndexer : listSuppliers) {
				if (supplierIndexer.getId() == supplier.getId()) {
					toUpdateSupplier = supplierIndexer;
					listSuppliers.set(listSuppliers.indexOf(toUpdateSupplier),
							supplier);
				}
			}

			csvWriter.writeCsvFile(listSuppliers);
			sysHelper.println("\n");
			sysHelper.println("****** Suppliers List Updated. ******");

		} catch (Exception e) {
			sysHelper.println("Cant Find Supplier ");
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

	public long getLastId() {

		List<SupplierPOJO> suppliers = csvReader.getList();
		long lastId = 0;
		for (SupplierPOJO supplier : suppliers) {
			lastId = supplier.getId();
		}
		return lastId;
	}

	public List<SupplierPOJO> add(List<SupplierPOJO> listSuppliers) {

		List<SupplierPOJO> resultList = csvWriter.writeCsvFile(listSuppliers);
		if (resultList != null) {
			return resultList;
		} else
			return null;

	}
}
