package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriesDaoImpl extends AbstractDao implements CategoriesDao {
	private Connection connection;

	public CategoriesDaoImpl() {
		connection = DBConnection.getConnection();
	}

	public List<String> getGenre(String type) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Statement statement = null;
		List<String> genres = new ArrayList<String>();
		String query = "SELECT DISTINCT genre  FROM CDs where type=?";
		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, type);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				genres.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			resourcesCloser(preparedStatement, statement, resultSet);
		}
		return genres;
	}
}
