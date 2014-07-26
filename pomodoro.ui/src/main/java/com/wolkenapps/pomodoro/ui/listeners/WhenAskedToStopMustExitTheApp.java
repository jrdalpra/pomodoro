package com.wolkenapps.pomodoro.ui.listeners;

import javax.enterprise.event.Observes;

import com.wolkenapps.pomodoro.api.Application;

public class WhenAskedToStopMustExitTheApp {

	public void whenAskedToStop(@Observes Application.AskToStop stop) {
		System.exit(0);
	}

}
