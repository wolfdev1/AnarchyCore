package wolfdev1.com.github.anarchycorev4.listeners

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerPickupItemEvent
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4

class PlayerPickup(var plugin: AnarchyCoreV4) : Listener {
    @EventHandler
    fun onPickup(e: PlayerPickupItemEvent) {
        val c = plugin.config
        val b = e.item.itemStack.type
        if (!e.player.isOp) {
            if (!c.getBoolean("Dimensions.End.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Overworld.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Nether.IllegalBlocks.Status")) {
                e.isCancelled = false
            } else {
                if (c.getBoolean("IllegalBlocks.Bedrock.Status")) {
                    if (b == Material.BEDROCK) {
                        e.isCancelled = true
                        e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.Bedrock.Message")))
                    }
                }
                if (c.getBoolean("IllegalBlocks.Barrier.Status")) {
                    if (b == Material.BARRIER) {
                        e.isCancelled = true
                        e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.Barrier.Message")))
                    }
                }
                if (c.getBoolean("IllegalBlocks.EndPortal.Status")) {
                    if (b == Material.ENDER_PORTAL) {
                        e.isCancelled = true
                        e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.EndPortal.Message")))
                    }
                }
                if (c.getBoolean("IllegalBlocks.MobSpawner.Status")) {
                    if (b == Material.MOB_SPAWNER) {
                        e.isCancelled = true
                        e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.MobSpawner.Message")))
                    }
                }
            }
        }
    }
}