package com.dao;

import java.util.Collection;
import com.model.CD;

public interface CDDao {
	
	Collection<CD> getProductList();

	void getProductList(String name);

	void getProductList(String type, String genre);

	Collection<CD> getNewProducts();

}
