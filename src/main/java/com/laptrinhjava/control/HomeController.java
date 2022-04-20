package com.laptrinhjava.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjava.dao.DAO;
import com.laptrinhjava.entity.Category;
import com.laptrinhjava.entity.Product;

@WebServlet(name = "HomeController", urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		// b1: get data from dao
		DAO dao = new DAO();
		List<Product> list = dao.getTop3();
		List<Category> listC = dao.getAllCategory();
		Product last = dao.getLast();

		// b2: set data to jsp
		request.setAttribute("listP", list);
		request.setAttribute("listCC", listC);
		request.setAttribute("p", last);
		request.getRequestDispatcher("Home.jsp").forward(request, response);
		// 404 -> url
		// 500 -> jsp properties
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		processRequest(request, response);
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
