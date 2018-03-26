package me.fantasyfreedom.fantasyfreedommod.command;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import me.fantasyfreedom.fantasyfreedommod.rank.Rank;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandPermissions
{

    Rank level();

    SourceType source();

    boolean blockHostConsole() default false;
}
