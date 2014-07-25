package com.wolkenapps.pomodoro.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowFocusListener;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.swing.JFrame;

import com.wolkenapps.pomodoro.api.Execution;
import com.wolkenapps.pomodoro.ui.events.UserInterface;

public class Window extends JFrame {

	private static final long				serialVersionUID	= 1L;

	@Inject
	private Dimension						dimension;

	@Inject
	private Instance<WindowFocusListener>	focusListeners;

	@PostConstruct
	public void initUI() {
		// setUndecorated(true);
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(dimension);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addAllFocusListeners();
	}

	private void addAllFocusListeners() {
		if (!focusListeners.isUnsatisfied())
			for (WindowFocusListener listener : focusListeners)
				addWindowFocusListener(listener);
	}

	public void startup(@Observes Execution.Started started) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				setVisible(true);
			}
		});
	}

	public void appear(@Observes UserInterface.OpenWindow open) {
		setLocation(open.getWhere());
		setVisible(true);
	}

}
