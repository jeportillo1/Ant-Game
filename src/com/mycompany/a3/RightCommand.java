package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RightCommand extends Command{
	private GameWorld gw;
	
	public RightCommand(GameWorld g) {
		super("Right");
		this.gw = g;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.right();
		gw.map();
	}
}
