package com.danegor;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.danegor.beans.LoginBean;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	LoginBean lb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null)
			response.getWriter()
					.write("<html><body>You are already logined!<br><a href=\"/Client/index\">Index page</a></body></html>");
		else
			response.getWriter()
					.write("<html><body>"
							+ "<form method=\"post\">login:<input name=\"login\">"
							+ "<br>pass:<input name=\"pass\" type=\"password\"><input type=submit value=\"Login\"></form><br><a href=\"/Client/index\">Index page</a></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		response.getWriter().write("<html><body>");
		if (lb.checkLogin(login, pass)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", login);
			response.getWriter().write("Welcome, " + login);
		} else {
			response.getWriter().write("Wrong login/pass");
		}
		response.getWriter().write(
				"<br><a href=\"/Client/index\">Index page</a></body></html");
	}

}
