package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command{
	MapView m;
	GameWorld myG;
	boolean choice;
	
	public PositionCommand(MapView mv, GameWorld g) {
		super("Position");
		// TODO Auto-generated constructor stub
		m = mv;
		myG = g;
	}
	
	public void actionPerformed(ActionEvent evt) {
		m.move(true);
	}
}
