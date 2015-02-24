package com.eclavijo.dao;

import java.util.List;

import com.model.SupplierPOJO;

public interface InterfaceSupplierDao {

	public abstract SupplierPOJO add(SupplierPOJO supplier) throws Exception;

	public abstract List<SupplierPOJO> getList();

	public abstract SupplierPOJO getById(long id);

	public abstract void update(SupplierPOJO supplier);

	public abstract boolean deleteById(long id);

	public abstract long getLastId();

	public abstract List<SupplierPOJO> add(List<SupplierPOJO> listSuppliers);

}