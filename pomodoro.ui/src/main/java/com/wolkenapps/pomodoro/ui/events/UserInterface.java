package com.wolkenapps.pomodoro.ui.events;

import java.awt.Point;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class UserInterface {

	public static class Event {
	}

	@RequiredArgsConstructor
	@Getter
	public static class TrayClicked extends UserInterface.Event {
		private final Point	where;
	}

	@RequiredArgsConstructor
	@Getter
	public class OpenWindow extends UserInterface.Event {
	}

}
