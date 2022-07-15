package wolfdev1.com.github.anarchycorev4.listeners

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4

class DeathEvent(var plugin: AnarchyCoreV4) : Listener {
    @EventHandler
    fun onDeath(e: PlayerDeathEvent) {
        val c = plugin.config
        if (c.getBoolean("Players.DeathMessage.Enabled")) {
            val args = e.deathMessage.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val reason = StringBuilder()
            for (i in 1 until args.size) {
                reason.append(args[i]).append(" ")
            }
            e.deathMessage = ChatColor.translateAlternateColorCodes('&', c.getString("Players.DeathMessage.Message")
                    .replace("{reason}", reason)
                    .replace("{player}", e.entity.player.name))
        } else {
            Bukkit.getWorld("world").setGameRuleValue("showDeathMessages", "false")
            Bukkit.getWorld("world_nether").setGameRuleValue("showDeathMessages", "false")
            Bukkit.getWorld("world_end").setGameRuleValue("showDeathMessages", "false")
        }
    }
}