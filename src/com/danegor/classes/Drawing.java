package com.danegor.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "drawing")
public class Drawing extends Post {
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "drawing_shapes", joinColumns = { @JoinColumn(name = "drawing_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "shape_id", referencedColumnName = "id") })
	List<Shape> shapes = new ArrayList<Shape>();

	public void setShapes(List<Shape> shapes) {
		this.shapes = shapes;
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "drawing_comments", joinColumns = { @JoinColumn(name = "drawing_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "comment_id", referencedColumnName = "id") })
	List<Comment> comments = new ArrayList<Comment>();


	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
