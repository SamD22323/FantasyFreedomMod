package me.fantasyfreedom.fantasyfreedommod.command;

import lombok.Getter;
import me.fantasyfreedom.fantasyfreedommod.FreedomService;
import me.fantasyfreedom.fantasyfreedommod.FantasyFreedomMod;
import me.fantasyfreedom.fantasyfreedommod.util.FLog;
import net.pravian.aero.command.handler.SimpleCommandHandler;
import org.bukkit.ChatColor;

public class CommandLoader extends FreedomService
{

    @Getter
    private final SimpleCommandHandler<FantasyFreedomMod> handler;

    public CommandLoader(FantasyFreedomMod plugin)
    {
        super(plugin);

        handler = new SimpleCommandHandler<>(plugin);
    }

    @Override
    protected void onStart()
    {
        handler.clearCommands();
        handler.setExecutorFactory(new FreedomCommandExecutor.FreedomExecutorFactory(plugin));
        handler.setCommandClassPrefix("Command_");
        handler.setPermissionMessage(ChatColor.RED + "You do not have permission to use this command.");
        handler.setOnlyConsoleMessage(ChatColor.RED + "This command can only be used from the console.");
        handler.setOnlyPlayerMessage(ChatColor.RED + "This command can only be used by players.");

        handler.loadFrom(FreedomCommand.class.getPackage());
        handler.registerAll("FantasyFreedomMod", true);

        FLog.info("Loaded " + handler.getExecutors().size() + " commands.");
    }

    @Override
    protected void onStop()
    {
        handler.clearCommands();
    }

}
