package com.service;

import java.util.Collection;
import java.util.List;
import com.dao.CDDaoImpl;
import com.dao.CategoriesDaoImpl;
import com.model.CD;

public class StoreServiceImpl implements StoreService {
	private CDDaoImpl cdDaoImpl = new CDDaoImpl();
	private CategoriesDaoImpl categoriesDaoImpl = new CategoriesDaoImpl();

	@Override
	public List<String> getProductGenre(String type) {
		return categoriesDaoImpl.getGenre(type);
	}

	@Override
	public Collection<CD> getCDsProductList() {
		return cdDaoImpl.getProductList();
	}

	@Override
	public void getCDsProductList(String name) {
		cdDaoImpl.getProductList(name);

	}

	@Override
	public void getCDsProductList(String type, String genre) {
		cdDaoImpl.getProductList(type, genre);

	}

	@Override
	public Collection<CD> getCDsNewProducts() {
		return cdDaoImpl.getNewProducts();
	}

}
