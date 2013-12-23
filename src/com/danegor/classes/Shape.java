package com.danegor.classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "shape")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Shape {
	String color;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	int id;

	public String getColor() {
		return color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
