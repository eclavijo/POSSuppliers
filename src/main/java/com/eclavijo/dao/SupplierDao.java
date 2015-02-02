package com.eclavijo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.eclavijo.MyBatisConnectionFactory;
import com.model.SupplierPOJO;

public class SupplierDao {

	// String MQL_GET_ALL_SUPPLIERS = "select * from suppliers";
	// String MQL_GET_SUPPLIER_BY_ID =
	// "select * from suppliers where id = #{id}";
	// String MQL_CREATE_SUPPLIER =
	// "insert into suppliers (name, email, address, phone) values (#{name},#{email},#{address},#{phone})";
	// String MQL_UPDATE_SUPPLIER =
	// "update suppliers set name=#{name}, email=#{email}, address=#{address}, phone=#{phone} where supplier_id=#{supplier_id}";
	// String MQL_DELETE_SUPPLIER = "delete from suppliers where id=#{id}";
	//
	// @Select(MQL_GET_ALL_SUPPLIERS)
	// public List<SupplierPOJO> getAllSuppliers() throws Exception;
	//
	// @Select(MQL_GET_SUPPLIER_BY_ID)
	// public SupplierPOJO getUserById(long id) throws Exception;
	//
	// @Insert(MQL_CREATE_SUPPLIER)
	// public int createSupplier(SupplierPOJO supplier) throws Exception;
	//
	// @Update(MQL_UPDATE_SUPPLIER)
	// public int updateSupplier(SupplierPOJO supplier) throws Exception;
	//
	// @Delete(MQL_DELETE_SUPPLIER)
	// public int deleteSupplier(SupplierPOJO supplier) throws Exception;
	private SqlSessionFactory sqlSessionFactory;

	public SupplierDao() {
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}

	public SupplierPOJO insertSupplier(SupplierPOJO supplier) throws Exception {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.insert("Supplier.insert", supplier);
			session.commit();
		} finally {
			session.close();
		}
		return supplier;
	}

	/**
	 * Returns the list of all Contact instances from the database.
	 * 
	 * @return the list of all Contact instances from the database.
	 */
	@SuppressWarnings("unchecked")
	public List<SupplierPOJO> getList() {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			List<SupplierPOJO> list = session.selectList("Supplier.getAll");
			return list;
		} finally {
			session.close();
		}
	}

	public SupplierPOJO getById(long id) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			SupplierPOJO contact = (SupplierPOJO) session.selectOne(
					"Supplier.getById", id);
			return contact;
		} finally {
			session.close();
		}
	}

	public void update(SupplierPOJO supplier) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.update("Supplier.update", supplier);
			session.commit();
		} finally {
			session.close();
		}
	}

	public void deleteById(long id) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.delete("Supplier.deleteById", id);
			session.commit();
		} finally {
			session.close();
		}
	}

	public long getNextId() {

		SqlSession session = sqlSessionFactory.openSession();
		long nextId = 0;
		try {
			nextId = (Long) session.selectOne("Supplier.getNextId");
			session.commit();
		} finally {
			session.close();
		}
		return nextId;
	}

}
