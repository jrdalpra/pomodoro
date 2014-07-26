package com.wolkenapps.pomodoro.ui.actions;

import static com.wolkenapps.pomodoro.api.qualifiers.Use.Context.PRIMARY;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Context.SECONDARY;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.ALL;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.FINISHED;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.INTERRUPTED;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.NOTHING_IS_RUNNING;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.RUNNING;

import java.util.Arrays;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.swing.Action;

import com.wolkenapps.pomodoro.api.Break;
import com.wolkenapps.pomodoro.api.Pomodoro;
import com.wolkenapps.pomodoro.api.qualifiers.Use;
import com.wolkenapps.pomodoro.ui.ActionsGroup;

public class ActionsGroupsProducer {

	@Inject
	private AskToStartAPomodoro		askToStart;

	@Inject
	private AskToRestartAPomodoro	askToRestart;

	@Inject
	private AskToExitTheApp			askToExit;

	@Inject
	private AskToInterruptAPomodoro	askToInterrupt;

	@Inject
	private AskToTakeAShortBreak	askToTakeAShortBreak;

	@Inject
	private AskToTakeALongBreak		askToTakeALongBreak;

	@Inject
	private AskToInterruptTheBreak	askToInterruptTheBreak;

	@Produces
	@Use(whenSituationIs = ALL, at = SECONDARY)
	public ActionsGroup common() {
		return new ActionsGroup.Impl(asList(askToExit));
	}

	@Produces
	@Use(whenSituationIs = NOTHING_IS_RUNNING, at = SECONDARY)
	public ActionsGroup welcomeSecondaryActions() {
		return new ActionsGroup.Impl(asList(askToStart));
	}

	@Produces
	@Use(whenSituationIs = NOTHING_IS_RUNNING, at = PRIMARY)
	public ActionsGroup welcomePrimaryActions() {
		return new ActionsGroup.Impl(asList(askToStart));
	}

	@Produces
	@Use(whenTypeIs = Pomodoro.class, whenSituationIs = RUNNING, at = PRIMARY)
	public ActionsGroup runningPrimaryActions() {
		return new ActionsGroup.Impl(asList(askToInterrupt));
	}

	@Produces
	@Use(whenTypeIs = Pomodoro.class, whenSituationIs = RUNNING, at = SECONDARY)
	public ActionsGroup runningSecondaryActions() {
		return new ActionsGroup.Impl(asList(askToInterrupt));
	}

	@Produces
	@Use(whenTypeIs = Pomodoro.class, whenSituationIs = INTERRUPTED, at = PRIMARY)
	public ActionsGroup interruptedPrimaryActions() {
		return new ActionsGroup.Impl(asList(askToRestart));
	}

	@Produces
	@Use(whenTypeIs = Pomodoro.class, whenSituationIs = INTERRUPTED, at = SECONDARY)
	public ActionsGroup interruptedSecondaryActions() {
		return new ActionsGroup.Impl(asList(askToRestart, askToTakeAShortBreak, askToTakeALongBreak));
	}

	@Produces
	@Use(whenTypeIs = Pomodoro.class, whenSituationIs = FINISHED, at = PRIMARY)
	public ActionsGroup finishedPrimaryActions() {
		return new ActionsGroup.Impl(asList(askToTakeAShortBreak, askToTakeALongBreak));
	}

	@Produces
	@Use(whenTypeIs = Pomodoro.class, whenSituationIs = FINISHED, at = SECONDARY)
	public ActionsGroup finishedSecondaryActions() {
		return new ActionsGroup.Impl(asList(askToTakeAShortBreak, askToTakeALongBreak, askToStart));
	}

	@Produces
	@Use(whenTypeIs = Break.class, whenSituationIs = RUNNING, at = PRIMARY)
	public ActionsGroup breakRunningPrimaryActions() {
		return new ActionsGroup.Impl(asList(askToInterruptTheBreak));
	}

	@Produces
	@Use(whenTypeIs = Break.class, whenSituationIs = RUNNING, at = SECONDARY)
	public ActionsGroup breakRunningSecondaryActions() {
		return new ActionsGroup.Impl(asList(askToStart, askToInterruptTheBreak));
	}

	@Produces
	@Use(whenTypeIs = Break.class, whenSituationIs = INTERRUPTED, at = PRIMARY)
	public ActionsGroup breakInterruptedPrimaryActions() {
		return new ActionsGroup.Impl(asList(askToStart));
	}

	@Produces
	@Use(whenTypeIs = Break.class, whenSituationIs = INTERRUPTED, at = SECONDARY)
	public ActionsGroup breakInterruptedSecondaryActions() {
		return new ActionsGroup.Impl(asList(askToStart));
	}

	@Produces
	@Use(whenTypeIs = Break.class, whenSituationIs = FINISHED, at = PRIMARY)
	public ActionsGroup breakFinishedPrimaryActions() {
		return new ActionsGroup.Impl(asList(askToStart));
	}

	@Produces
	@Use(whenTypeIs = Break.class, whenSituationIs = FINISHED, at = SECONDARY)
	public ActionsGroup breakFinishedSecondaryActions() {
		return new ActionsGroup.Impl(asList(askToStart));
	}

	@SuppressWarnings("unchecked")
	private <T extends Action> Iterable<Action> asList(T... actions) {
		return Arrays.<Action> asList(actions);
	}

}
