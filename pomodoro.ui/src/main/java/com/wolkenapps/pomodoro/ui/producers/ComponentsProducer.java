package com.wolkenapps.pomodoro.ui.producers;

import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseListener;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import lombok.val;

@ApplicationScoped
public class ComponentsProducer {

    @Inject
    private Instance<MouseListener> mouseListernerForTray;

    @Inject
    @Named
    private Image                   trayLogo;

    @Produces
    public SystemTray tray() {
        return SystemTray.getSystemTray();
    }

    @Produces
    public TrayIcon icon(SystemTray tray) {
        val icon = new TrayIcon(trayLogo);
        for (val listener : mouseListernerForTray)
            icon.addMouseListener(listener);
        return icon;
    }

}
