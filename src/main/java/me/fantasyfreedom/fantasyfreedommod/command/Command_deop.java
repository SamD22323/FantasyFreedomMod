package me.fantasyfreedom.fantasyfreedommod.command;

import me.fantasyfreedom.fantasyfreedommod.rank.Rank;
import me.fantasyfreedom.fantasyfreedommod.util.DepreciationAggregator;
import me.fantasyfreedom.fantasyfreedommod.util.FUtil;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Deop a player.", usage = "/<command> <playername>")
public class Command_deop extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        OfflinePlayer player = null;

        for (Player onlinePlayer : server.getOnlinePlayers())
        {
            if (args[0].equalsIgnoreCase(onlinePlayer.getName()))
            {
                player = onlinePlayer;
            }
        }

        // if the player is not online
        if (player == null)
        {
            player = DepreciationAggregator.getOfflinePlayer(server, args[0]);
        }

        FUtil.adminAction(sender.getName(), "De-opping " + player.getName(), false);

        player.setOp(false);

        return true;
    }
}
