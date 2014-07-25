package com.wolkenapps.pomodoro.ui;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.wolkenapps.pomodoro.api.Execution;

public class TrayManager implements Runnable {

	@Inject
	private SystemTray	tray;

	@Inject
	private TrayIcon	icon;

	public void startup(@Observes Execution.Started started) {
		EventQueue.invokeLater(this);
	}

	@Override
	public void run() {
		putTheIconOnTray();
	}

	private void putTheIconOnTray() {
		try {
			tray.add(icon);
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}

}
