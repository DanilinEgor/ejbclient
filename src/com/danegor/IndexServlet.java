package com.danegor;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danegor.beans.RegBean;
import com.danegor.classes.Circle;
import com.danegor.classes.Shape;

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
		List<Shape> shapes = rb.showAll();
		response.getWriter().write("<html><body>");
		for (Shape shape : shapes) {
			Circle c = (Circle) shape;
			response.getWriter().write(c.getId() + " | " + c.getRadius() + "<br>");
		}
		response.getWriter()
				.write("<a href=\"/Client/login\">Login</a><br><a href=\"/Client/registration\">Registration</a>"
						+ "</body></html>");
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
