package com.eclavijo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import com.eclavijo.SystemHelper;
import com.eclavijo.dao.SupplierCsvDao;
import com.model.SupplierPOJO;

public class CsvSupplierHelper {

	static void readSupplierCsvFile(String location) {

		String csvFile = location;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		SystemHelper sysHelper = new SystemHelper();

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// , as separator
				String[] newLine = line.split(cvsSplitBy);
				sysHelper.println(" ID :[" + newLine[0] + "] \n Name : ["
						+ newLine[1] + "]" + "  \n Address: [" + newLine[2]
						+ "]\n Email: [" + newLine[3] + "]\n Phone: ["
						+ newLine[4] + "]. \n   -o-   ");

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		sysHelper.println("Done");

	}

	public SupplierPOJO generateSupplierCsvEntry() throws ParseException,
			IOException {

		// ask for supplier info and Fill a Supplier object
		SupplierCsvDao supplierDao = new SupplierCsvDao();
		SystemHelper sysHelper = new SystemHelper();

		long lastId = supplierDao.getLastSupplierId("c:\\suppliers.csv");
		long id = lastId + 1;
		SupplierPOJO supplier = new SupplierPOJO(id,
				sysHelper.readln("\nEnter Supplier's Name\n"),
				sysHelper.readln("\nEnter Supplier's Address\n"),
				sysHelper.readln("\nEnter Supplier's Email\n"),
				sysHelper.readln("\nEnter Supplier's Phone\n"));

		supplierDao.addSupplier("c:\\suppliers.csv", supplier);

		sysHelper
				.println("\n\n------ Thank for adding supplier's info -------\n\n ");

		return supplier;
	}

	public SupplierCsvDao getSupplierDao() {
		SupplierCsvDao supplierDao = new SupplierCsvDao();
		return supplierDao;
	}

	public void printSupplier(SupplierPOJO supplier) {
		SystemHelper sysHelper = new SystemHelper();

		
		sysHelper.println(" ID :[" + supplier.getSupplierId() + "] \n Name : ["
				+ supplier.getSupplierName() + "]" + "  \n Address: ["
				+ supplier.getSupplierAddress() + "]\n Email: ["
				+ supplier.getSupplierEmail() + "]\n Phone: ["
				+ supplier.getSupplierPhone() + "]. \n   -o-   ");		

	}
}
