package com.mycompany.a3;

public abstract class Moveable extends GameObjects{
	private int heading; //local variable for heading
	private int speed; //local variable for speed
	
	public Moveable(int s, int r, int g, int b, float x, float y, int h, int sp) {
		super(s, r, g, b, x, y);
		this.speed = sp;
		this.heading = h;
	}
	
	public abstract int getHeading(); //abstract method for all moveable objects

	public abstract void move(int time);
}
