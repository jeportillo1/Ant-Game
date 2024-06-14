package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.util.Random;


public class Spider extends Moveable{
	private int size; //local variable for size
	private int heading; //local variable for heading
	private int speed; //local variable for speed
	private Point myCurrLoc; //local variable for myCurrSpeed
	private ColorUtil myColor = new ColorUtil(); //local variable for color
	private int red; //local variable for red color
	private int blue; //local variable for blue color
	private int green; //local variable for green color
	
	// spider constructor
	public Spider(int s, int h, int sp, int r, int g, int b, float x, float y) {
		super(s, r, g, b, x, y, h, sp);
		this.size = s;
		this.heading = h;
		this.speed = sp;
		ColorUtil.rgb(r, g, b);
		this.myCurrLoc = new Point(x,y);
		this.red = r;
		this.green = g;
		this.blue = b;
	}

	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pt) {
		// TODO Auto-generated method stub
		
		int xPt = (int) this.myCurrLoc.getX() + pt.getX();
		int yPt = (int) this.myCurrLoc.getY()+ pt.getY();
		
		int xPt2 = xPt + this.size;
		int yPt2 = yPt;
		
		int xPt3 = xPt + (int) (this.size / 2);
		int yPt3 = yPt + this.size;
		
		int[] xPoints = {xPt,xPt2,xPt3};
		int[] yPoints = {yPt,yPt2,yPt3};
		
		g.setColor(ColorUtil.rgb(this.red, this.green, this.blue));
		g.drawPolygon(xPoints, yPoints, 3);
	}
	
	@Override
	public boolean collidesWith(GameObjects otherObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void handleCollision(GameObjects otherObject) {
		// TODO Auto-generated method stub
		
	}

	// implementation of parent method to move object
	public void move() {
		moveHeading();
		double angle = (90 - (this.heading));
		float delX;
		float delY;
		float constraintX;
		float constraintY;
		float newX;
		float newY;
		
		//formulas to calculate how the the spiders new location
		delX = (float) ((Math.cos(Math.toRadians(angle))) * speed);
		delY = (float) ((Math.sin(Math.toRadians(angle))) * speed);
		
		newX = (myCurrLoc.getX() + delX);
		newY = (myCurrLoc.getY() + delY);
		
		//constraints so spider "bounces" off the "walls" of the game
		if((myCurrLoc.getX() + delX) > 1745){
			constraintX = (myCurrLoc.getX() + delX) - 1745;
			newX = 1745 - constraintX;
		}
		
		if((myCurrLoc.getY() + delY) > 1196){
			constraintY = (myCurrLoc.getY() + delY) - 1196;
			newY = 1196 - constraintY;
		}
		
		if((myCurrLoc.getX() + delX) < 0){
			constraintX = Math.abs((myCurrLoc.getX() + delX));
			newX = constraintX;
		}
		
		if((myCurrLoc.getY() + delY) < 0){
			constraintY = Math.abs((myCurrLoc.getX() + delX));
			newY = constraintY;
		}
		
		//new location is accepted
		setLocation(newX,newY);
	}

	@Override
	public void move(int time) {
		// TODO Auto-generated method stub
		moveHeading();
		double angle = (90 - (this.heading));
		float delX;
		float delY;
		float constraintX;
		float constraintY;
		float newX;
		float newY;
		float time1 = (float) time;
		
		float dist = this.speed * (time1/1000);
		
		//formulas to calculate how the the spiders new location
		delX = (float) ((Math.cos(Math.toRadians(angle))) * dist);
		delY = (float) ((Math.sin(Math.toRadians(angle))) * dist);
		
		newX = (myCurrLoc.getX() + delX);
		newY = (myCurrLoc.getY() + delY);
		
		//constraints so spider "bounces" off the "walls" of the game
		if((myCurrLoc.getX() + delX) > 1745){
			constraintX = (myCurrLoc.getX() + delX) - 1745;
			newX = 1745 - constraintX;
		}
		
		if((myCurrLoc.getY() + delY) > 1196){
			constraintY = (myCurrLoc.getY() + delY) - 1196;
			newY = 1196 - constraintY;
		}
		
		if((myCurrLoc.getX() + delX) < 0){
			constraintX = Math.abs((myCurrLoc.getX() + delX));
			newX = constraintX;
		}
		
		if((myCurrLoc.getY() + delY) < 0){
			constraintY = Math.abs((myCurrLoc.getX() + delX));
			newY = constraintY;
		}
		
		//new location is accepted
		setLocation(newX,newY);
	}

	// implementation of grandparent method to get size of object
	@Override
	public int getSize() {
		return this.size;
	}

	// implementation of grandparent method to get color of object
	@Override
	public ColorUtil getColor() {
		return this.myColor;
	}

	// implementation of grandparent method to get location of object
	@Override
	public Point getLocation() {
		return this.myCurrLoc;
	}
	
	//private method that sets the location of the spider, only used in spider class since only the spider controls the way it moves
	public Point setLocation(float newX, float newY) {
		this.myCurrLoc = new Point(newX,newY);
		return this.myCurrLoc;
	}
	
	//parent method for getting the heading of the spider
	@Override
	public int getHeading() {
		return this.heading;
	}
	
	//private method for calculating the spider new heading based on the constraints of spider movement
	private void moveHeading() {
		Random rNum = new Random();
		Random yNum = new Random();
		
		int newHeading = this.heading;
		int b = rNum.nextInt(5);
		int a = yNum.nextInt(5);
		int index = rNum.nextInt(1);
		
		switch (index) {
		case 0:
			newHeading = this.heading + (b);
			break;
		case 1:
			newHeading = this.heading - (a);
			break;}
		
		this.heading = newHeading;
	}

	// implementation of grandparent method to get string of object 
	@Override
	public String toString() {
		return "Spider: loc = " + this.myCurrLoc.getX() + ", " + this.myCurrLoc.getY() + " color=[ " + this.red + ","+ this.green + ","
				+ this.blue + "] heading = " + this.heading + " speed = " + this.speed + " size = " + this.size;
	}

	// grandparent method that isn't used because a spiders color cannot be changed
	@Override
	public void setColor(int a, int b, int c) {
	}

	@Override
	public void handleCollision(GameObjects otherObject, GameWorld yo) {
		// TODO Auto-generated method stub
		
	}

	

}
