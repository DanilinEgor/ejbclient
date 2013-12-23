package com.danegor;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danegor.beans.RegBean;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @EJB
    RegBean rb;
	
//	ejb:/EJBTest//HelloWorldBean!com.danegor.business.HelloWorld
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter()
		.write("<html><body>"
				+ "<form method=\"post\">login:<input name=\"login\">" +
				"<br>pass:<input name=\"pass\"><input type=submit value=\"Register\"></form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		rb.createUser(login, pass);
		response.getWriter().write("<html><body>Registered!</body></html");
		/*
		Connection con = null;
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:jboss/postgresDS");
			con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement("insert into user_table(name, pass) values (?, ?)");
			stmt.setString(1, login);
			stmt.setString(2, pass);
			stmt.executeUpdate();
			response.getWriter().write("<html><body>Registered!</body></html");
			stmt.close();
		} catch (Exception e) {
			response.getWriter().write("Exception thrown :/");
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		*/
	}

}
