package com.danegor.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.danegor.classes.Comment;
import com.danegor.classes.Drawing;
import com.danegor.classes.Post;

/**
 * Session Bean implementation class PostsBean
 */
@Stateless
@LocalBean
public class PostsBean {
	@PersistenceContext(name = "punit")
	EntityManager em;
	
    public PostsBean() {
    }
    
    public List<Post> getAllPosts() {
    	Query query = em.createQuery("from Post");
    	List<Post> res = query.getResultList();
    	return res;
    }
    
    public List<Post> getUserPosts(String author) {
    	Query query = em.createQuery("from Post where author = :author");
    	query.setParameter("author", author);
    	List<Post> res = query.getResultList();
    	return res;
    }
    

}
