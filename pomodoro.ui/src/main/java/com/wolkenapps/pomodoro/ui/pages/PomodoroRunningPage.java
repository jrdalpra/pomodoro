package com.wolkenapps.pomodoro.ui.pages;

import static com.wolkenapps.pomodoro.api.qualifiers.Use.Context.PRIMARY;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Context.SECONDARY;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.ALL;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.RUNNING;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.val;

import com.wolkenapps.pomodoro.api.Pomodoro;
import com.wolkenapps.pomodoro.api.qualifiers.Use;
import com.wolkenapps.pomodoro.ui.ActionsGroup;
import com.wolkenapps.pomodoro.ui.Page;
import com.wolkenapps.pomodoro.ui.components.TimeLabel;
import com.wolkenapps.pomodoro.ui.events.UserInterface;
import com.wolkenapps.pomodoro.ui.events.UserInterface.ChangePage;

@ApplicationScoped
@Getter
public class PomodoroRunningPage implements Page {

    @Inject
    private Event<UserInterface.ChangePage> changePage;

    @Inject
    @Use(whenTypeIs = Pomodoro.class, whenSituationIs = RUNNING, at = PRIMARY)
    private Instance<ActionsGroup>          primaryActions;

    @Inject
    @Use(whenTypeIs = Pomodoro.class, whenSituationIs = RUNNING, at = SECONDARY)
    private Instance<ActionsGroup>          secondaryActions;

    @Inject
    @Use(whenSituationIs = ALL, at = SECONDARY)
    private ActionsGroup                    commonsActions;

    private JPanel                          content;
    private TimeLabel                       counter;

    @SuppressWarnings("serial")
    @PostConstruct
    public void initUI() {
        content = new PageContentTemplate() {

            @Override
            protected JPanel createTheContent() {
                val innerContent = new JPanel(new BorderLayout());
                innerContent.add(counter = new TimeLabel(), BorderLayout.CENTER);
                return innerContent;
            }

            @Override
            protected List<ActionsGroup> allSecondariesActions() {
                if (secondaryActions.isUnsatisfied())
                    return Arrays.asList(commonsActions);
                val list = ActionsGroup.Utils.toList(secondaryActions);
                list.add(commonsActions);
                return list;
            }

            @Override
            protected ActionsGroup allPrimaryActions() {
                if (primaryActions.isUnsatisfied())
                    return new ActionsGroup.Empty();
                return primaryActions.get();
            }

            @Override
            protected String getTitle() {
                return "Pomodoro";
            }
        };
    }

    public void must(@Observes Pomodoro.Started started) {
        view(started.getWho());
        changePage.fire(new ChangePage(PomodoroRunningPage.this));
    }

    public void must(@Observes Pomodoro.Running running) {
        view(running.getWho());
    }

    private void view(Pomodoro who) {
        counter.view(who.getRemainingMinutes(), who.getRemainingSeconds());
    }

}
