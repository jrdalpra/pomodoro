package com.wolkenapps.pomodoro.ui.producers;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.enterprise.inject.Produces;

public class DimensionsProducer {
    @Produces
    public Rectangle screenSize() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
    }

    @Produces
    public Dimension defaultWindowDimension() {
        return new Dimension(180, 115);
    }
}
