package com.wolkenapps.pomodoro.api;

import java.io.Serializable;
import java.util.Calendar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Pomodoro implements Serializable {

	private static final long serialVersionUID = 1L;

	public static abstract class Event {
	}

	@RequiredArgsConstructor
	@Getter
	public static class Run extends Pomodoro.Event {
	}

	@RequiredArgsConstructor
	@Getter
	public static class Started extends Pomodoro.Event {
		private final Pomodoro current;
		private final Calendar when;
	}

	@RequiredArgsConstructor
	@Getter
	public static class Interrupt extends Pomodoro.Event {
		private final Pomodoro who;
	}

	@RequiredArgsConstructor
	@Getter
	public static class Finished extends Pomodoro.Event {
		private final Pomodoro current;
		private final Calendar when;
	}

	@RequiredArgsConstructor
	@Getter
	public static class Interrupted extends Pomodoro.Event {
		private final Pomodoro current;
		private final Calendar when;
	}

	private final Calendar startWhen;
	private final Calendar endedWhen;
	private final Calendar interruptedWhen;

}
