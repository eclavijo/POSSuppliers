package com.eclavijo.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.model.SupplierPOJO;

public class SupplierPOJOTest {
	SupplierPOJO supplier =new SupplierPOJO();
	
	@Test
	public void testPojoSetId() {

		supplier.setSupplierId(123L);
		assertEquals(Long.valueOf("123"), supplier.getSupplierId() );
		
	}
	

	@Test
	public void testPojoSetName() {

		supplier.setSupplierName("nombre de prueba");
		assertEquals("nombre de prueba", supplier.getSupplierName());
		
	}
	
	@Test
	public void testPojoSetAddress() {

		supplier.setSupplierAddress("test address");
		assertEquals("test address", supplier.getSupplierAddress());
		
	}

	@Test
	public void testPojoSetEmail() {

		supplier.setSupplierEmail("test email");
		assertEquals("test email", supplier.getSupplierEmail());
		
	}
	
	@Test
	public void testPojoSetPhone() {
		supplier.setSupplierPhone("test phone");
		assertEquals("test phone",supplier.getSupplierPhone() );
		
	}
}
