package wolfdev1.com.github.anarchycorev4.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

import java.util.List;

public class Chat implements Listener {

    AnarchyCoreV4 plugin;

    public Chat(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        FileConfiguration c = plugin.getConfig();

        if(c.getBoolean("Chat.Enabled")) {

            if(c.getBoolean("Chat.CustomChat")) {

                List<String> bl = c.getStringList("Chat.Word-Blacklist");
                boolean isBlacklisted = false;
                for (String word : bl) {
                    if (e.getMessage().toLowerCase().contains(word)) {
                        isBlacklisted = true;
                        break;
                    }
                }

                if(isBlacklisted) {
                    e.setCancelled(true);
                }else{
                    if(e.getMessage().startsWith(">"))  {
                        e.setFormat
                                (
                                        c.getString("Chat.ChatFormat")
                                                .replace("{player}",
                                                        e.getPlayer().getName())
                                                .replace("{message}",
                                                        ChatColor.GREEN + e.getMessage().toLowerCase())
                                );

                    }else {

                        e.setFormat
                                (
                                        c.getString("Chat.ChatFormat")
                                                .replace("{player}",
                                                        e.getPlayer().getName())
                                                .replace("{message}",
                                                        e.getMessage().toLowerCase())
                                );

                    }
                }

            }else{
                List<String> bl = c.getStringList("Chat.Word-Blacklist");
                boolean isBlacklisted = false;
                for (String word : bl) {
                    if (e.getMessage().toLowerCase().contains(word)) {
                        isBlacklisted = true;
                        break;
                    }
                }

                if(isBlacklisted) {
                    e.setCancelled(true);
                }else{
                    if(e.getMessage().startsWith(">"))  {
                        e.setFormat
                                ("<"+e.getPlayer().getName()+"> "
                                        + ChatColor.GREEN +
                                        e.getMessage().toLowerCase());
                    }else{
                        e.setFormat
                                ("<"+e.getPlayer().getName()+"> "
                                        + e.getMessage().
                                        toLowerCase());
                    }
                }
            }
        }else{
            e.setCancelled(true);
        }

    }
}
