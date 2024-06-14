package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {
private GameWorld gw;
	
	public SoundCommand() {
		super("Sound");
		// TODO Auto-generated constructor stub
	}
	
	public void setGameWorld(GameWorld g) {
		this.gw = g;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.toggleSound();
	}
}
