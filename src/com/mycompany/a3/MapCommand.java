package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class MapCommand extends Command {
	GameWorld gw;
	
	public MapCommand(GameWorld g) {
		super("Map");
		this.gw = g;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.map();
	}

}
