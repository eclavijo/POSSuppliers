package com.eclavijo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.eclavijo.MyBatisConnectionFactory;
import com.eclavijo.SystemHelper;
import com.model.SupplierPOJO;

public class SupplierDao {

	private SqlSessionFactory sqlSessionFactory;
	private SystemHelper sysHelper;

	public SupplierDao(SqlSessionFactory sessionFactory) {
		sqlSessionFactory = sessionFactory;
	}

	public SupplierPOJO insertSupplier(SupplierPOJO supplier) throws Exception {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			int result = session.insert("Supplier.insert", supplier);
			session.commit();
			return supplier;
		} catch (Exception e) {
			System.out.println("Error in insert !!!");
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}

	public List<SupplierPOJO> getList() {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			List<SupplierPOJO> list = session.selectList("Supplier.getAll");
			return list;
		} catch (Exception e) {
			sysHelper.println("Error in Listing !!!");
			e.printStackTrace();
			return null;
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
		} catch (Exception e) {
			sysHelper.println("Error in findById !!!");
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}

	public void update(SupplierPOJO supplier) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.update("Supplier.update", supplier);
			session.commit();
		} catch (Exception e) {
			sysHelper.println("Error on update !!!");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public boolean deleteById(long id) {

		SqlSession session = sqlSessionFactory.openSession();

		try {
			session.delete("Supplier.deleteById", id);
			session.commit();
			return true;
		} catch (Exception e) {
			sysHelper.println("Error on delete !!!");
			e.printStackTrace();
			return false;
		} finally {
			session.close();
		}
	}

	// public long getNextId() {
	//
	// SqlSession session = sqlSessionFactory.openSession();
	// long nextId = 0;
	// try {
	// nextId = (Long) session.selectOne("Supplier.getNextId");
	// session.commit();
	// } finally {
	// session.close();
	// }
	// return nextId;
	// }

}
