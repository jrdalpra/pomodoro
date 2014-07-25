package com.wolkenapps.pomodoro.ui.producers;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.inject.Produces;
import javax.imageio.ImageIO;
import javax.inject.Named;

public class ImagesProducer {

    public static InputStream tomato_32px() {
        return ImagesProducer.class.getResourceAsStream("tomato-32.png");
    }

    @Produces
    @Named
    public Image trayLogo() {
        // TODO get the best logo
        return imageFrom(tomato_32px());
    }

    private Image imageFrom(InputStream stream) {
        try {
            return ImageIO.read(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
