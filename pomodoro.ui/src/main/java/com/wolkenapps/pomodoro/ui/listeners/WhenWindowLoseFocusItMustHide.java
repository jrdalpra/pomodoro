package com.wolkenapps.pomodoro.ui.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class WhenWindowLoseFocusItMustHide implements WindowFocusListener {

	@Override
	public void windowGainedFocus(WindowEvent arg0) {
	}

	@Override
	public void windowLostFocus(WindowEvent focusLosted) {
		focusLosted.getWindow().setVisible(false);
	}

}
