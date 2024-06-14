package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftCommand extends Command{
	private GameWorld gw;
	
	public LeftCommand(GameWorld g) {
		super("Left");
		this.gw = g;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.left();
		gw.map();
	}
}
