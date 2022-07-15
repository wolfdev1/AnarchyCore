package wolfdev1.com.github.anarchycorev4.listeners

import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4
import java.util.*

class Chat(var plugin: AnarchyCoreV4) : Listener {
    @EventHandler
    fun onChat(e: AsyncPlayerChatEvent) {
        val c = plugin.config
        if (c.getBoolean("Chat.Enabled")) {
            if (c.getBoolean("Chat.CustomChat")) {
                val bl = c.getStringList("Chat.Word-Blacklist")
                var isBlacklisted = false
                for (word in bl) {
                    if (e.message.lowercase(Locale.getDefault()).contains(word!!)) {
                        isBlacklisted = true
                        break
                    }
                }
                if (isBlacklisted) {
                    e.isCancelled = true
                } else {
                    if (e.message.startsWith(">")) {
                        e.format = c.getString("Chat.ChatFormat")
                                .replace("{player}",
                                        e.player.name)
                                .replace("{message}",
                                        ChatColor.GREEN.toString() + e.message.lowercase(Locale.getDefault()))
                    } else {
                        e.format = c.getString("Chat.ChatFormat")
                                .replace("{player}",
                                        e.player.name)
                                .replace("{message}",
                                        e.message.lowercase(Locale.getDefault()))
                    }
                }
            } else {
                val bl = c.getStringList("Chat.Word-Blacklist")
                var isBlacklisted = false
                for (word in bl) {
                    if (e.message.lowercase(Locale.getDefault()).contains(word!!)) {
                        isBlacklisted = true
                        break
                    }
                }
                if (isBlacklisted) {
                    e.isCancelled = true
                } else {
                    if (e.message.startsWith(">")) {
                        e.format = ("<" + e.player.name + "> "
                                + ChatColor.GREEN +
                                e.message.lowercase(Locale.getDefault()))
                    } else {
                        e.format = ("<" + e.player.name + "> "
                                + e.message.lowercase(Locale.getDefault()))
                    }
                }
            }
        } else {
            e.isCancelled = true
        }
    }
}