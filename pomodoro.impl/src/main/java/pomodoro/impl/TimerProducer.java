package pomodoro.impl;

import java.util.Timer;

import javax.enterprise.inject.Produces;

public class TimerProducer {

    @Produces
    public Timer timer() {
        return new Timer("pomodoro");
    }

}
