package com.danegor.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class LoginBean
 */
@Stateless
@LocalBean
public class LoginBean {
	@PersistenceContext(name = "punit")
	EntityManager em;
	
    public LoginBean() {
    }
    
    public boolean checkLogin(String login, String pass) {
    	Query query = em.createQuery("from User where name = :name and pass = :pass");
    	query.setParameter("name", login);
    	query.setParameter("pass", pass);
    	List<?> list = query.getResultList();
		return !list.isEmpty();
    }

}
