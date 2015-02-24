package com.eclavijo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.mockito.ArgumentCaptor;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.eclavijo.MyBatisConnectionFactory;
import com.model.SupplierPOJO;

import java.io.Serializable;

@RunWith(MockitoJUnitRunner.class)
public class SupplierDaoMockitoTest {

	private SupplierPOJO supplierMock;
	private InterfaceSupplierDao dao;
	private SqlSessionFactory sqlSessionFactoryMock;
	private SqlSession sessionMock;

	@Before
	public void setUp() throws Exception {
		supplierMock = mock(SupplierPOJO.class);
		sqlSessionFactoryMock = mock(SqlSessionFactory.class);
		sessionMock = mock(SqlSession.class);

		dao = new MyBatisSupplierDao(sqlSessionFactoryMock);

		when(sqlSessionFactoryMock.openSession()).thenReturn(sessionMock);
	}

	@Test
	public void testGetById() {
		SupplierPOJO expectedSupplier = new SupplierPOJO(1L, "asd", "asd",
				"asd@asd.com", "123");

		when(sessionMock.selectOne("Supplier.getById",expectedSupplier.getId())).thenReturn(expectedSupplier);
		SupplierPOJO receivedSupplier = dao.getById(expectedSupplier.getId());

		assertEquals(expectedSupplier, receivedSupplier);

	}

	@Test
	public void testInsertSupplier() throws Exception {
		SupplierPOJO newSupplier = new SupplierPOJO(1L, "asd", "asd",
				"asd@asd.com", "123");
		SupplierPOJO expectedSupplier = new SupplierPOJO(1L, "asd", "asd",
				"asd@asd.com", "123");
		when(sessionMock.insert("Supplier.insert", newSupplier)).thenAnswer(
				new Answer<Serializable>() {
					public Serializable answer(InvocationOnMock invocation)
							throws Throwable {
						SupplierPOJO tempSupplier = invocation.getArgumentAt(1,
								SupplierPOJO.class);
						tempSupplier.setId(1L);
						return tempSupplier.getId();
					}
				});

		// ArgumentCaptor<SupplierPOJO> capture
		// =ArgumentCaptor.forClass(SupplierPOJO.class);
		// doNothing()

		SupplierPOJO resultSupplier = dao.add(newSupplier);

		assertEquals(expectedSupplier.getId(), resultSupplier.getId());
	}

	@Test
	public void testGetAllSuppliersList() {
		List<SupplierPOJO> expectedList = new ArrayList<SupplierPOJO>();
		expectedList.add(new SupplierPOJO(1L, "asd", "asd", "asd@asd.com", "123"));
		expectedList.add(new SupplierPOJO(1L, "asd", "asd", "asd@asd.com", "123"));
		expectedList.add(new SupplierPOJO(1L, "asd", "asd", "asd@asd.com", "123"));

		when(sessionMock.selectList("Supplier.getAll")).thenReturn(expectedList);

		List<SupplierPOJO> newList = dao.getList();
		assertEquals(expectedList, newList);

	}

	@Test
	public void testDeleteSupplier() {

		SupplierPOJO supplierToDelete = new SupplierPOJO(1L, "asd", "asd",
				"asd", "123");
		when(sessionMock.delete("Supplier.deleteById", supplierToDelete))
				.thenAnswer(new Answer<Serializable>() {
					public Serializable answer(InvocationOnMock invocation)
							throws Throwable {
						return true;
					}
				});

		boolean bool = dao.deleteById(supplierToDelete.getId());
		assertEquals(true, bool);

	}

}
