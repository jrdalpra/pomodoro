package com.wolkenapps.pomodoro.main;

import java.util.Calendar;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.weld.environment.se.events.ContainerInitialized;

import com.wolkenapps.pomodoro.api.Application;

public class ContainerObserver {

    @Inject
    private Event<Application.Started> executionStarted;

    public void started(@Observes ContainerInitialized started) {
        executionStarted.fire(new Application.Started(Calendar.getInstance()));
    }

}
