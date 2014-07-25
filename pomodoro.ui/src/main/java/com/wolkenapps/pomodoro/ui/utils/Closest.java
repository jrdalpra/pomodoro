package com.wolkenapps.pomodoro.ui.utils;

import static java.util.Arrays.asList;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import javax.inject.Inject;

import lombok.RequiredArgsConstructor;
import lombok.val;

public class Closest {

    @Inject
    private Rectangle screenSize;

    public Closest.ScreenLocation basedOn(Point mouseCursor) {
        ScreenLocation closest = null;
        for (val location : insideAllLocations(mouseCursor))
            if (closest == null || location.distanceFromScreenSide() < closest.distanceFromScreenSide())
                closest = location;
        return closest;
    }

    private List<ScreenLocation> insideAllLocations(Point mouseCursor) {
        return asList(new Top(screenSize, mouseCursor), new Bottom(screenSize, mouseCursor), new Left(screenSize, mouseCursor), new Right(screenSize, mouseCursor));
    }

    public static abstract class ScreenLocation {

        protected static final int MARGIN = 5;

        public Point determineBasedOn(Dimension window) {
            return new Point(withXBasedOn(window), withYBasedOn(window));
        }

        protected abstract int distanceFromScreenSide();

        protected abstract int withXBasedOn(Dimension window);

        protected abstract int withYBasedOn(Dimension window);
    }

    @RequiredArgsConstructor
    private static class Top extends ScreenLocation {
        private final Rectangle screen;
        private final Point     mouse;

        @Override
        public int distanceFromScreenSide() {
            return mouse.y;
        }

        @Override
        protected int withXBasedOn(Dimension window) {
            return mouse.x - window.width / 2;
        }

        @Override
        protected int withYBasedOn(Dimension window) {
            return screen.y + MARGIN;
        }
    }

    @RequiredArgsConstructor
    private static class Bottom extends ScreenLocation {
        private final Rectangle screen;
        private final Point     mouse;

        @Override
        public int distanceFromScreenSide() {
            return screen.height - mouse.y;
        }

        @Override
        protected int withXBasedOn(Dimension window) {
            return mouse.x - window.width / 2;
        }

        @Override
        protected int withYBasedOn(Dimension window) {
            return screen.height - window.height - MARGIN;
        }
    }

    @RequiredArgsConstructor
    private static class Left extends ScreenLocation {
        private final Rectangle screen;
        private final Point     mouse;

        @Override
        public int distanceFromScreenSide() {
            return mouse.x;
        }

        @Override
        protected int withXBasedOn(Dimension window) {
            return screen.x + MARGIN;
        }

        @Override
        protected int withYBasedOn(Dimension window) {
            return mouse.y - window.height / 2;
        }
    }

    @RequiredArgsConstructor
    private static class Right extends ScreenLocation {
        private final Rectangle screen;
        private final Point     mouse;

        @Override
        public int distanceFromScreenSide() {
            return screen.width - mouse.x;
        }

        @Override
        protected int withXBasedOn(Dimension window) {
            return screen.width - window.width - MARGIN;
        }

        @Override
        protected int withYBasedOn(Dimension window) {
            return mouse.y - window.height / 2;
        }
    }
}
