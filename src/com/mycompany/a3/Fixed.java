package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObjects implements ISelectable {
	
	public Fixed(int s, int r, int g, int b, float x, float y) {
		super(s, r, g, b, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract Point setLocation(float a, float b);//abstract class for all GameObjects
}
