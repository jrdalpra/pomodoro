package com.wolkenapps.pomodoro.api;

import java.util.Calendar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Execution {

	public static class Event {
	}

	@RequiredArgsConstructor
	@Getter
	public static class Started extends Execution.Event {
		private final Calendar when;
	}

	@RequiredArgsConstructor
	@Getter
	public static class Finished extends Execution.Event {
		private final Calendar when;
	}
}
