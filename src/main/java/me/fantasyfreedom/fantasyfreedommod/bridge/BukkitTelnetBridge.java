package me.fantasyfreedom.fantasyfreedommod.bridge;

import java.util.Iterator;
import java.util.Map;
import me.totalfreedom.bukkittelnet.api.TelnetCommandEvent;
import me.totalfreedom.bukkittelnet.api.TelnetPreLoginEvent;
import me.totalfreedom.bukkittelnet.api.TelnetRequestDataTagsEvent;
import me.fantasyfreedom.fantasyfreedommod.FreedomService;
import me.fantasyfreedom.fantasyfreedommod.FantasyFreedomMod;
import me.fantasyfreedom.fantasyfreedommod.admin.Admin;
import me.fantasyfreedom.fantasyfreedommod.rank.Rank;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class BukkitTelnetBridge extends FreedomService
{

    public BukkitTelnetBridge(FantasyFreedomMod plugin)
    {
        super(plugin);
    }

    @Override
    protected void onStart()
    {
    }

    @Override
    protected void onStop()
    {
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onTelnetPreLogin(TelnetPreLoginEvent event)
    {

        final String ip = event.getIp();
        if (ip == null || ip.isEmpty())
        {
            return;
        }

        final Admin admin = plugin.al.getEntryByIpFuzzy(ip);

        if (admin == null || !admin.isActive() || !admin.getRank().hasConsoleVariant())
        {
            return;
        }

        event.setBypassPassword(true);
        event.setName(admin.getName());
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onTelnetCommand(TelnetCommandEvent event)
    {
        if (plugin.cb.isCommandBlocked(event.getCommand(), event.getSender()))
        {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onTelnetRequestDataTags(TelnetRequestDataTagsEvent event)
    {
        final Iterator<Map.Entry<Player, Map<String, Object>>> it = event.getDataTags().entrySet().iterator();
        while (it.hasNext())
        {
            final Map.Entry<Player, Map<String, Object>> entry = it.next();
            final Player player = entry.getKey();
            final Map<String, Object> playerTags = entry.getValue();

            boolean isAdmin = false;
            boolean isTelnetAdmin = false;
            boolean isSeniorAdmin = false;

            final Admin admin = plugin.al.getAdmin(player);
            if (admin != null)
            {
                boolean active = admin.isActive();

                isAdmin = active;
                isSeniorAdmin = active && admin.getRank() == Rank.SENIOR_ADMIN;
                isTelnetAdmin = active && (isSeniorAdmin || admin.getRank() == Rank.TELNET_ADMIN);
            }

            playerTags.put("tfm.admin.isAdmin", isAdmin);
            playerTags.put("tfm.admin.isTelnetAdmin", isTelnetAdmin);
            playerTags.put("tfm.admin.isSeniorAdmin", isSeniorAdmin);

            playerTags.put("tfm.playerdata.getTag", plugin.pl.getPlayer(player).getTag());

            playerTags.put("tfm.essentialsBridge.getNickname", plugin.esb.getNickname(player.getName()));
        }
    }
}
