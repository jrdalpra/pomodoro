package com.wolkenapps.pomodoro.api;

import java.io.Serializable;
import java.util.Calendar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Break implements Serializable {

	private static final long	serialVersionUID	= 1L;

	public static class Event {
	}

	public static class AskToTakeALongOne extends Break.Event {
	}

	public static class AskToTakeAShortOne extends Break.Event {
	}

	@RequiredArgsConstructor
	@Getter
	public static class Started extends Break.Event {
		private final Break	who;
	}

	@RequiredArgsConstructor
	@Getter
	public static class Running extends Break.Event {
		private final Break	who;
	}

	@RequiredArgsConstructor
	@Getter
	public static class Finished extends Break.Event {
		private final Break	who;
	}

	public static class AskToInterrupt extends Break.Event {
	}

	@RequiredArgsConstructor
	@Getter
	public static class Interrupted extends Break.Event {
		private final Break	who;
	}

	private final Calendar	whenStarted;

	private final Integer	totalSecondsToRun;

	private Calendar		finishedWhen;

	private Calendar		interruptedWhen;

	private Integer			secondsRunning	= 0;

	public Break finish() {
		finishedWhen = Calendar.getInstance();
		return this;
	}

	public Break interrupt() {
		interruptedWhen = Calendar.getInstance();
		return this;
	}

	public Break countASecond() {
		this.secondsRunning = this.secondsRunning + 1;
		return this;
	}

	public Integer getTotalRemainingSeconds() {
		return totalSecondsToRun - this.secondsRunning;
	}

	public Integer getRemainingMinutes() {
		return (int) Math.floor((double) getTotalRemainingSeconds() / 60.0);
	}

	public Integer getRemainingSeconds() {
		return getTotalRemainingSeconds() % 60;
	}

	public boolean isCompleted() {
		return getTotalRemainingSeconds() <= 0;
	}

	public boolean isRunning() {
		return finishedWhen == null && interruptedWhen == null && !isCompleted();
	}

}
