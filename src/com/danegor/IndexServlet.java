package com.danegor;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.danegor.beans.PostsBean;
import com.danegor.beans.RegBean;
import com.danegor.classes.Circle;
import com.danegor.classes.Comment;
import com.danegor.classes.Drawing;
import com.danegor.classes.Line;
import com.danegor.classes.Post;
import com.danegor.classes.Rect;
import com.danegor.classes.Shape;

/**
 * Servlet implementation class IndexSerblet
 */
@WebServlet("/*")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	RegBean rb;

	@EJB
	PostsBean pb;

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
					.write("<a href=\"/Client/login\">Login</a> | <a href=\"/Client/registration\">Registration</a>");
		else
			response.getWriter().write(
					session.getAttribute("username")
							+ " | <a href=\"/Client/exit\">Exit</a>");
		response.getWriter()
				.write("<script type='text/javascript'>"
						+ "function changed()"
						+ "{var el = document.getElementById('shape');"
						+ "var text = el.options[el.selectedIndex].innerHTML;"
						+ "if (text===\"circle\"){"
						+ "document.getElementById('line').hidden=true;"
						+ "document.getElementById('circle').hidden=false;"
						+ "document.getElementById('rect').hidden=true;}"
						+ "if (text===\"line\"){"
						+ "document.getElementById('line').hidden=false;"
						+ "document.getElementById('circle').hidden=true;"
						+ "document.getElementById('rect').hidden=true;}"
						+ "if (text===\"rect\"){"
						+ "document.getElementById('line').hidden=true;"
						+ "document.getElementById('circle').hidden=true;"
						+ "document.getElementById('rect').hidden=false;}"
						+ "}"
						+ "function changed2()"
						+ "{var el2 = document.getElementById('shape2');"
						+ "var text2 = el2.options[el2.selectedIndex].innerHTML;"
						+ "if (text2===\"circle2\"){"
						+ "document.getElementById('line2').hidden=true;"
						+ "document.getElementById('circle2').hidden=false;"
						+ "document.getElementById('rect2').hidden=true;}"
						+ "if (text2===\"line2\"){"
						+ "document.getElementById('line2').hidden=false;"
						+ "document.getElementById('circle2').hidden=true;"
						+ "document.getElementById('rect2').hidden=true;}"
						+ "if (text2===\"rect2\"){"
						+ "document.getElementById('line2').hidden=true;"
						+ "document.getElementById('circle2').hidden=true;"
						+ "document.getElementById('rect2').hidden=false;}"
						+ "}" + "</script>");
		response.getWriter().write("<br><br><br><hr>");
		List<Post> posts = pb.getAllPosts();
		for (Post post : posts) {
			if (Drawing.class.isAssignableFrom(post.getClass())) {
				Drawing dr = (Drawing) post;
				response.getWriter().write("<p>");
				response.getWriter().write("#" + dr.getId() + "<br>");
				response.getWriter()
						.write("Author: " + dr.getAuthor() + "<br>");
				response.getWriter().write("Mark: " + dr.getMark() + "<br>");
				response.getWriter().write("Shapes:<br>");
				Set<Shape> shapes = dr.getShapes();
				for (Shape shape : shapes) {
					response.getWriter().write(
							"#" + shape.getId() + " | type: ");
					if (Circle.class.isAssignableFrom(shape.getClass())) {
						Circle c = (Circle) shape;
						response.getWriter().write("circle: (");
						response.getWriter().write("xC =" + c.getxC());
						response.getWriter().write(", yC =" + c.getyC());
						response.getWriter().write(", r =" + c.getRadius());
						response.getWriter().write(", color =" + c.getColor());
						response.getWriter().write(")<br>");
					} else if (Rect.class.isAssignableFrom(shape.getClass())) {
						Rect r = (Rect) shape;
						response.getWriter().write("rectangle: (");
						response.getWriter().write("x =" + r.getX());
						response.getWriter().write(", y =" + r.getY());
						response.getWriter().write(", w =" + r.getW());
						response.getWriter().write(", h =" + r.getH());
						response.getWriter().write(", color =" + r.getColor());
						response.getWriter().write(")<br>");
					} else if (Line.class.isAssignableFrom(shape.getClass())) {
						Line l = (Line) shape;
						response.getWriter().write("line: (");
						response.getWriter().write("x1 =" + l.getX1());
						response.getWriter().write(", y1 =" + l.getY1());
						response.getWriter().write(", x2 =" + l.getX2());
						response.getWriter().write(", y2 =" + l.getY2());
						response.getWriter().write(", color =" + l.getColor());
						response.getWriter().write(")<br>");
					}
					response.getWriter().write("<br>");
				}

				if (request.getSession().getAttribute("username") != null
						&& request.getSession().getAttribute("username")
								.equals(dr.getAuthor()))
					response.getWriter()
							.write("Add shape:<br><form method=\"post\" action=\"/Client/add\">"
									+ "Shape:<select id=\"shape2\" name=\"shape\" onChange=\"changed2()\">"
									+ "<option selected=\"selected\">circle</option>"
									+ "<option>rect</option>"
									+ "<option>line</option>"
									+ "</select>"
									+ "<p id=\"circle2\">xC:<input name=\"xc\"><br>yC:<input name=\"yc\"><br>radius:<input name=\"radius\"></p>"
									+ "<p hidden=\"true\" id=\"rect2\">x:<input name=\"x\"><br>y:<input name=\"y\"><br>w:<input name=\"w\"><br>h:<input name=\"h\"></p>"
									+ "<p hidden=\"true\" id=\"line2\">x1:<input name=\"x1\"><br>y1:<input name=\"y1\"><br>x2:<input name=\"x2\"><br>y2:<input name=\"y2\"></p>"
									+ "<br>color:<input name=\"color\">"
									+ "<input type=\"hidden\" name=\"postType\" value=\"shape\">"
									+ "<input type=\"hidden\" name=\"drawingID\" value="
									+ dr.getId()
									+ ">"

									+ "<br><input type=submit value=\"Add\"></form><br>");

				Set<Comment> comments = dr.getComments();
				if (!comments.isEmpty()) {
					response.getWriter().write(
							"<p style=\"margin-left: 100px\">Comments:<br>");
					for (Comment comment : comments) {
						response.getWriter().write("#" + comment.getId());
						response.getWriter().write(
								"  " + comment.getAuthor() + "<br>");
						response.getWriter().write(
								"Text: " + comment.getText() + "<br>");
						response.getWriter().write(
								"Mark: " + comment.getMark() + "<br><br><br>");
					}
					response.getWriter().write("</p>");
				}
				if (request.getSession().getAttribute("username") != null)
					response.getWriter()
							.write("<form method=\"post\" action=\"/Client/add\">Text:<input name=\"text\"><input type=\"hidden\" name=\"postType\" value=\"comment\"><input type=\"hidden\" name=\"drawingID\" value="
									+ dr.getId()
									+ ">"
									+ "<br><input type=submit value=\"Add\"></form><br>");

				response.getWriter().write("</p><hr>");
			}
		}

		if (request.getSession().getAttribute("username") != null)
			response.getWriter()
					.write("Add drawing:<br><form method=\"post\" action=\"/Client/add\">"
							+ "Shape:<select id=\"shape\" name=\"shape\" onChange=\"changed()\">"
							+ "<option selected=\"selected\">circle</option>"
							+ "<option>rect</option>"
							+ "<option>line</option>"
							+ "</select>"
							+ "<p id=\"circle\">xC:<input name=\"xc\"><br>yC:<input name=\"yc\"><br>radius:<input name=\"radius\"></p>"
							+ "<p hidden=\"true\" id=\"rect\">x:<input name=\"x\"><br>y:<input name=\"y\"><br>w:<input name=\"w\"><br>h:<input name=\"h\"></p>"
							+ "<p hidden=\"true\" id=\"line\">x1:<input name=\"x1\"><br>y1:<input name=\"y1\"><br>x2:<input name=\"x2\"><br>y2:<input name=\"y2\"></p>"
							+ "<br>color:<input name=\"color\">"
							+ "<input type=\"hidden\" name=\"postType\" value=\"drawing\">"
							+ "<br><input type=submit value=\"Add\"></form><br>");

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
