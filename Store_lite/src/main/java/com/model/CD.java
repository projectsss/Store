package com.model;

import java.io.*;

public class CD implements Serializable {
	private static final long serialVersionUID = 1L;
	int cdQuantity;
	String cdName;
	String cdType;
	String cdGenre;

	public CD() {}

	public CD(String name, String type, String genre) {
		cdName = name;
		cdType = type;
		cdGenre = genre;
		cdQuantity = 1;
	}

	public void setType(String type) {
		this.cdType = type;
	}

	public String getType() {
		return cdType;
	}

	public void setGenre(String genre) {
		this.cdGenre = genre;
	}

	public String getGenre() {
		return cdGenre;
	}

	public void setName(String name) {
		this.cdName = name;
	}

	public String getName() {
		return cdName;
	}

	public void setQuantity() {
		cdQuantity += 1;
	}

	public int getQuantity() {
		return cdQuantity;
	}

}
