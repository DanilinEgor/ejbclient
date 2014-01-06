package com.danegor.classes;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "drawing")
public class Drawing extends Post {
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "drawing_shapes", joinColumns = { @JoinColumn(name = "drawing_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "shape_id", referencedColumnName = "id") })
	Set<Shape> shapes = new HashSet<Shape>();

	public Set<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(Set<Shape> shapes) {
		this.shapes = shapes;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "drawing_comments", joinColumns = { @JoinColumn(name = "drawing_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "comment_id", referencedColumnName = "id") })
	Set<Comment> comments = new HashSet<Comment>();

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
