package com.wolkenapps.pomodoro.main;

import java.util.Calendar;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.wolkenapps.pomodoro.api.Application;

public class Startup {

    public static void main(String[] args) {
        val weld = new Weld("com.wolkenapps.pomorodo");
        Runtime.getRuntime().addShutdownHook(new Shutdown(weld, weld.initialize()));
    }

    @RequiredArgsConstructor
    private static class Shutdown extends Thread {

        private final Weld          weld;
        private final WeldContainer container;

        @Override
        public void run() {
            container.event().fire(new Application.Stopping(Calendar.getInstance()));
            weld.shutdown();
        }
    }

}
