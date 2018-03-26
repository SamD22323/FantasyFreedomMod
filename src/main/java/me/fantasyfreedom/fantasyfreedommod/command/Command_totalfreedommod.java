package me.fantasyfreedom.fantasyfreedommod.command;

import me.fantasyfreedom.fantasyfreedommod.FantasyFreedomMod;
import me.fantasyfreedom.fantasyfreedommod.config.ConfigEntry;
import me.fantasyfreedom.fantasyfreedommod.config.MainConfig;
import me.fantasyfreedom.fantasyfreedommod.rank.Rank;
import me.fantasyfreedom.fantasyfreedommod.util.FLog;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * See https://github.com/TotalFreedom/License - This file may not be edited or removed.
 */
@CommandPermissions(level = Rank.NON_OP, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about TotalFreedomMod or reloads it", usage = "/<command> [reload]", aliases = "tfm")
public class Command_totalfreedommod extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 1)
        {
            if (!args[0].equals("reload"))
            {
                return false;
            }

            if (!plugin.al.isAdmin(sender))
            {
                noPerms();
                return true;
            }

            plugin.config.load();
            plugin.services.stop();
            plugin.services.start();

            final String message = String.format("%s v%s reloaded.",
                    FantasyFreedomMod.pluginName,
                    FantasyFreedomMod.pluginVersion);

            msg(message);
            FLog.info(message);
            return true;
        }

        FantasyFreedomMod.BuildProperties build = FantasyFreedomMod.build;
        msg("TotalFreedomMod for 'Total Freedom', the original all-op server.", ChatColor.GOLD);
        msg("Running on " + ConfigEntry.SERVER_NAME.getString() + ".", ChatColor.GOLD);
        msg("Created by Madgeek1450 and Prozza.", ChatColor.GOLD);
        msg(String.format("Version "
                + ChatColor.BLUE + "%s %s.%s " + ChatColor.GOLD + "("
                + ChatColor.BLUE + "%s" + ChatColor.GOLD + ")",
                build.codename,
                build.version,
                build.number,
                build.head), ChatColor.GOLD);
        msg(String.format("Compiled "
                + ChatColor.BLUE + "%s" + ChatColor.GOLD + " by "
                + ChatColor.BLUE + "%s",
                build.date,
                build.author), ChatColor.GOLD);
        msg("Visit " + ChatColor.AQUA + "http://github.com/TotalFreedom/TotalFreedomMod"
                + ChatColor.GREEN + " for more information.", ChatColor.GREEN);

        return true;
    }
}
