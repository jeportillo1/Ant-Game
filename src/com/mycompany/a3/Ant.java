package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Ant extends Moveable implements ISteerable{
	private int size; //local variable for size
	private int heading; //local variable for heading
	private int speed; //local variable for speed
	private int maxSpeed; // local variable for max speed
	private int foodLevel; //local variable for food level
	private int foodConsumptionRate; //local variable for food consumption rate
	private int health; //local variable for health
	private int lastFlagReached; //local variable for last flag reached
	private Point myCurrLoc; //local variable for my current location
	private ColorUtil myColor = new ColorUtil(); // local variable for my color
	private int red;// local variable for red color
	private int blue;// local variable for blue color
	private int green;//local variable for green color
	private GameWorld g;
	
	// ant constructor
	public Ant(int s, int sp, int ms, int fl, int fcr, int lfr, int r, int g, int b, int head, float x, float y) {
		super(s, r, g, b, x, y, head, sp);
		this.size = s;
		this.health = 10;
		this.speed = sp;
		this.maxSpeed = ms;
		this.foodLevel = fl;
		this.foodConsumptionRate = fcr;
		this.lastFlagReached = lfr;
		this.red = r;
		this.green = g;
		this.blue = b;
		ColorUtil.rgb(red, green, blue);
		this.heading = head;
		this.myCurrLoc = new Point(x,y);
		
		
	}
	
	@Override
	public void draw(Graphics g, com.codename1.ui.geom.Point pt) {
		// TODO Auto-generated method stub
		int x = pt.getX();
		int y = pt.getY();
		int r = (int)(this.size/2);
		int width = 2 * r;
		int height = 2 * r;
		int startAngle = 0;
		int arcAngle = 360;
		g.setColor(ColorUtil.rgb(this.red, this.green, this.blue));
		g.fillArc((x + (int) myCurrLoc.getX()), (y + (int) myCurrLoc.getY()), width, height, startAngle, arcAngle);
		
	}
	
	// implementation of parent method to move object
	
	public void move(int time) {
		double angle = (90 - (this.heading));
		float delX;
		float delY;
		float time1 = (float) time;
		float dist = this.speed * (time1/1000);
		
		
		dist = (float) Math.ceil(dist);
		
		delX = (float) (Math.cos(Math.toRadians(angle)) * dist);
		delX = (float) Math.ceil(delX);
		delY = (float) (Math.sin(Math.toRadians(angle)) * dist);
		delY = (float) Math.ceil(delY);
		
		setLocation((myCurrLoc.getX() + delX),(myCurrLoc.getY() + delY ));
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
	
	//method to change the hue of red for the ant
	public void redColor() {
		int newRed;
		newRed = this.red - 10;
		this.red = newRed;
		ColorUtil.rgb(red, green, blue);
	}
	
	//method to get the red hue of ant
	public int getRedHue() {
		return red;
	}
	
	//method to get the green hue of ant
	public int getGreenHue() {
		return green;
	}
	
	//method to get the blue hue of ant
	public int getBlueHue() {
		return blue;
	}
	//private method to set the location of ant, it is later applied to the move method
	@Override
	public Point setLocation(float newX, float newY) {
		this.myCurrLoc = new Point(newX,newY);
		return this.myCurrLoc;
	}

	// implementation of grandparent method to get location of object
	@Override
	public Point getLocation() {
		return this.myCurrLoc;
	}
	
	// implementation of grandparent method to set heading of object
	@Override
	public int setHeading(int newHeading) {
		this.heading = newHeading;
		return this.heading;
	}
	
	// implementation of grandparent method to get heading of object
	@Override
	public int getHeading() {
		return this.heading;
	}
	
	// implementation of interface method to steer ant to the left
	@Override
	public void goLeft() {
		setHeading((this.heading) - 10);
	}

	// implementation of interface method to steer ant to the right
	@Override
	public void goRight() {
		setHeading((this.heading) + 10);
	}

	//method to get the speed value of the ant
	public int getSpeed() {
		return this.speed;
	}
	
	//method to tell ant to speed up
	public int speedUp() {
		this.speed = this.speed + 5;
		return setSpeed(this.speed);
	}
	
	//method to tell ant to slow down
	public int slowDown() {
		this.speed = this.speed - 5;
		return setSpeed(this.speed);
	}
	
	//private method to to constrain the speed so it never goes over the max speed 
	private int setSpeed(int newSpeed) {
		if(newSpeed == maxSpeed)
			return this.speed = newSpeed;
		else if(newSpeed > maxSpeed) {
			return this.speed = maxSpeed;
		}
		else if(newSpeed < 0)
			return this.speed = 0;
		else
		    return this.speed = newSpeed;
	}

	//method to get max speed value
	public int getMaxSpeed() {
		return this.maxSpeed;
	}
	
	//method to get current food level
	public int getFoodLevel() {
		return this.foodLevel;
	}

	// method to decrease the food level of ant
	public void decreaseFoodLevel() {
		this.foodLevel = this.foodLevel - this.foodConsumptionRate;
		if (this.foodLevel == 0) {
			this.maxSpeed = 0;
		}
		else if(this.foodLevel < 0) {
			this.foodLevel = 0;
		}
		
	}
	
	//method to increase the food level of an
	public void increaseFoodLevel(int add) {
		this.foodLevel = this.foodLevel + add;
	}
	
	//method to get the food consumption rate
	public int getFoodConsumptionRate() {
		return this.foodConsumptionRate;
	}
	
	//method to get health level
	public int getHealth() {
		return this.health;
	}

	//method to decrease health level
	public void decreaseHealth() {
		this.health = this.health - 1;
		this.maxSpeed = (int)((float)this.maxSpeed * (((float)this.health) / ((float) 10))); //so max speed decrease as health decreases
		
		//if statement checks to see if current speed is higher then max speed
		if(this.speed > this.maxSpeed)
			setSpeed(this.speed); 
		
		if(this.health < 0)
			this.health = 0;
	}

	public void resetHealth() {
		this.health = 10;
	}
	
	//method to get number of last flag reached
	public int getLastFlagReached() {
		return this.lastFlagReached;
	}

	//method to increment last flag reached
	public void nextFlagReached() {
		this.lastFlagReached = this.lastFlagReached + 1;
	}

	// implementation of grandparent method to get string of object
	@Override
	public String toString() {
		return "Ant: loc = " + this.myCurrLoc.getX() + ", " + this.myCurrLoc.getY() + " color = ["  + this.red + ","+ this.green + ","+ this.blue 
				+ "] heading = " + this.heading + " speed = " + this.speed + " size = " + this.size + " maxSpeed = " + this.maxSpeed 
				+ " foodConsumptionRate = " + this.foodConsumptionRate;
	}
	
	//method to get string data of ant like health level, last flag reached and food level
	public String toStringData() {
		return "Last Flag Reached : " + this.lastFlagReached + " Food Level : " + this.foodLevel + " Health : " + this.health ;
	}

	//method to set color not used because the color after collision with spider only affects the red value not all other colors
	@Override
	public void setColor(int a, int b, int c) {
	}

	@Override
	public boolean collidesWith(GameObjects obj) {
		boolean result = false;
		int objSize = obj.getSize();
		
		int thisCenterX = (int)this.myCurrLoc.getX() + (objSize/2); // find centers
		int thisCenterY = (int)this.myCurrLoc.getY() + (objSize/2);
		int otherCenterX = (int)obj.getLocation().getX() + (objSize/2);
		int otherCenterY = (int)obj.getLocation().getY() + (objSize/2);// find dist between centers (use square, to avoid taking roots)
		int dx = thisCenterX - otherCenterX;
		int dy = thisCenterY - otherCenterY;
		int distBetweenCentersSqr = (dx*dx + dy*dy);
		// find square of sum of radii
		int thisRadius = objSize/2;
		int otherRadius = objSize/2;
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius+ otherRadius*otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) { 
		result = true ; 
		}

		return result;
	}

	@Override
	public void handleCollision(GameObjects otherObject, GameWorld yo) {
		this.g = yo;
		if(otherObject instanceof Spider) {
			g.colliSpider();
		}
		else if(otherObject instanceof FoodStation) {
			g.colliFood();
		}
		else if(otherObject instanceof Flag) {
			int flag = ((Flag) otherObject).getSequenceNumber();
			g.colliFlag(flag);
		}
		else
			System.out.println("what collision");
	}

	@Override
	public void handleCollision(GameObjects otherObject) {
		// TODO Auto-generated method stub
		
	}

	

	
	
}
