package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("Help");
	}
	
	public void actionPerformed(ActionEvent evt) {
		Button showPopup = new Button("Show Popup");
		Dialog d = new Dialog("Help");
		TextArea popupBody = new TextArea("Key Actions\n'a' is Accelerate Ant\n'b' is to Brake Ant\n'l' is go left\n'r' is go right\n'm' map view\n", 10, 20);
	    popupBody.setUIID("PopupBody");
	    popupBody.setEditable(false);
	    d.setLayout(new BorderLayout());
	    d.add(BorderLayout.CENTER, popupBody);
	    d.showPopupDialog(showPopup);
	}

}
