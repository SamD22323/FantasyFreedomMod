package me.fantasyfreedom.fantasyfreedommod.command;

import me.fantasyfreedom.fantasyfreedommod.rank.Rank;
import me.fantasyfreedom.fantasyfreedommod.util.FUtil;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Broadcasts the given message as the console, includes sender name.", usage = "/<command> <message>")
public class Command_say extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }

        String message = StringUtils.join(args, " ");

        if (senderIsConsole && FUtil.isFromHostConsole(sender.getName()))
        {
            if (message.equalsIgnoreCase("WARNING: Server is restarting, you will be kicked"))
            {
                FUtil.bcastMsg("Server is going offline.", ChatColor.GRAY);

                for (Player player : server.getOnlinePlayers())
                {
                    player.kickPlayer("FantasyFreedom is getting full reboot. Or we just shut off the server.");
                }

                server.shutdown();

                return true;
            }
        }

        FUtil.bcastMsg(String.format("[FantasyFreedomServer:%s] %s", sender.getName(), message), ChatColor.GREEN);

        return true;
    }
}
