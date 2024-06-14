package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelCommand extends Command{
	private GameWorld g;
	public AccelCommand(GameWorld yo) {
		super("Accelerate");
		this.g = yo;
	}
	
	public void actionPerformed(ActionEvent evt) {
		g.accel();
		g.map();
	}

}
