package com.pazukdev.dao;

import com.pazukdev.entities.OldBearing;

import java.util.List;

public interface DAOInterface<T> {

	public void create(OldBearing obj);

	public T read(T obj);

	public void update(T obj);

	public void delete(T obj);

	public List<T> getList();
}
