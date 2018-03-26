package me.fantasyfreedom.fantasyfreedommod.rank;

import lombok.Getter;
import org.bukkit.ChatColor;

public enum Title implements Displayable
{
    SYSTEM_ADMIN("a", "System Admin", ChatColor.DARK_RED, "System Admin"),
    DEVELOPER("a", "Developer", ChatColor.DARK_PURPLE, "Dev"),
    EXECUTIVE("an", "Executive", ChatColor.DARK_AQUA, "Executive"),
    CO_OWNER("a", "Co-Owner", ChatColor.RED, "Co-Owner"),
    QUEEN("a", "Queen", ChatColor.LIGHT_PURPLE, "Queen"), 
    OWNER("the", "Owner", ChatColor.BLUE, "Owner");

    private final String determiner;
    @Getter
    private final String name;
    @Getter
    private final String tag;
    @Getter
    private final String coloredTag;
    @Getter
    private final ChatColor color;

    private Title(String determiner, String name, ChatColor color, String tag)
    {
        this.determiner = determiner;
        this.name = name;
        this.tag = "[" + tag + "]";
        this.coloredTag = ChatColor.DARK_GRAY + "[" + color + tag + ChatColor.DARK_GRAY + "]" + color;
        this.color = color;
    }

    @Override
    public String getColoredName()
    {
        return color + name;
    }

    @Override
    public String getColoredLoginMessage()
    {
        return determiner + " " + color + ChatColor.ITALIC + name;
    }

}
