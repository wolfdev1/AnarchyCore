package wolfdev1.com.github.anarchycorev4.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerKickEvent
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4

class KickEvent(var plugin: AnarchyCoreV4) : Listener {
    @EventHandler
    fun onKick(e: PlayerKickEvent) {
        val c = plugin.config
        if (e.reason.equals("Kicked for spamming", ignoreCase = true)) {
            e.isCancelled = true
        }
    }
}