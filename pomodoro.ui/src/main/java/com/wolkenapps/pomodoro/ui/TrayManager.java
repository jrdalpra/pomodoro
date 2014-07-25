package com.wolkenapps.pomodoro.ui;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.wolkenapps.pomodoro.api.Application;
import com.wolkenapps.pomodoro.ui.events.UserInterface;
import com.wolkenapps.pomodoro.ui.utils.Closest;

public class TrayManager implements Runnable {

    @Inject
    private Dimension                       window;

    @Inject
    private SystemTray                      tray;

    @Inject
    private Closest                         atClosest;

    @Inject
    private Event<UserInterface.OpenWindow> openWindow;

    @Inject
    private TrayIcon                        icon;

    public void startup(@Observes Application.Started started) {
        invokeLater(this);
    }

    @Override
    public void run() {
        putTheIconOnTray();
    }

    private void putTheIconOnTray() {
        try {
            tray.add(icon);
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void trayWasClicked(@Observes UserInterface.TrayClicked trayClicked) {
        openWindow.fire(new UserInterface.OpenWindow(atClosest.basedOn(trayClicked.getWhere()).determineBasedOn((window))));
    }
}
