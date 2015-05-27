package com.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.dao.ShoppingCartImpl;
import com.model.CD;

public class ShoppingCartTest {

	@Test
	public void testAddProduct() {
		CD cd1 = new CD();
		cd1.setType("Video");
		cd1.setGenre("Film");
		cd1.setName("Matrix");
		cd1.setQuantity();
		CD cd2 = new CD();
		cd2.setType("Video");
		cd2.setGenre("Film");
		cd2.setName("Mortal Kombat");
		cd2.setQuantity();
		ShoppingCartImpl cart = new ShoppingCartImpl();
		cart.addProduct(cd1);
		cart.addProduct(cd2);
		assertTrue("Correct list of products",
				cart.getCartProducts().get(1) == cd2);
	}

}
