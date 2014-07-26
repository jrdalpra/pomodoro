package pomodoro.impl;

import java.util.Calendar;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.wolkenapps.pomodoro.api.Clock;
import com.wolkenapps.pomodoro.api.Pomodoro;

@ApplicationScoped
public class DefaultPomodoService {

	@Inject
	private Event<Pomodoro.Started>		started;

	@Inject
	private Event<Pomodoro.Running>		running;

	@Inject
	private Event<Pomodoro.Interrupted>	interrupted;

	@Inject
	private Event<Pomodoro.Finished>	finished;

	private Pomodoro					current;

	public void whenAskedToRun(@Observes Pomodoro.AskToStart run) {
		// TODO configure a POMODORO time in minutes
		started.fire(new Pomodoro.Started(this.current = new Pomodoro(Calendar.getInstance(), 5)));
	}

	public void whenAskedToInterrupt(@Observes Pomodoro.AskToInterrupt interrupt) {
		if (current == null)
			return;
		interrupted.fire(new Pomodoro.Interrupted(current.interrupt()));
	}

	public void whenClockIsTicking(@Observes Clock.Ticking ticking) {
		if (current == null || !current.isRunning())
			return;
		current.countASecond();
		running.fire(new Pomodoro.Running(current));
	}

	public void whenPomodoroIsRunning(@Observes Pomodoro.Running running) {
		if (!running.getWho().isCompleted())
			return;
		finished.fire(new Pomodoro.Finished(running.getWho().finish()));
	}

}
