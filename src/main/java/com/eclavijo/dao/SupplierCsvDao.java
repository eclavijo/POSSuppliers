package com.eclavijo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.eclavijo.SystemHelper;
import com.model.SupplierPOJO;

public class SupplierCsvDao {

	public Object getLastSupplierId() {

		return null;
	}

	public boolean addSupplier(String location, SupplierPOJO supplier)
			throws IOException {

		FileWriter writer = new FileWriter(location, true);

		// header
		writer.append(supplier.getId().toString());
		writer.append(',');
		writer.append(supplier.getName());
		writer.append(',');
		writer.append(supplier.getAddress());
		writer.append(',');
		writer.append(supplier.getEmail());
		writer.append(',');
		writer.append(supplier.getPhone().toString());
		writer.append('\n');

		writer.flush();
		writer.close();

		return true;
	}

	public List<SupplierPOJO> listSuppliers(String location)
			throws NumberFormatException, IOException {

		List<SupplierPOJO> listSuppliers = new ArrayList<SupplierPOJO>();

		BufferedReader br = null;

		String line = "";
		String cvsSplitBy = ",";

		br = new BufferedReader(new FileReader(location));
		while ((line = br.readLine()) != null) {
			SupplierPOJO supplier = new SupplierPOJO();

			String[] newLine = line.split(cvsSplitBy);

			supplier.setId(Long.valueOf(newLine[0]));
			supplier.setName(newLine[1]);
			supplier.setAddress(newLine[2]);
			supplier.setEmail(newLine[3]);
			supplier.setPhone(newLine[4]);

			listSuppliers.add(supplier);
		}

		return listSuppliers;
	}

	public SupplierPOJO modSupplier(int id, String csvFilename)
			throws NumberFormatException, IOException {
		String line = "";
		String cvsSplitBy = ",";
		SupplierPOJO supplier = new SupplierPOJO();
		BufferedReader br = null;
		List<SupplierPOJO> suppliersList = listSuppliers(csvFilename);
		boolean finded = false;
		int index=0;

		for (SupplierPOJO supplierIndexer : suppliersList) {

			long suppId = supplierIndexer.getId();
			index++;
			if (suppId == id) {
				supplier = supplierIndexer;
				finded = true;
			}
		}

		if (finded) {
			SystemHelper sysHelper = new SystemHelper();
			SupplierPOJO newSupplier = supplier;
			
			newSupplier.setName(sysHelper.readln("\nEnter Supplier's Name\n"));
			newSupplier.setAddress(sysHelper
					.readln("\nEnter Supplier's Address\n"));
			newSupplier.setEmail(sysHelper
					.readln("\nEnter Supplier's Email\n"));
			newSupplier.setPhone(sysHelper
					.readln("\nEnter Supplier's Phone\n"));
			
			suppliersList.set(suppliersList.indexOf(supplier), newSupplier);
			
			
			FileWriter overWriter = new FileWriter(csvFilename);
			overWriter.append("");
			overWriter.flush();
			overWriter.close();
			
			for (SupplierPOJO supplierToWrite : suppliersList) {

				addSupplier(csvFilename, supplierToWrite);

			}
		
			return newSupplier;
			
		} else {
			return null;

		}

	}

	public Long getLastSupplierId(String location)
			throws NumberFormatException, IOException {

		Long id = (long) 1;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		br = new BufferedReader(new FileReader(location));
		while ((line = br.readLine()) != null) {

			String[] newLine = line.split(cvsSplitBy);

			id = Long.valueOf(newLine[0]);

		}
		return id;
	}

	public SupplierPOJO getSupplierById(int id, String location)
			throws NumberFormatException, IOException {

		String line = "";
		String cvsSplitBy = ",";
		SupplierPOJO supplier = new SupplierPOJO();
		BufferedReader br = null;

		br = new BufferedReader(new FileReader(location));
		while ((line = br.readLine()) != null) {

			String[] newLine = line.split(cvsSplitBy);

			supplier.setId(Long.valueOf(newLine[0]));

			if (supplier.getId() == id) {

				supplier.setName(newLine[1]);
				supplier.setAddress(newLine[2]);
				supplier.setEmail(newLine[3]);
				supplier.setPhone(newLine[4]);
				return supplier;
			}

		}

		return null;
	}

	public boolean deleteSupplier(long id, String csvFilename)
			throws NumberFormatException, IOException {

		List<SupplierPOJO> suppliersList = listSuppliers(csvFilename);
		SupplierPOJO supplier = new SupplierPOJO();
		boolean finded = false;

		for (SupplierPOJO supplierIndexer : suppliersList) {

			long suppId = supplierIndexer.getId();
			if (suppId == id) {
				supplier = supplierIndexer;
				finded = true;
			}
		}
		if (finded) {
			suppliersList.remove(supplier);
			FileWriter overWriter = new FileWriter(csvFilename);
			overWriter.append("");
			overWriter.flush();
			overWriter.close();
			
			for (SupplierPOJO supplierToWrite : suppliersList) {

				addSupplier(csvFilename, supplierToWrite);

			}

			return true;

		} else
			return false;
	}
}
