package com.wolkenapps.pomodoro.ui;

import static javax.swing.SwingUtilities.invokeLater;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowFocusListener;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wolkenapps.pomodoro.api.Application;
import com.wolkenapps.pomodoro.ui.events.UserInterface;

@ApplicationScoped
public class Window extends JFrame implements Runnable {

    private static final long             serialVersionUID = 1L;

    @Inject
    private Dimension                     dimension;

    @Inject
    private Instance<WindowFocusListener> focusListeners;

    private JPanel                        content;

    @PostConstruct
    public void initUI() {
        setUndecorated(true);
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(dimension);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addAllFocusListeners();
        setContentPane(content = new JPanel(new BorderLayout()));
    }

    private void addAllFocusListeners() {
        if (!focusListeners.isUnsatisfied())
            for (WindowFocusListener listener : focusListeners)
                addWindowFocusListener(listener);
    }

    public void startup(@Observes Application.Started started) {
        invokeLater(this);
    }

    @Override
    public void run() {
        setVisible(false);
    }

    public void whenAskedToAppear(@Observes UserInterface.OpenWindow open) {
        setLocation(open.getWhere());
        setVisible(true);
    }

    public void whenAskedToChangePage(@Observes final UserInterface.ChangePage pageChanged) throws InvocationTargetException, InterruptedException {
        invokeLater(new Runnable() {
            public void run() {
                changeTo(pageChanged.getPageToRender());
            }
        });
    }

    private void changeTo(Page page) {
        content.removeAll();
        content.add(page.getContent(), BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }

}
