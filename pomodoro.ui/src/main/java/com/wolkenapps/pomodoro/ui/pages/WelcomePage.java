package com.wolkenapps.pomodoro.ui.pages;

import static com.wolkenapps.pomodoro.api.qualifiers.Use.Context.PRIMARY;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Context.SECONDARY;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.ALL;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.NOTHING_IS_RUNNING;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.val;

import com.wolkenapps.pomodoro.api.Application;
import com.wolkenapps.pomodoro.api.qualifiers.Use;
import com.wolkenapps.pomodoro.ui.ActionsGroup;
import com.wolkenapps.pomodoro.ui.Page;
import com.wolkenapps.pomodoro.ui.events.UserInterface;

@ApplicationScoped
@Getter
public class WelcomePage implements Page {

    @Inject
    private Event<UserInterface.ChangePage> changePage;

    @Inject
    @Use(whenSituationIs = NOTHING_IS_RUNNING, at = PRIMARY)
    private ActionsGroup                    primaryActions;

    @Inject
    @Use(whenSituationIs = NOTHING_IS_RUNNING, at = SECONDARY)
    private Instance<ActionsGroup>          secondaryActions;

    @Inject
    @Use(whenSituationIs = ALL, at = SECONDARY)
    private ActionsGroup                    commonSecondaryActions;

    private JPanel                          content;

    @Inject
    protected Image                         trayLogo;

    @SuppressWarnings("serial")
    @PostConstruct
    public void setup() {
        content = new PageContentTemplate() {

            @Override
            protected List<ActionsGroup> allSecondariesActions() {
                return aListOfAllActions();
            }

            @Override
            protected ActionsGroup allPrimaryActions() {
                return primaryActions;
            }

            @Override
            protected JPanel createTheContent() {
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(new JLabel(new ImageIcon(trayLogo)), BorderLayout.CENTER);
                return panel;
            }

            @Override
            protected String getTitle() {
                return "Bem Vindo!";
            }

        };
    }

    private List<ActionsGroup> aListOfAllActions() {
        if (secondaryActions.isUnsatisfied())
            return Arrays.asList(commonSecondaryActions);
        val list = ActionsGroup.Utils.toList(secondaryActions);
        list.add(commonSecondaryActions);
        return list;
    }

    public void must(@Observes Application.Started started) {
        changePage.fire(new UserInterface.ChangePage(this));
    }

}
