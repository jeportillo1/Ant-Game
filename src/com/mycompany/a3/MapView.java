package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import com.codename1.ui.plaf.Border;


public class MapView extends Container implements Observer {
	private GameObjectsCollection go;
	private GameWorld g;
	private int posButtonCounter;
	private boolean move = false;
	private int newX;
	private int newY;
	
	public MapView(GameObjectsCollection yo, GameWorld ye) {
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.rgb(255, 0, 0)));
		this.g = ye;
		this.go = this.g.getMyGame();
		//this.repaint();
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Point pt = new Point (this.getX(), this.getY());
		IIterator myIt2 = go.getIterator();

		
		FoodStation f = null;
		
		
		IIterator myIt = go.getIterator();

		while (myIt.hasNext()) {
			if(myIt.getCurr() instanceof FoodStation ) {
				f = (FoodStation) myIt.getCurr(); //assign f to the food station object in ArrayList to access FoodStation Class methods
				f.draw(g, pt);
			}
			myIt.getNext();
		}
		
		Spider s = null;
		IIterator myIt3 = go.getIterator();

		while (myIt3.hasNext()) {
			if(myIt3.getCurr() instanceof Spider ) {
				s = (Spider) myIt3.getCurr(); 
				s.draw(g, pt);
			}
			myIt3.getNext();
		}
		
		Flag fl = null;
		IIterator myIt4 = go.getIterator();

		while (myIt4.hasNext()) {
			if(myIt4.getCurr() instanceof Flag ) {
				fl = (Flag) myIt4.getCurr(); 
				fl.draw(g, pt);
			}
			myIt4.getNext();
		}
		
		Ant ant = null;
		while (myIt2.hasNext()) {
			if(myIt2.getCurr() instanceof Ant ) {
				ant = (Ant) myIt2.getCurr(); //assign ant to the ant object in ArrayList to access Ant Class methods
				break;
			}
			else
				myIt2.getNext();
		}
		ant.draw(g, pt);
		
	}
	
	@Override
	public void pointerPressed(int x, int y) {
		x = x - getParent().getAbsoluteX();
		y = y - getParent().getAbsoluteY();
		Point pPtrRelPrnt = new Point(x, y); 
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		this.newX = x;
		this.newY = y;
		
		Flag fl = null;
		IIterator myIt7 = go.getIterator();

		while (myIt7.hasNext()) {
			if(myIt7.getCurr() instanceof Flag ) {
				fl = (Flag) myIt7.getCurr(); 
				if(fl.contains(pPtrRelPrnt, pCmpRelPrnt)) {
					fl.setSelected(true);
				}
				else {
					fl.setSelected(false);
				}
			}
			myIt7.getNext();
		}
		
		FoodStation f = null;
		IIterator myIt8 = go.getIterator();

		while (myIt8.hasNext()) {
			if(myIt8.getCurr() instanceof FoodStation ) {
				f = (FoodStation) myIt8.getCurr(); //assign f to the food station object in ArrayList to access FoodStation Class methods
				if(f.contains(pPtrRelPrnt, pCmpRelPrnt)) {
					f.setSelected(true);
				}
				else {
					f.setSelected(false);
				}
			}
			myIt8.getNext();
		}
		
		repaint();
		
	}

	@Override
	public void update(Observable obs, Object data) {
		if(obs == g) {
			this.go = this.g.getMyGame();
		}
		
	}
	
	public void move(boolean m) {
		this.move = m;
		if(move == true) {
			Flag fl = null;
			IIterator myIt7 = go.getIterator();
			
			while (myIt7.hasNext()) {
				if(myIt7.getCurr() instanceof Flag ) {
					fl = (Flag) myIt7.getCurr(); 
					if(fl.isSelected()) {
						fl.setLocation(this.newX, this.newY);
					}
				}
				myIt7.getNext();
			}
			
			FoodStation f = null;
			IIterator myIt8 = go.getIterator();

			while (myIt8.hasNext()) {
				if(myIt8.getCurr() instanceof FoodStation ) {
					f = (FoodStation) myIt8.getCurr(); //assign f to the food station object in ArrayList to access FoodStation Class methods
					if(f.isSelected()) {
						f.setLocation(this.newX, this.newY);
					}
				}
				myIt8.getNext();
			}
		}
			
		
	}
	
	public int getCounter() {
		return this.posButtonCounter;
	}

	public int getNewX() {
		return newX;
	}


	public int getNewY() {
		return newY;
	}
}
