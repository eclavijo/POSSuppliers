package com.model;

import static com.google.common.base.Preconditions.*;
import java.io.Serializable;


public class SupplierPOJO implements Serializable {

	private static final long serialVersionUID = 4872640461000241018L;

	private Long supplierId;
	private String supplierName;
	private String supplierAddress;
	private String supplierEmail;
	private String supplierPhone;

	// Getters & Setters

	public SupplierPOJO(Long supplierId, String supplierName,
			String supplierAddress, String supplierEmail, String supplierPhone) {
		this.supplierId = checkNotNull(supplierId);
		this.supplierName = checkNotNull(supplierName);
		this.supplierAddress = checkNotNull(supplierAddress);
		this.supplierEmail = checkNotNull(supplierEmail);
		this.supplierPhone = checkNotNull(supplierPhone);

		checkArgument(supplierId > 0);
		checkArgument(supplierPhone.length() > 0);
		checkArgument(supplierName.length() > 0);
		checkArgument(supplierAddress.length() > 0);
		checkArgument(supplierEmail.length() > 0);
	}

	public SupplierPOJO() {
		// TODO Auto-generated constructor stub
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = checkNotNull(supplierId);
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = checkNotNull(supplierName);
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = checkNotNull(supplierAddress);
	}

	public String getSupplierEmail() {
		return supplierEmail;
	}

	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = checkNotNull(supplierEmail);
	}

	public String getSupplierPhone() {
		return supplierPhone;
	}

	public void setSupplierPhone(String supplierPhone) {
		this.supplierPhone = checkNotNull(supplierPhone);
	}

}
