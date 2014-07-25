package com.wolkenapps.pomodoro.main;

import java.util.Calendar;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import com.wolkenapps.pomodoro.api.Execution;

public class ContainerObserver {

	@Inject
	private Event<Execution.Started> executionStarted;

	public void started(@Observes ContainerInitialized started) {
		System.out.println(19);
		executionStarted.fire(new Execution.Started(Calendar.getInstance()));
	}

}
