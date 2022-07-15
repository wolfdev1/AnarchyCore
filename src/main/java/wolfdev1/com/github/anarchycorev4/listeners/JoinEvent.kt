package wolfdev1.com.github.anarchycorev4.listeners

import net.dv8tion.jda.api.EmbedBuilder
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4
import java.awt.Color
import java.time.Instant

class JoinEvent(var plugin: AnarchyCoreV4) : Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        val c = plugin.config
        e.joinMessage = null
        if (c.getBoolean("Players.FirstJoin.Enabled")) {
            if (!e.player.hasPlayedBefore()) {
                if (c.getBoolean("Discord.Enabled") && c.getBoolean("Discord.Alerts.FirstPlayerJoin")) {
                    Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                            c.getString("Players.FirstJoin.Message").replace("{player}", e.player.name)))
                    try {
                        val eb = EmbedBuilder()
                                .setTitle("It is " + e.player.name + " first time on the server")
                                .setColor(Color.decode("#130c24"))
                                .setTimestamp(Instant.now())
                                .setFooter("AnarchyCoreV4")
                        AnarchyCoreV4.Companion.jda!!.getTextChannelById(c.getString("Discord.DiscordChannelId"))!!.sendMessageEmbeds(eb.build()).queue()
                    } catch (ne: NullPointerException) {
                        return
                    }
                }
                Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                        c.getString("Players.FirstJoin.Message").replace("{player}", e.player.name)))
            }
        }
    }

    @EventHandler
    fun onQuit(ev: PlayerQuitEvent) {
        val c = plugin.config
        if (c.getBoolean("Staff.DeopOnLeave")) {
            if (ev.player.isOp) {
                ev.player.isOp = false
            }
        } else {
            ev.quitMessage = null
        }
    }
}