package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.model.CD;

public class ShoppingCartImpl extends AbstractDao implements ShoppingCartDao {

	protected ArrayList<CD> cartProducts;

	public ShoppingCartImpl() {
		cartProducts = new ArrayList<CD>();
	}

	public ArrayList<CD> getCartProducts() {
		return cartProducts;
	}

	public void addProduct(CD newCartProduct) {
		List<CD> cartProducts = getCartProducts();
		for (int i = 0; i < cartProducts.size(); i++) {
			CD cd = (CD) cartProducts.get(i);
			if (cd.getName().equals(newCartProduct.getName())) {
				cd.setQuantity();
				return;
			}
		}
		cartProducts.add(newCartProduct);
		return;
	}

	public void removeProduct(int productIndex) {
		cartProducts.remove(productIndex);
	}
}
