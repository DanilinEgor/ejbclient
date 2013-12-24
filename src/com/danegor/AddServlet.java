package com.danegor;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danegor.beans.AddBean;
import com.danegor.classes.Circle;
import com.danegor.classes.Line;
import com.danegor.classes.Rect;

/**
 * Servlet implementation class AddBean
 */
@WebServlet("/add")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	AddBean ab;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String postType = request.getParameter("postType");
		if (postType.equals("comment")) {
			ab.addComment(request.getParameter("text"), (String) request
					.getSession().getAttribute("username"), request
					.getParameter("drawingID"));
		} else if (postType.equals("drawing")) {
			String shape = request.getParameter("shape");
			if (shape.equals("circle")) {
				Circle c = new Circle();
				c.setxC(Integer.parseInt(request.getParameter("xc")));
				c.setyC(Integer.parseInt(request.getParameter("yc")));
				c.setRadius(Integer.parseInt(request.getParameter("radius")));
				c.setColor(request.getParameter("color"));
				ab.addDrawing(
						(String) request.getSession().getAttribute("username"),
						c);
			} else if (shape.equals("rect")) {
				Rect r = new Rect();
				r.setX(Integer.parseInt(request.getParameter("y")));
				r.setY(Integer.parseInt(request.getParameter("y")));
				r.setW(Integer.parseInt(request.getParameter("w")));
				r.setH(Integer.parseInt(request.getParameter("h")));
				r.setColor(request.getParameter("color"));
				ab.addDrawing(
						(String) request.getSession().getAttribute("username"),
						r);
			} else if (shape.equals("line")) {
				Line l = new Line();
				l.setX1(Integer.parseInt(request.getParameter("x1")));
				l.setY1(Integer.parseInt(request.getParameter("y1")));
				l.setX2(Integer.parseInt(request.getParameter("x2")));
				l.setY2(Integer.parseInt(request.getParameter("y2")));
				l.setColor(request.getParameter("color"));
				ab.addDrawing(
						(String) request.getSession().getAttribute("username"),
						l);
			}
		} else if (postType.equals("shape")) {
			String shape = request.getParameter("shape");
			if (shape.equals("circle")) {
				Circle c = new Circle();
				c.setxC(Integer.parseInt(request.getParameter("xc")));
				c.setyC(Integer.parseInt(request.getParameter("yc")));
				c.setRadius(Integer.parseInt(request.getParameter("radius")));
				c.setColor(request.getParameter("color"));
				ab.addShape(request.getParameter("drawingID"), c);
			} else if (shape.equals("rect")) {
				Rect r = new Rect();
				r.setX(Integer.parseInt(request.getParameter("y")));
				r.setY(Integer.parseInt(request.getParameter("y")));
				r.setW(Integer.parseInt(request.getParameter("w")));
				r.setH(Integer.parseInt(request.getParameter("h")));
				r.setColor(request.getParameter("color"));
				ab.addShape(request.getParameter("drawingID"), r);
			} else if (shape.equals("line")) {
				Line l = new Line();
				l.setX1(Integer.parseInt(request.getParameter("x1")));
				l.setY1(Integer.parseInt(request.getParameter("y1")));
				l.setX2(Integer.parseInt(request.getParameter("x2")));
				l.setY2(Integer.parseInt(request.getParameter("y2")));
				l.setColor(request.getParameter("color"));
				ab.addShape(request.getParameter("drawingID"), l);
			}
		}
		response.sendRedirect("/Client/");
	}

}
