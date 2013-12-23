package com.danegor.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.danegor.classes.User;

/**
 * Session Bean implementation class RegBean
 */
@Stateless
@LocalBean
public class RegBean {
	@PersistenceContext(name = "org.hibernate.tutorial.jpa")
	EntityManager em;
	
    public RegBean() {
    }

	public void createUser(String name, String pass) {
		User user = new User();
		user.setUserName(name);
		user.setUserPass(pass);
		em.persist(user);
	}

}
