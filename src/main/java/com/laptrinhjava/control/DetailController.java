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

@WebServlet(name = "DetailController", urlPatterns = { "/detail" })
public class DetailController extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("pid");
		DAO dao = new DAO();
		Product p = dao.getProductByID(id);
		List<Category> listC = dao.getAllCategory();
		Product last = dao.getLast();

		request.setAttribute("detail", p);
		request.setAttribute("listCC", listC);
		request.setAttribute("p", last);
		request.getRequestDispatcher("Detail.jsp").forward(request, response);
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