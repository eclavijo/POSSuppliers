package com.eclavijo;

import java.util.Scanner;

import com.model.SupplierPOJO;

public class SystemHelper {

	private Scanner scanner;

	public void println(Object text) {
		System.out.println(text);
	}

	public String readln(String msg) {

		println(msg);
		scanner = new Scanner(System.in);
		String incoming = scanner.nextLine();
		return incoming;
	}
	
	public void printSupplier(SupplierPOJO supplier){
	
	
	println(" ID: [" + supplier.getSupplierId() + "] \n Name: ["
			+ supplier.getSupplierName() + "]" + "  \n Address: [" + supplier.getSupplierAddress()
			+ "]\n Email: [" + supplier.getSupplierEmail() + "]\n Phone: ["
			+ supplier.getSupplierPhone() + "]. \n   -o-   ");
	
	}
	
	public Integer readInt(String msg) {
		println(msg);
		Integer incoming = 0;
		boolean flag = false;
		while (!flag) {
			try {
				scanner = new Scanner(System.in);
				incoming = scanner.nextInt();
				flag = true;

			} catch (Exception ex) {
				println("Please enter a Integer.");
				readInt(msg);
			}

		}
		return incoming;
	}

}
