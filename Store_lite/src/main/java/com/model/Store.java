package com.model;

import java.util.ArrayList;
import java.util.List;
import com.model.CD;

public class Store {

	public static List<CD> productList = new ArrayList<CD>();;

	public static List<CD> getProductList() {
		return productList;
	}

	public static void addProduct(CD cd) {
		getProductList().add(cd);
	}

	public static void deleteProducts(List<CD> productList) {
		Store.productList.clear();
	}
}
