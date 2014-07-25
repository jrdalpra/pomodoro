package com.wolkenapps.pomodoro.ui.listeners;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.wolkenapps.pomodoro.api.Application;
import com.wolkenapps.pomodoro.api.Pomodoro;

public class WhenAskedToStopMustExitTheApp {

    @Inject
    private Event<Pomodoro.Interrupt> interrupt;

    public void whenAskedToStop(@Observes Application.AskToStop stop) {
        interrupt.fire(new Pomodoro.Interrupt());
        System.exit(0);
    }

}
