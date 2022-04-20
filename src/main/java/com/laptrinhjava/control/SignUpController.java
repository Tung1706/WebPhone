package com.laptrinhjava.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjava.dao.DAO;
import com.laptrinhjava.entity.Account;

@WebServlet(name = "SignUpController", urlPatterns = { "/signup" })
public class SignUpController extends HttpServlet {
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String re_pass = request.getParameter("repass");
		if (!pass.equals(re_pass)) {
			response.sendRedirect("Login.jsp");
		} else {
			DAO dao = new DAO();
			Account a = dao.checkAccountExit(user);
			if (a == null) {
				// được signup
				dao.singup(user, pass);
				response.sendRedirect("home");
			} else {
				// đẩy về trang login.jsp
				response.sendRedirect("Login.jsp");
			}
		}
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
