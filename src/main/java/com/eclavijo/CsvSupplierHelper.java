package com.eclavijo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.eclavijo.SystemHelper;
import com.eclavijo.csv.SupplierCsvFileReader;
import com.eclavijo.dao.SupplierCsvDao;
import com.model.SupplierPOJO;

public class CsvSupplierHelper {
	static SystemHelper sysHelper = new SystemHelper();
	SupplierCsvFileReader csvReader ;
	
	
	
	public CsvSupplierHelper(SupplierCsvFileReader csvReader) {
		this.csvReader = csvReader;
	}


	static void readSupplierCsvFile(String location) {

		String csvFile = location;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

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
	
	
	public void printSuppliersList(List<SupplierPOJO> suppliers) {

		sysHelper.println("[ ID ]	[  Name  ]	[ Address ]	  [ Email ]	  [ Phone ]");
		for (SupplierPOJO supplier : suppliers) {
			sysHelper.println("[" + supplier.getId()
					+ "][" + supplier.getName() 
					+ "][" + supplier.getAddress()
					+ "][" + supplier.getEmail()
					+ "][" + supplier.getPhone()
					+ "] \n ");
		}
		sysHelper.println("------------------------------------------------");
	}

	public SupplierPOJO generateSupplierCsvEntry() throws ParseException,
			IOException {

		// ask for supplier info and Fill a Supplier object
		SupplierCsvDao supplierDao = new SupplierCsvDao();

		long lastId = supplierDao.getLastSupplierId("c:\\suppliers.csv");
		long id = lastId + 1;
		SupplierPOJO supplier = new SupplierPOJO(id,
				sysHelper.readln("\nEnter Supplier's Name\n"),
				sysHelper.readln("\nEnter Supplier's Address\n"),
				sysHelper.readln("\nEnter Supplier's Email\n"),
				sysHelper.readln("\nEnter Supplier's Phone\n"));

		supplierDao.addSupplier("c:\\suppliers.csv", supplier);

		sysHelper
				.println("\n\n------ Thanks for adding supplier's info -------\n\n ");

		return supplier;
	}

	public SupplierCsvDao getSupplierDao() {
		SupplierCsvDao supplierDao = new SupplierCsvDao();
		return supplierDao;
	}

	public void printSupplier(SupplierPOJO supplier) {

		
		sysHelper.println(" ID :[" + supplier.getId() + "] \n Name : ["
				+ supplier.getName() + "]" + "  \n Address: ["
				+ supplier.getAddress() + "]\n Email: ["
				+ supplier.getEmail() + "]\n Phone: ["
				+ supplier.getPhone() + "]. \n   -o-   ");		

	}
}
