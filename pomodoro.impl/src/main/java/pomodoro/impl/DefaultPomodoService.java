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
    private Event<Pomodoro.Started>     started;

    @Inject
    private Event<Pomodoro.Running>     running;

    @Inject
    private Event<Pomodoro.Interrupted> interrupted;

    @Inject
    private Event<Pomodoro.Finished>    finished;

    @Inject
    private Event<Clock.AskToTick>      askClockToTick;

    @Inject
    private Event<Clock.AskToStop>      askClockToStop;

    private Pomodoro                    current;

    public void whenAskedToRun(@Observes Pomodoro.AskToStart run) {
        // TODO configure a POMODORO time in minutes
        askClockToTick.fire(new Clock.AskToTick());
        started.fire(new Pomodoro.Started(this.current = new Pomodoro(Calendar.getInstance(), 5)));
    }

    public void whenAskedToInterrupt(@Observes Pomodoro.Interrupt interrupt) {
        if (current == null)
            return;
        askClockToStop.fire(new Clock.AskToStop());
        interrupted.fire(new Pomodoro.Interrupted(current.interrupted()));
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
        finished.fire(new Pomodoro.Finished(running.getWho().finished()));
        askClockToStop.fire(new Clock.AskToStop());
    }

}
