package com.laptrinhjava.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.laptrinhjava.dao.DAO;
import com.laptrinhjava.entity.Account;

@WebServlet(name = "AddController", urlPatterns = { "/add" })
public class AddController extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");// lam viec voi tieng viet
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		String price = request.getParameter("price");
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String category = request.getParameter("category");
		HttpSession session = request.getSession();
		Account a = (Account) session.getAttribute("acc");
		int sid = a.getId();

		DAO dao = new DAO();
		dao.insertProduct(name, image, price, title, description, category, sid);
		response.sendRedirect("manager");
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
