package com.wolkenapps.pomodoro.ui.events;

import java.awt.Point;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.wolkenapps.pomodoro.ui.Page;

public class UserInterface {

    public static class Event {
    }

    @RequiredArgsConstructor
    @Getter
    public static class TrayClicked extends UserInterface.Event {
        private final Point where;
    }

    @RequiredArgsConstructor
    @Getter
    public static class OpenWindow extends UserInterface.Event {
        private final Point where;
    }

    @RequiredArgsConstructor
    @Getter
    public static class ChangePage extends UserInterface.Event {
        private final Page pageToRender;
    }

}
