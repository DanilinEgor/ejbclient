package com.danegor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		response.getWriter()
				.write("<html><body>"
						+ "<form method=\"post\">login:<input name=\"login\">"
						+ "<br>pass:<input name=\"pass\"><input type=submit value=\"Login\"></form></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		Connection con = null;
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:jboss/postgresDS");
			con = ds.getConnection();
			PreparedStatement stmt = con.prepareStatement(
					"select * from user_table where name = ? and pass = ?",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			stmt.setString(1, login);
			stmt.setString(2, pass);
			ResultSet rs = stmt.executeQuery();
			response.getWriter().write("<html><body>");
			if (rs.next() == false) {
				response.getWriter().write("No such name and pass");
			} else {
				rs.beforeFirst();
				while (rs.next()) {
					response.getWriter().write(
							login + "<br>" + pass + "<br> <hr>"
									+ rs.getString("id") + " | "
									+ rs.getString("name") + " | "
									+ rs.getString("pass"));
				}
			}
			response.getWriter().write("</body></html");
			rs.close();
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
	}

}
