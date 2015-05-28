package com.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

import com.service.StoreServiceImpl;

public class CategoriesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StoreServiceImpl storeServiceImpl = new StoreServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws java.io.IOException, ServletException {

		doPost(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws java.io.IOException, ServletException {
		String type = request.getParameter("type");
		List<String> genres = storeServiceImpl.getProductGenre(type);
		String types = new ObjectMapper().writeValueAsString(genres);
		PrintWriter pw = response.getWriter();
		pw.print(types.toString());
		pw.close();

	}

}
