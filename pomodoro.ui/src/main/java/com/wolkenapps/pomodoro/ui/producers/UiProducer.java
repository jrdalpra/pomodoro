package com.wolkenapps.pomodoro.ui.producers;

import java.awt.Dimension;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.imageio.ImageIO;
import javax.inject.Inject;

import lombok.val;

import com.wolkenapps.pomodoro.ui.Icons;

@ApplicationScoped
public class UiProducer {

	@Inject
	private Instance<MouseListener>	mouseListernerForTray;

	@Produces
	public SystemTray tray() {
		return SystemTray.getSystemTray();
	}

	@Produces
	public TrayIcon icon(SystemTray tray) {
		val icon = new TrayIcon(iconImage());
		for (val listener : mouseListernerForTray)
			icon.addMouseListener(listener);
		return icon;
	}

	private BufferedImage iconImage() {
		try {
			return ImageIO.read(Icons.tomato_32());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Produces
	public Dimension defaultWindowDimension() {
		return new Dimension(180, 115);
	}

}
