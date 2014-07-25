package com.wolkenapps.pomodoro.ui.pages;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.plaf.basic.BasicArrowButton;

import lombok.val;

import com.wolkenapps.pomodoro.ui.ActionsGroup;

abstract class PageContentTemplate extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel            header;
    private JPopupMenu        menu;
    private BasicArrowButton  button;

    private JPanel            content;

    private JPanel            footer;
    private JPanel            footerParentPanel;
    private Component         horizontalStrut;
    private Component         horizontalStrut_1;
    private Component         verticalStrut;
    private Component         verticalStrut_1;

    public PageContentTemplate() {
        initUI();
    }

    protected void initUI() {
        setLayout(new BorderLayout(0, 0));
        buildHeader();
        buildFooter();
        buildContent();
    }

    private void buildHeader() {
        add(header = new JPanel(new BorderLayout()), BorderLayout.NORTH);
        buildTitle();
        createAndAddMenuToHeader();
        appendSecondaryActionsToMenu();
    }

    private void buildTitle() {
        header.add(new JLabel(getTitle(), JLabel.CENTER), BorderLayout.CENTER);
    }

    protected abstract String getTitle();

    private void createAndAddMenuToHeader() {
        menu = new JPopupMenu();
        button = new BasicArrowButton(BasicArrowButton.SOUTH);
        button.addActionListener(new OnMenuButtonClicked());
        header.add(button, BorderLayout.EAST);
    }

    private void appendSecondaryActionsToMenu() {
        Integer count = 0;
        val secondaries = allSecondariesActions();
        for (ActionsGroup group : secondaries) {
            appendActionsAt(group);
            count++;
            if (count < secondaries.size())
                menu.addSeparator();
        }
    }

    protected abstract List<ActionsGroup> allSecondariesActions();

    private void appendActionsAt(ActionsGroup group) {
        for (Action action : group.getActions())
            menu.add(menuItemFor(action));
    }

    private JMenuItem menuItemFor(Action action) {
        return new JMenuItem(action);
    }

    private void buildContent() {
        content = createTheContent();
        add(content, BorderLayout.CENTER);
    }

    protected abstract JPanel createTheContent();

    private void buildFooter() {
        buildFooterParent();
        buildFooterItself();
        putLittleStrutsOnFooter();
        appendActionsToFooter();
    }

    private void buildFooterParent() {
        footerParentPanel = new JPanel(new BorderLayout(0, 0));
        add(footerParentPanel, BorderLayout.SOUTH);
    }

    private void buildFooterItself() {
        footer = new JPanel(new BorderLayout(0, 0));
        footerParentPanel.add(footer);
    }

    private void putLittleStrutsOnFooter() {
        footer.setLayout(new GridLayout(1, 0, 0, 0));
        horizontalStrut = Box.createHorizontalStrut(5);
        footerParentPanel.add(horizontalStrut, BorderLayout.WEST);
        horizontalStrut_1 = Box.createHorizontalStrut(5);
        footerParentPanel.add(horizontalStrut_1, BorderLayout.EAST);
        verticalStrut = Box.createVerticalStrut(1);
        footerParentPanel.add(verticalStrut, BorderLayout.NORTH);
        verticalStrut_1 = Box.createVerticalStrut(2);
        footerParentPanel.add(verticalStrut_1, BorderLayout.SOUTH);
    }

    private void appendActionsToFooter() {
        for (Action primary : allPrimaryActions().getActions())
            footer.add(new JButton(primary));
    }

    protected abstract ActionsGroup allPrimaryActions();

    private class OnMenuButtonClicked implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            menu.show(button, 0, button.getHeight());
        }
    }

}
