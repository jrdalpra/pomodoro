package com.wolkenapps.pomodoro.ui.actions;

import java.awt.event.ActionEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.swing.AbstractAction;

import com.wolkenapps.pomodoro.api.Application;
import com.wolkenapps.pomodoro.api.Break;
import com.wolkenapps.pomodoro.api.Pomodoro;

@SuppressWarnings("serial")
class AskToStartAPomodoro extends AbstractAction {

    @Inject
    private Event<Pomodoro.AskToStart> askToStart;

    public AskToStartAPomodoro() {
        super("Start");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        askToStart.fire(new Pomodoro.AskToStart());
    }

}

@SuppressWarnings("serial")
class AskToRestartAPomodoro extends AbstractAction {

    @Inject
    private Event<Pomodoro.AskToStart> askToRun;

    public AskToRestartAPomodoro() {
        super("Restart");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        askToRun.fire(new Pomodoro.AskToStart());
    }

}

@SuppressWarnings("serial")
class AskToInterruptAPomodoro extends AbstractAction {

    @Inject
    private Event<Pomodoro.Interrupt> askToInterrupt;

    public AskToInterruptAPomodoro() {
        super("Interrupt");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        askToInterrupt.fire(new Pomodoro.Interrupt());
    }

}

@SuppressWarnings("serial")
class AskToExitTheApp extends AbstractAction {

    @Inject
    private Event<Application.AskToStop> askToStopTheApp;

    public AskToExitTheApp() {
        super("Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        askToStopTheApp.fire(new Application.AskToStop());
    }

}

@SuppressWarnings("serial")
class AskToTakeAShortBreak extends AbstractAction {

    @Inject
    private Event<Break.AskToTakeAShortOne> askToTakeABreak;

    public AskToTakeAShortBreak() {
        super("Short Break");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        askToTakeABreak.fire(new Break.AskToTakeAShortOne());
    }

}

@SuppressWarnings("serial")
class AskToTakeALongBreak extends AbstractAction {

    @Inject
    private Event<Break.AskToTakeALongOne> askToTakABreak;

    public AskToTakeALongBreak() {
        super("Long Break");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        askToTakABreak.fire(new Break.AskToTakeALongOne());
    }

}