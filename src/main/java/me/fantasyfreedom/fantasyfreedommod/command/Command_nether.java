package me.fantasyfreedom.fantasyfreedommod.command;

import me.fantasyfreedom.fantasyfreedommod.rank.Rank;
import me.fantasyfreedom.fantasyfreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.NON_OP, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Goto the nether.", usage = "/<command>")
public class Command_nether extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        plugin.wm.gotoWorld(playerSender, server.getWorlds().get(0).getName() + "_nether");
        return true;
    }
}
