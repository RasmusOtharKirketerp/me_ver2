package me_ver2;

import java.awt.Color;

public class SolarElement {
	double x = 0;
	double y = 0;
	Color color;
	double speed = 0;
	double omkreds = 0;
	private double radius = 0;

	public SolarElement(int x, int y, Color color, int speed, int omkreds) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.speed = speed;
		this.omkreds = omkreds;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		this.omkreds = (int) Math.PI * this.radius * 2;
	}

	public double getOmkreds() {
		return this.omkreds;
	}

	public void setOmkreds(int o) {
		this.omkreds = o;
		this.radius = o / (int) Math.PI / 2;
	}

}