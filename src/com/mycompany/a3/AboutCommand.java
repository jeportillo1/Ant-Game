package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;

public class AboutCommand extends Command {

	public AboutCommand() {
		super("About");
	}

	public void actionPerformed(ActionEvent evt) {
		Button showPopup = new Button("Show Popup");
		Dialog d = new Dialog("About");
	        TextArea popupBody = new TextArea("Juan Enrique Portillo\nCSC133\nA3", 6, 15);
	        popupBody.setUIID("PopupBody");
	        popupBody.setEditable(false);
	        d.setLayout(new BorderLayout());
	        d.add(BorderLayout.CENTER, popupBody);
	        d.showPopupDialog(showPopup);
	}
}
