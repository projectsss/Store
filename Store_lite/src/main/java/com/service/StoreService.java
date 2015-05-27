package com.service;
import java.util.Collection;
import java.util.List;
import com.model.CD;

public interface StoreService {
	
	public List<String> getProductGenre(String type);

	public Collection<CD> getCDsProductList();

	public void getCDsProductList(String name);

	public void getCDsProductList(String type, String genre);

	public Collection<CD> getCDsNewProducts();

}
