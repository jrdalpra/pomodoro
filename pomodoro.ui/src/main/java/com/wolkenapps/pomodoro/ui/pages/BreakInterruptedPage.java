package com.wolkenapps.pomodoro.ui.pages;

import static com.wolkenapps.pomodoro.api.qualifiers.Use.Context.PRIMARY;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Context.SECONDARY;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.ALL;
import static com.wolkenapps.pomodoro.api.qualifiers.Use.Situation.INTERRUPTED;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.Getter;
import lombok.val;

import com.wolkenapps.pomodoro.api.Break;
import com.wolkenapps.pomodoro.api.qualifiers.Use;
import com.wolkenapps.pomodoro.ui.ActionsGroup;
import com.wolkenapps.pomodoro.ui.Page;
import com.wolkenapps.pomodoro.ui.events.UserInterface;

@ApplicationScoped
@Getter
public class BreakInterruptedPage implements Page {

	@Inject
	private Event<UserInterface.ChangePage>	changePage;

	@Inject
	@Use(whenTypeIs = Break.class, whenSituationIs = INTERRUPTED, at = PRIMARY)
	private Instance<ActionsGroup>			primaryActions;

	@Inject
	@Use(whenTypeIs = Break.class, whenSituationIs = INTERRUPTED, at = SECONDARY)
	private Instance<ActionsGroup>			secondaryActions;

	@Inject
	@Use(whenSituationIs = ALL, at = SECONDARY)
	private ActionsGroup					commonsActions;

	private JPanel							content;

	@SuppressWarnings("serial")
	@PostConstruct
	public void initUI() {
		content = new PageContentTemplate() {

			@Override
			protected JPanel createTheContent() {
				val innerContent = new JPanel(new BorderLayout());
				innerContent.add(new JLabel("Break Interrupted", JLabel.CENTER), BorderLayout.CENTER);
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
				return "Break";
			}
		};
	}

	public void must(@Observes Break.Interrupted interrupted) {
		changePage.fire(new UserInterface.ChangePage(this));
	}

}
