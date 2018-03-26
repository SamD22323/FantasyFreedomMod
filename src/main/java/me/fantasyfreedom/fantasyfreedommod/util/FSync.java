package me.fantasyfreedom.fantasyfreedommod.util;

import me.fantasyfreedom.fantasyfreedommod.FantasyFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FSync
{

    public static void playerMsg(final Player player, final String message)
    {
        final FantasyFreedomMod plugin = FantasyFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                FUtil.playerMsg(player, message);
            }

        }.runTask(plugin);
    }

    public static void playerKick(final Player player, final String reason)
    {
        final FantasyFreedomMod plugin = FantasyFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                player.kickPlayer(reason);
            }

        }.runTask(plugin);
    }

    public static void adminChatMessage(final CommandSender sender, final String message)
    {
        final FantasyFreedomMod plugin = FantasyFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                plugin.cm.adminChat(sender, message);
            }

        }.runTask(plugin);
    }

    public static void autoEject(final Player player, final String kickMessage)
    {
        final FantasyFreedomMod plugin = FantasyFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                plugin.ae.autoEject(player, kickMessage);
            }

        }.runTask(plugin);
    }

    public static void bcastMsg(final String message, final ChatColor color)
    {
        final FantasyFreedomMod plugin = FantasyFreedomMod.plugin();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                FUtil.bcastMsg(message, color);
            }

        }.runTask(plugin);
    }
}
