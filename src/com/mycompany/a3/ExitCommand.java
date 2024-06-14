package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command {
	private GameWorld gw;
	
	public ExitCommand(GameWorld gw2) {
		super("Exit");
		this.gw = gw2;
	}
	
	public void actionPerformed(ActionEvent evt) {
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?","Ok", "Cancel");
				if (bOk){
					System.out.println("You have failed the ant ='(");
					gw.exit();
				}
				else
					System.out.println("The ant lives to fight another day");
	}
}
