package com.dao;

import java.util.Collection;

import com.model.CD;

public interface ShoppingCartDao {
	
	public Collection<CD> getCartProducts();

	public void addProduct(CD newCartProduct);

	public void removeProduct(int productIndex);

}
