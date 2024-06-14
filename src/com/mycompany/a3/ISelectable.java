package com.mycompany.a3;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface ISelectable {
	public abstract void setSelected(boolean b);
	public abstract boolean isSelected();
	public abstract boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	public abstract void draw(Graphics g, Point pCmpRelPrnt);
}
