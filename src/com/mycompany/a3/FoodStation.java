package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed{
	
	private int size; //local variable for size of food station
	private int capacity; //local variable for capacity of food station
	private ColorUtil myColor = new ColorUtil(); //local variable for color
	private Point myLoc; //local variable for location
	private int red; // local variable for red color
	private int blue; // local variable for blue color
	private int green; // local variable for green color
	private boolean isSelected;
	

	// food station constructor
	public FoodStation(int s, int r, int g, int b, float x, float y) {
		super(s, r, g, b, x, y);
		this.size = s;
		this.capacity = this.size; // these are equal to each other since they are proportional to each other
		this.myLoc = new Point(x,y); 
		this.red = r;
		this.green = g;
		this.blue = b;
		ColorUtil.rgb(red, green, blue);
	}
	
	@Override
	public void setSelected(boolean b) {
		// TODO Auto-generated method stub
		this.isSelected = b;
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return this.isSelected;
	}

	@Override
	public boolean contains(com.codename1.ui.geom.Point pPtrRelPrnt, com.codename1.ui.geom.Point pCmpRelPrnt) {
		int px = pPtrRelPrnt.getX();
		int py = pPtrRelPrnt.getY();
		int xLoc = pCmpRelPrnt.getX()+ (int)this.myLoc.getX();
		int yLoc = pCmpRelPrnt.getY()+ (int)this.myLoc.getY();
		int width = this.size;
		int height = this.size;
		
		if ( (px >= xLoc) && (px <= xLoc+width) && (py >= yLoc) && (py <= yLoc+height) )
			return true; 
		else 
			return false;
	}

	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pt) {
		// TODO Auto-generated method stub
		int width = this.size;
		int height = this.size;
		int x = pt.getX();
		int y = pt.getY();
		
		String yo = String.valueOf(this.capacity);
		
		if(this.isSelected()) {
			g.setColor(ColorUtil.rgb(this.red, this.green, this.blue));
			g.drawRect((x + (int)this.myLoc.getX()), (y + (int)this.myLoc.getY()), width, height);
			g.setColor(ColorUtil.BLACK);
			g.drawString(yo,(x + (int)this.myLoc.getX()) , (y + (int)this.myLoc.getY()));
		}
		else {
			g.setColor(ColorUtil.rgb(this.red, this.green, this.blue));
			g.fillRect((x + (int)this.myLoc.getX()), (y + (int)this.myLoc.getY()), width, height);
			g.setColor(ColorUtil.BLACK);
			g.drawString(yo,(x + (int)this.myLoc.getX()) , (y + (int)this.myLoc.getY()));
		}
	}
	
	// implementation of grandparent method to get size of object
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return this.size;
	}

	// implementation of grandparent method to get color of object
	@Override
	public ColorUtil getColor() {
		// TODO Auto-generated method stub
		return this.myColor;
	}
	
	// implementation of grandparent method to set color of object
	@Override
	public void setColor(int a, int b, int c) { //used when we need to change the color of food station
		this.myColor = new ColorUtil();
		this.red = a;
		this.green = b;
		this.blue = c;
		ColorUtil.rgb(red, green, blue);
	}

	// implementation of grandparent method to get location of object
	@Override
	public Point getLocation() {
		return this.myLoc;
		// TODO Auto-generated method stub
		
	}

	// method to get capacity of the food station
	public int getCapacity() {
		return this.capacity;
	}
	
	//method to deplete capacity to zero
	public void depleteCapacity () {
		this.capacity = 0;
	}

	// implementation of grandparent method to get string of object
	@Override
	public String toString() {
		return "FoodStation: loc = " + this.myLoc.getX() + ", " + this.myLoc.getY() + " color = [" + this.red + ", "+ this.green 
				+ ", "+ this.blue + "] size = " + this.size + " capacity = " + this.capacity;
	}

	@Override
	public Point setLocation(float a, float b) {
		myLoc.setX(a);
		myLoc.setY(b);
		return myLoc;
	}

	public void setLocationFood(float a, float b) {
		myLoc.setX(a);
		myLoc.setY(b);
	}

	@Override
	public void handleCollision(GameObjects otherObject, GameWorld yo) {
		// TODO Auto-generated method stub
		
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

	

}
