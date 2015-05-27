package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.model.CD;
import com.model.Store;

public class CDDaoImpl extends AbstractDao implements CDDao {
	private Connection connection;

	public CDDaoImpl() {
		connection = DBConnection.getConnection();
	}

	public Collection<CD> getProductList() {
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT Type,Genre,Name FROM CDs");
			createCD(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			resourcesCloser(preparedStatement, statement, resultSet);
		}

		return Store.productList;
	}

	public void getProductList(String name) {
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT Type, Genre, Name FROM CDs WHERE Name LIKE ?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%" + name + "%");
			resultSet = preparedStatement.executeQuery();
			createCD(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			resourcesCloser(preparedStatement, statement, resultSet);
		}
	}

	public void getProductList(String type, String genre){
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		String query = "SELECT type, genre, name FROM cds WHERE Type=? AND Genre=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, type);
			preparedStatement.setString(2, genre);
			resultSet = preparedStatement.executeQuery();
			createCD(resultSet);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			resourcesCloser(preparedStatement, statement, resultSet);
		}
	}

	public Collection<CD> getNewProducts() {
		Statement statement = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<String> genres = new ArrayList<String>();
		String query = "SELECT * FROM  CDs WHERE genre =? ORDER BY timestamp DESC LIMIT 1";
		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("SELECT DISTINCT genre FROM CDs");
			while (resultSet.next()) {
				genres.add(resultSet.getString(1));
			}
			for (int i = 0; i < genres.size(); i++) {
				preparedStatement = connection.prepareStatement(query);
				preparedStatement.setString(1, genres.get(i));
				resultSet = preparedStatement.executeQuery();
				createCD(resultSet);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			resourcesCloser(preparedStatement, statement, resultSet);
		}
		return Store.productList;
	}

	private void createCD(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				CD cd = new CD();
				cd.setType(resultSet.getString("Type"));
				cd.setGenre(resultSet.getString("Genre"));
				cd.setName(resultSet.getString("Name"));
				Store.addProduct(cd);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
