package pomodoro.impl;

import java.util.Calendar;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.wolkenapps.pomodoro.api.Break;
import com.wolkenapps.pomodoro.api.Clock;
import com.wolkenapps.pomodoro.api.Pomodoro;

@ApplicationScoped
public class DefaulBreakService {

	@Inject
	private Event<Break.Started>		started;

	@Inject
	private Event<Break.Running>		running;

	@Inject
	private Event<Break.Finished>		finished;

	@Inject
	private Event<Break.Interrupted>	interrupted;

	private Break						current;

	public void must(@Observes Break.AskToTakeAShortOne askedToTakeAShortOne) {
		// TODO get config from some repository
		started.fire(new Break.Started(current = new Break(Calendar.getInstance(), 5)));
	}

	public void must(@Observes Break.AskToTakeALongOne askedToTakeALongOne) {
		// TODO get config from some repository
		started.fire(new Break.Started(current = new Break(Calendar.getInstance(), 15)));
	}

	public void must(@Observes Clock.Ticking ticking) {
		if (!isRunningABreak())
			return;
		running.fire(new Break.Running(current.countASecond()));
	}

	public void must(@Observes Break.AskToInterrupt interrupt) {
		interrupted.fire(new Break.Interrupted(current.interrupt()));
	}

	public void must(@Observes Break.Running running) {
		if (!running.getWho().isCompleted())
			return;
		finished.fire(new Break.Finished(current.finish()));
	}

	public void must(@Observes Pomodoro.Started startedAPomodoro) {
		if (!isRunningABreak())
			return;
		current.interrupt();
	}

	private boolean isRunningABreak() {
		return current != null && current.isRunning();
	}

}
