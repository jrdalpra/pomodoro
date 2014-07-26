package com.wolkenapps.pomodoro.api;

import java.io.Serializable;
import java.util.Calendar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Pomodoro implements Serializable {

    private static final long serialVersionUID = 1L;

    public static abstract class Event {
    }

    @RequiredArgsConstructor
    @Getter
    public static class AskToStart extends Pomodoro.Event {
    }

    @RequiredArgsConstructor
    @Getter
    public static class Started extends Pomodoro.Event {
        private final Pomodoro who;
    }

    @RequiredArgsConstructor
    @Getter
    public static class Running extends Pomodoro.Event {
        private final Pomodoro who;
    }

    @RequiredArgsConstructor
    @Getter
    public static class AskToInterrupt extends Pomodoro.Event {
    }

    @RequiredArgsConstructor
    @Getter
    public static class Interrupted extends Pomodoro.Event {
        private final Pomodoro who;
    }

    @RequiredArgsConstructor
    @Getter
    public static class Finished extends Pomodoro.Event {
        private final Pomodoro who;
    }

    private final Calendar startWhen;
    private final Integer  totalSecondsToRun;
    private Calendar       finishedWhen;
    private Calendar       interruptedWhen;

    private Integer        secondsRunning = 0;

    public Pomodoro finish() {
        finishedWhen = Calendar.getInstance();
        return this;
    }

    public Pomodoro interrupt() {
        interruptedWhen = Calendar.getInstance();
        return this;
    }

    public Pomodoro countASecond() {
        this.secondsRunning = this.secondsRunning + 1;
        return this;
    }

    public Integer getTotalRemainingSeconds() {
        return totalSecondsToRun - this.secondsRunning;
    }

    public Integer getRemainingMinutes() {
        return (int) Math.floor((double) getTotalRemainingSeconds() / 60.0);
    }

    public Integer getRemainingSeconds() {
        return getTotalRemainingSeconds() % 60;
    }

    public boolean isCompleted() {
        return getTotalRemainingSeconds() <= 0;
    }

    public boolean isRunning() {
        return finishedWhen == null && interruptedWhen == null && !isCompleted();
    }

}
