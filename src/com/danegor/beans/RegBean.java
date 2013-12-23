package com.danegor.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.danegor.classes.Circle;
import com.danegor.classes.Drawing;
import com.danegor.classes.Shape;
import com.danegor.classes.User;

/**
 * Session Bean implementation class RegBean
 */
@Stateless
@LocalBean
public class RegBean {
	@PersistenceContext(name = "punit")
	EntityManager em;
	
    public RegBean() {
    }

	public void createUser(String name, String pass) {
		User user = new User();
		user.setUserName(name);	
		user.setUserPass(pass);
		em.persist(user);
	}

	public List<Shape> showAll() {
		Query query = em.createQuery("from Drawing");
		List<Drawing> drs = query.getResultList();
		List<Shape> res = new ArrayList<Shape>();
		for (Drawing drawing : drs) {
			res.addAll(drawing.getShapes());
		}
		return res;
	}
	
}
