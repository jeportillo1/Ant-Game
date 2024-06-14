package com.mycompany.a3;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed {
	
	private int size; //local variable for size
	private int sequenceNumber; //local variable for sequence number
	private ColorUtil myColor = new ColorUtil(); //local variable for color
	private Point myLoc; //local variable for location
	private int red; //local variable for red color
	private int blue; //local variable for blue color
	private int green; //local variable for green color
	private boolean isSelected;
	
	// flag constructor
	public Flag(int r, int g, int b, int s, int c, float x,  float y) {
		super(c, r, g, b, x, y);
		ColorUtil.rgb(r, g, b);
		this.sequenceNumber = s;
		this.size = c;
		this.myLoc = new Point(x,y);
		this.red = r;
		this.green = g;
		this.blue = b;
		
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
	public void draw(Graphics g, com.codename1.ui.geom.Point pt) {
		int xPt = (int) this.myLoc.getX() + pt.getX();
		int yPt = (int) this.myLoc.getY()+ pt.getY();
		
		int xPt2 = xPt + this.size;
		int yPt2 = yPt;
		
		int xPt3 = xPt + (int) (this.size / 2);
		int yPt3 = yPt + this.size;
		
		int[] xPoints = {xPt,xPt2,xPt3};
		int[] yPoints = {yPt,yPt2,yPt3};
		
		String yo = String.valueOf(this.sequenceNumber);
		
		if(this.isSelected()) {
			g.setColor(ColorUtil.rgb(this.red, this.green, this.blue));
			g.drawPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawStringBaseline(yo, xPt3-2 , yPt3 - (int) (this.size / 2));
		}
		else {
			g.setColor(ColorUtil.rgb(this.red, this.green, this.blue));
			g.fillPolygon(xPoints, yPoints, 3);
			g.setColor(ColorUtil.BLACK);
			g.drawStringBaseline(yo, xPt3-2 , yPt3 - (int) (this.size / 2));
		}
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
		return this.myLoc;
		
	}

	// method to get the sequence number of flag
	public int getSequenceNumber() {
		return this.sequenceNumber;
	}

	// method to set the sequence number of flag
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber; //only used if flag is set to wrong sequence number
	}

	// implementation of grandparent method to get string of object
	@Override
	public String toString() {
		
		return "Flag: loc= " + this.myLoc.getX() + ", " + this.myLoc.getY() + " color=[ " + this.red + ","+ this.green + ","
		+ this.blue + "]" + "size = " + this.size + " seqNum = " + this.sequenceNumber;
	}

	// implementation of grandparent method to set color of object
	@Override
	public void setColor(int a, int b, int c) { //empty because flag doesn't allow the change of color
	}

	@Override
	public Point setLocation(float a, float b) {
		myLoc.setX(a);
		myLoc.setY(b);
		return myLoc;
	}

	public void setLocationFlag(float a, float b) {
		myLoc.setX(a);
		myLoc.setY(b);
	}
	
	@Override
	public void handleCollision(GameObjects otherObject, GameWorld yo) {
		// TODO Auto-generated method stub
		
	}

	
}
