package com.wolkenapps.pomodoro.api;

public class Clock {

    public static class Event {
    }

    public static class AskToTick extends Clock.Event {
    }

    public static class Ticking extends Clock.Event {
    }

    public static class AskToStop extends Clock.Event {
    }

}
