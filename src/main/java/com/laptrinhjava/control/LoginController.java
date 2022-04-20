package com.laptrinhjava.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.laptrinhjava.dao.DAO;
import com.laptrinhjava.entity.Account;

@WebServlet(name = "LoginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		// String username = request.getParameter("user");
		// String password = request.getParameter("pass");
		// DAO dao = new DAO();
		// Account a = dao.login(username, password);
		// if (a == null) {
		// request.setAttribute("mess", "Wrong user or pass");
		// request.getRequestDispatcher("Login.jsp").forward(request, response);
		// } else {
		// request.getRequestDispatcher("home").forward(request, response);// sử dụng
		// khi chuyển trang càn truyền dữ diệu
		// response.sendRedirect("home");//sử dụng khi chuyển trang mà không cần truyền
		// dữ liệu
		// HttpSession session = request.getSession();
		// session.setAttribute("acc", a);
		// session.setMaxInactiveInterval(60);
		// response.sendRedirect("home");
		// }
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// processRequest(request, response);
		// b1 get user, pass from cookie
		Cookie arr[] = request.getCookies();
		if (arr != null) {
			for (Cookie o : arr) {
				if (o.getName().equals("userC")) {
					request.setAttribute("username", o.getValue());
				}
				if (o.getName().equals("passC")) {
					request.setAttribute("password", o.getValue());
				}
			}
		}

		// b2 set user, pass to login form
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// processRequest(request, response);
		String username = request.getParameter("user");
		String password = request.getParameter("pass");
		String remember = request.getParameter("remember");
		DAO dao = new DAO();
		Account a = dao.login(username, password);
		if (a == null) {
			request.setAttribute("mess", "wrong username or password");
			request.getRequestDispatcher("Login.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("acc", a);
			// lưu account lên trên cookie
			Cookie u = new Cookie("userC", username);
			Cookie p = new Cookie("passC", password);
			u.setMaxAge(60);

			if (remember != null) {
				u.setMaxAge(60);
			} else {
				p.setMaxAge(0);
			}

			// lưu u và p lên trên chrome
			response.addCookie(u);
			response.addCookie(p);

			response.sendRedirect("home");
		}

	}

	@Override
	public String getServletInfo() {

		return "Short description";
	}// </editor-fold>

}
