package pomodoro.impl;

import java.util.Calendar;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.wolkenapps.pomodoro.api.Break;
import com.wolkenapps.pomodoro.api.Clock;

@ApplicationScoped
public class DefaulBreakService {

    @Inject
    private Event<Break.Started>   started;

    @Inject
    private Event<Clock.AskToTick> askClockToTick;

    @Inject
    private Event<Clock.AskToStop> askClockToStop;

    private Break                  current;

    public void must(@Observes Break.AskToTakeAShortOne askedToTakeAShortOne) {
        started.fire(new Break.Started(current = new Break(Calendar.getInstance(), 5 * 60)));
        askClockToTick.fire(new Clock.AskToTick());
    }

    public void must(@Observes Break.AskToTakeALongOne askedToTakeALongOne) {
        started.fire(new Break.Started(current = new Break(Calendar.getInstance(), 10 * 60)));
        askClockToTick.fire(new Clock.AskToTick());
    }

    public void must(@Observes Clock.Ticking ticking) {
        
    }

}
