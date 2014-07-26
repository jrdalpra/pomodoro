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
	private Event<Pomodoro.AskToStart>	pomodoros;

	public AskToStartAPomodoro() {
		super("Start a Pomodoro");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pomodoros.fire(new Pomodoro.AskToStart());
	}

}

@SuppressWarnings("serial")
class AskToRestartAPomodoro extends AbstractAction {

	@Inject
	private Event<Pomodoro.AskToStart>	pomodoros;

	public AskToRestartAPomodoro() {
		super("Restart");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pomodoros.fire(new Pomodoro.AskToStart());
	}

}

@SuppressWarnings("serial")
class AskToInterruptAPomodoro extends AbstractAction {

	@Inject
	private Event<Pomodoro.AskToInterrupt>	pomodoros;

	public AskToInterruptAPomodoro() {
		super("Interrupt");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pomodoros.fire(new Pomodoro.AskToInterrupt());
	}

}

@SuppressWarnings("serial")
class AskToExitTheApp extends AbstractAction {

	@Inject
	private Event<Application.AskToStop>	pomodoros;

	public AskToExitTheApp() {
		super("Exit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		pomodoros.fire(new Application.AskToStop());
	}

}

@SuppressWarnings("serial")
class AskToTakeAShortBreak extends AbstractAction {

	@Inject
	private Event<Break.AskToTakeAShortOne>	breaks;

	public AskToTakeAShortBreak() {
		super("Short Break");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		breaks.fire(new Break.AskToTakeAShortOne());
	}

}

@SuppressWarnings("serial")
class AskToTakeALongBreak extends AbstractAction {

	@Inject
	private Event<Break.AskToTakeALongOne>	breaks;

	public AskToTakeALongBreak() {
		super("Long Break");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		breaks.fire(new Break.AskToTakeALongOne());
	}

}

@SuppressWarnings("serial")
class AskToInterruptTheBreak extends AbstractAction {

	@Inject
	private Event<Break.AskToInterrupt>	breaks;

	public AskToInterruptTheBreak() {
		super("Interrupt");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		breaks.fire(new Break.AskToInterrupt());
	}

}