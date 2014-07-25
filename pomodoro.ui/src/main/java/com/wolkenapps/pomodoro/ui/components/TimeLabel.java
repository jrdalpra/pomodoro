package com.wolkenapps.pomodoro.ui.components;

import static java.lang.String.format;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class TimeLabel extends JLabel {

    public TimeLabel() {
        super("", CENTER);
    }

    public void view(Integer minutes, Integer seconds) {
        setText(format("%02d:%02d", minutes, seconds));
    }
}
