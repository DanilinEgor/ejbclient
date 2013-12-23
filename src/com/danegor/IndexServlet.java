package com.danegor;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.danegor.beans.RegBean;

/**
 * Servlet implementation class IndexSerblet
 */
@WebServlet("/*")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	RegBean rb;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
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
		response.getWriter().write("<html><body>");
		if (session.getAttribute("username") == null)
			response.getWriter()
					.write("<a href=\"/Client/login\">Login</a><br><a href=\"/Client/registration\">Registration</a>");
		else
			response.getWriter().write(
					session.getAttribute("username")
							+ " | <a href=\"/Client/exit\">Exit</a>");
		
		response.getWriter().write("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
