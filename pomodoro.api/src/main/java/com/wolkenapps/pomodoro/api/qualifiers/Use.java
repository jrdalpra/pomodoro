package com.wolkenapps.pomodoro.api.qualifiers;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ FIELD, METHOD })
@javax.inject.Qualifier
public @interface Use {

    @SuppressWarnings("rawtypes")
    Class whenTypeIs() default Object.class;

    Use.Situation whenSituationIs() default Situation.ALL;

    Use.Context at() default Context.SECONDARY;

    public static enum Situation {
        ALL,
        NOTHING_IS_RUNNING,
        RUNNING,
        INTERRUPTED,
        FINISHED;
    }

    public static enum Context {
        PRIMARY,
        SECONDARY;
    }

}