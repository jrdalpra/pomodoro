package com.wolkenapps.pomodoro.api;

import java.util.Calendar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Application {

	public static class Event {
	}

	@RequiredArgsConstructor
	@Getter
	public static class Started extends Application.Event {
		private final Calendar	when;
	}

	@RequiredArgsConstructor
	@Getter
	public static class AskToStop extends Application.Event {
	}

	@RequiredArgsConstructor
	@Getter
	public static class Stopping extends Application.Event {
		private final Calendar	when;
	}

}
