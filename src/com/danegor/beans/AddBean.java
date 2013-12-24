package com.danegor.beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.danegor.classes.Comment;
import com.danegor.classes.Drawing;
import com.danegor.classes.Post;
import com.danegor.classes.Shape;

/**
 * Session Bean implementation class LoginBean
 */
@Stateless
@LocalBean
public class AddBean {
	@PersistenceContext(name = "punit")
	EntityManager em;

	@EJB
	PostsBean pb;

	public AddBean() {
	}

	public void addComment(String text, String author, String drID) {
		List<Post> drs = pb.getAllPosts();
		int id = Integer.parseInt(drID);
		for (Post post : drs) {
			if (post.getId() == id) {
				Drawing dr = (Drawing) post;
				Comment c = new Comment();
				c.setAuthor(author);
				c.setText(text);
				em.persist(c);
				dr.getComments().add(c);
				em.merge(dr);
				return;
			}
		}
	}

	public void addDrawing(String author, Shape s) {
		Drawing dr = new Drawing();
		dr.setAuthor(author);
		Set<Shape> shapes = new HashSet<Shape>();
		shapes.add(s);
		dr.setShapes(shapes);
		em.persist(s);
		em.persist(dr);
	}
	
	public void addShape(String drID, Shape s){
		List<Post> drs = pb.getAllPosts();
		int id = Integer.parseInt(drID);
		for (Post post : drs) {
			if (post.getId() == id) {
				Drawing dr = (Drawing) post;
				em.persist(s);
				dr.getShapes().add(s);
				em.merge(dr);
				return;
			}
		}
	}

}
