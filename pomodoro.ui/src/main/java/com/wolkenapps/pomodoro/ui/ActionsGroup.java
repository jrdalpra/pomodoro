package com.wolkenapps.pomodoro.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;

public interface ActionsGroup {

    @RequiredArgsConstructor
    @Getter
    public static class Impl implements ActionsGroup {
        private final Iterable<Action> actions;
    }

    public static class Empty implements ActionsGroup {
        @Override
        public Iterable<Action> getActions() {
            return Collections.emptyList();
        }
    }

    public static class Utils {

        public static List<ActionsGroup> toList(Iterable<ActionsGroup> actions) {
            val list = new ArrayList<ActionsGroup>();
            for (val group : actions)
                list.add(group);
            return list;
        }
    }

    Iterable<Action> getActions();

}
