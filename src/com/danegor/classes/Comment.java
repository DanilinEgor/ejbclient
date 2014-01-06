package com.danegor.classes;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name = "comment")
public class Comment extends Post {
	String text;
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
