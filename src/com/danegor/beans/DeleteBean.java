package com.danegor.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.danegor.classes.Post;
import com.danegor.classes.Shape;

/**
 * Session Bean implementation class DeleteBean
 */
@Stateless
@LocalBean
public class DeleteBean {
	@PersistenceContext(name = "punit")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public DeleteBean() {
        // TODO Auto-generated constructor stub
    }

    public void delete(String table, String id) {
    	Query query = em.createQuery("from "+table+" where id = :id");
    	query.setParameter("id", Integer.parseInt(id));
    	List<Shape> list = query.getResultList();
    	em.remove(list.get(0));
    }
}
