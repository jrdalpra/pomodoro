package com.wolkenapps.pomodoro.main;

import java.util.Calendar;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.wolkenapps.pomodoro.api.Execution;

public class Startup {

	public static void main(String[] args) {
		val weld = new Weld("com.wolkenapps.pomorodo");
		val container = weld.initialize();
		Runtime.getRuntime().addShutdownHook(new Shutdown(weld, container));
	}

	@RequiredArgsConstructor
	private static class Shutdown extends Thread {

		private final Weld			weld;
		private final WeldContainer	container;

		@Override
		public void run() {
			container.event().fire(new Execution.Finished(Calendar.getInstance()));
			weld.shutdown();
		}
	}

}
