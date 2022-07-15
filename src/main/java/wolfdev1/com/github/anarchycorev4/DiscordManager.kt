package wolfdev1.com.github.anarchycorev4

import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit
import org.bukkit.ChatColor

class DiscordManager(var plugin: AnarchyCoreV4) : ListenerAdapter() {
    override fun onReady(event: ReadyEvent) {
        val config = plugin.config
        if (event.jda.getTextChannelById(config.getString("Discord.DiscordChannelId")) == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('8',
                    "&l&9[AnarchyCore] &r&7Discord text channel id set in config.yml is invalid, set an a valid text channel id."))
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('8',
                    "&l&9[AnarchyCore] &r&7Discord integration successfully loaded"))
        }
    }
}