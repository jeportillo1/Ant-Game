package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public abstract class GameObjects implements IDrawable, ICollider {
	@SuppressWarnings("unused")
	private Point myCurrLoc; //local variable for my current location
	@SuppressWarnings("unused")
	private ColorUtil myColor = new ColorUtil(); // local variable for my color
	private int red;// local variable for red color
	private int blue;// local variable for blue color
	private int green;//local variable for green color
	@SuppressWarnings("unused")
	private int size; //local variable for size
	
	public GameObjects (int s, int r, int g, int b, float x, float y) {
		this.size = s;
		this.red =r;
		this.green= g;
		this.blue = b;
		ColorUtil.rgb(red, green, blue);
		this.myCurrLoc = new Point(x,y);
	}
	
	public abstract int getSize(); //abstract class for all GameObjects 
	public abstract ColorUtil getColor(); //abstract class for all GameObjects 
	public abstract Point getLocation(); //abstract class for all GameObjects 
	public abstract Point setLocation(float a, float b);//abstract class for all GameObjects 
	public abstract String toString(); //abstract class for all GameObjects 
	public abstract void setColor(int a, int b, int c); //abstract class for all GameObjects 

	
}
