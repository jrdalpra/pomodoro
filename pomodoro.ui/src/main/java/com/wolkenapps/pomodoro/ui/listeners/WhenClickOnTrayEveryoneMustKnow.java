package com.wolkenapps.pomodoro.ui.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.wolkenapps.pomodoro.ui.events.UserInterface;
import com.wolkenapps.pomodoro.ui.events.UserInterface.TrayClicked;

public class WhenClickOnTrayEveryoneMustKnow extends MouseAdapter {

	@Inject
	private Event<UserInterface.TrayClicked>	trayClicked;

	@Override
	public void mousePressed(MouseEvent mouse) {
		trayClicked.fire(new TrayClicked(mouse.getLocationOnScreen()));
	}

}
