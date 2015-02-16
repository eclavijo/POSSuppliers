package com.eclavijo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.model.SupplierPOJO;

public class SupplierPOJOTest {
	SupplierPOJO supplier =new SupplierPOJO();
	
	@Test
	public void testPojoSetId() {

		supplier.setId(123L);
		assertEquals(Long.valueOf("123"), supplier.getId() );
		
	}
	

	@Test
	public void testPojoSetName() {

		supplier.setName("nombre de prueba");
		assertEquals("nombre de prueba", supplier.getName());
		
	}
	
	@Test
	public void testPojoSetAddress() {

		supplier.setAddress("test address");
		assertEquals("test address", supplier.getAddress());
		
	}

	@Test
	public void testPojoSetEmail() {

		supplier.setEmail("test email");
		assertEquals("test email", supplier.getEmail());
		
	}
	
	@Test
	public void testPojoSetPhone() {
		supplier.setPhone("test phone");
		assertEquals("test phone",supplier.getPhone() );
		
	}
}
