package com.eclavijo;


//para slf4j
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

//para slf4j
// private static final Logger logger =
// LoggerFactory.getLogger(DateUtils.class);
//
//import java.util.List;
//
////para log4j
//import org.apache.log4j.Logger;
//
//import com.eclavijo.csv.SupplierCsvReader;
//import com.eclavijo.csv.SupplierCsvWriter;
//import com.model.SupplierPOJO;
//import com.eclavijo.dao.SupplierDao;

public class SupplierManager {

	private static SupplierManagerHelper managerHelper= new SupplierManagerHelper();

	public static void main(String[] args) throws Exception {

		// para slf4j
		// private static final Logger logger =
		// LoggerFactory.getLogger(DateUtils.class);
		// para log4j
		// DateUtils dateUtils = new DateUtils();
		// final Logger LOGGER = Logger.getLogger(DateUtils.class);

		if (args[0].equals("addDB")) {
			managerHelper.addOnDB();
		}

		if (args[0].equals("listDB")) {
			managerHelper.listOnDB();
		}

		if (args[0].equals("findDB")) {

			managerHelper.findOnDB();
		}

		if (args[0].equals("deleteDB")) {
			managerHelper.deleteOnDB();

		}

		if (args[0].equals("modifyDB")) {

			managerHelper.modifyOnDB();

		}

		if (args[0].equals("help")) {
			managerHelper.help();
		}

		if (args[0].equals("add")) {
				
			managerHelper.addOneOnCsv();

		}
		if (args[0].equals("list")) {
			managerHelper.listOnCsv();
		}
		if (args[0].equals("find")) {
			managerHelper.findInCsv();

		}
		if (args[0].equals("delete")) {
			managerHelper.deleteInCsv();

		}

		if (args[0].equals("modify")) {

			managerHelper.modifyInCsv();

		}
		// else {
		// sysHelper
		// .println("\nRecibed :[ "
		// + args[0]
		// + " ] as parameter. Don't know what to do with that shiet! \n ");
		// }

	}

	
}