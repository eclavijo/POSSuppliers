package com.model;

import static com.google.common.base.Preconditions.*;
import java.io.Serializable;


public class SupplierPOJO implements Serializable {

	private static final long serialVersionUID = 4872640461000241018L;

	private Long id;
	private String name;
	private String address;
	private String email;
	private String phone;

	// Getters & Setters

	public SupplierPOJO(Long id, String name,
			String address, String email, String phone) {
		this.id = checkNotNull(id);
		this.name = checkNotNull(name);
		this.address = checkNotNull(address);
		this.email = checkNotNull(email);
		this.phone = checkNotNull(phone);

		checkArgument(id > 0);
		checkArgument(phone.length() > 0);
		checkArgument(name.length() > 0);
		checkArgument(address.length() > 0);
		checkArgument(email.length() > 0);
	}

	public SupplierPOJO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = checkNotNull(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = checkNotNull(name);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = checkNotNull(address);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = checkNotNull(email);
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = checkNotNull(phone);
	}

}
