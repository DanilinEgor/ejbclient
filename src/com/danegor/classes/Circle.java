package com.danegor.classes;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "circle")
public class Circle extends Shape {
	int xC, yC, radius;

	public int getxC() {
		return xC;
	}

	public void setxC(int xC) {
		this.xC = xC;
	}

	public int getyC() {
		return yC;
	}

	public void setyC(int yC) {
		this.yC = yC;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
