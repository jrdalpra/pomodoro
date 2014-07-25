package pomodoro.impl;

import java.util.Timer;
import java.util.TimerTask;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import lombok.RequiredArgsConstructor;

import com.wolkenapps.pomodoro.api.Clock;

@ApplicationScoped
public class DefaultTimerService {

    private static final int     SECOND = 1000;

    @Inject
    private Event<Clock.Ticking> ticking;

    @Inject
    private Instance<Timer>      timers;

    private Timer                timer;

    public void whenAskedToTick(@Observes Clock.AskToTick pleaseTick) {
        interruptTheTimer();
        startToTick();
    }

    public void whenAskedToStop(@Observes Clock.AskToStop interrupted) {
        interruptTheTimer();
    }

    private void interruptTheTimer() {
        if (this.timer == null)
            return;
        this.timer.cancel();
    }

    private void startToTick() {
        this.timer = timers.get();
        this.timer.schedule(new TickTack(), SECOND, SECOND);
    }

    @RequiredArgsConstructor
    private class TickTack extends TimerTask {
        @Override
        public void run() {
            ticking.fire(new Clock.Ticking());
        }
    }
}
