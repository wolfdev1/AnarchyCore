package wolfdev1.com.github.anarchycorev4.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Channel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

import java.awt.*;
import java.time.Instant;

public class JoinEvent implements Listener {

    AnarchyCoreV4 plugin;

    public JoinEvent(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        FileConfiguration c = plugin.getConfig();

        e.setJoinMessage(null);

        if(c.getBoolean("Players.FirstJoin.Enabled")) {

            if (!e.getPlayer().hasPlayedBefore()) {
                if(c.getBoolean("Discord.Enabled") && c.getBoolean("Discord.Alerts.FirstPlayerJoin")) {
                        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                                c.getString("Players.FirstJoin.Message").replace("{player}", e.getPlayer().getName())));
                        try {
                            EmbedBuilder eb = new EmbedBuilder()
                                    .setTitle("It is "+e.getPlayer().getName()+" first time on the server")
                                    .setColor(Color.decode("#130c24"))
                                    .setTimestamp(Instant.now())
                                    .setFooter("AnarchyCoreV4")
                                    ;

                            AnarchyCoreV4.jda.getTextChannelById(c.getString("Discord.DiscordChannelId")).sendMessageEmbeds(eb.build()).queue();
                        } catch (NullPointerException ne) {
                            return;
                        }
                }
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            c.getString("Players.FirstJoin.Message").replace("{player}", e.getPlayer().getName())));


                }


            }
        }
        @EventHandler
        public void onQuit(PlayerQuitEvent ev) {

            FileConfiguration c = plugin.getConfig();

        if(c.getBoolean("Staff.DeopOnLeave")) {
         if(ev.getPlayer().isOp()) {
             ev.getPlayer().setOp(false);
         }
        }else
        {
            ev.setQuitMessage(null);
            }
        }
    }

