package wolfdev1.com.github.anarchycorev4.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

import java.util.ArrayList;

public class BlackList implements Listener {

    public static ArrayList<Player> blacklist = new ArrayList<Player>(100);

    AnarchyCoreV4 plugin;

    public BlackList(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent e) {
        FileConfiguration c = plugin.getConfig();

        if(c.getBoolean
                ("Players.Blacklist.Enabled")) {
            String[] bl =
                    c.getString
                            ("Players.Blacklist.Players").split(" ");

            StringBuilder sb
                    = new StringBuilder();
            for(int i = 2; i <
                    bl.length; i++)
            {
                sb.append(bl[i])
                        .append(" ");
            }

            if(sb.toString().contains
                    (e.getPlayerProfile().getName())) {
                    e.setLoginResult
                            (AsyncPlayerPreLoginEvent.Result.KICK_BANNED);
                    e.setKickMessage
                            (ChatColor.translateAlternateColorCodes('&', "\n" +
                            "&0[&6Anarchy&r&dCoreV4&0] &4&lError!&r &3Your &4user&r&3 is on the blacklist"));
            }
        }
    }
}
