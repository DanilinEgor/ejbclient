package com.danegor.classes;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "comment")
public class Comment extends Post {
	String text;
	
	String author;

	public String getText() {
		return text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setText(String text) {
		this.text = text;
	}
}
