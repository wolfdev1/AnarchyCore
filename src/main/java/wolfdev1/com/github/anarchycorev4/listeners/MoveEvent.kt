package wolfdev1.com.github.anarchycorev4.listeners

import com.destroystokyo.paper.event.player.PlayerTeleportEndGatewayEvent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4

class MoveEvent(var plugin: AnarchyCoreV4) : Listener {
    @EventHandler
    fun onMove(e: PlayerMoveEvent) {
        val x = e.player.location.blockX
        val z = e.player.location.blockZ
        val l = Location(e.player.world, z.toDouble(), 120.0, x.toDouble())
        val c = plugin.config
        if (!e.player.isOp) {
            if (c.getBoolean("Players.DisableElytraOnLowTps.Status")) {
                if (e.player.isGliding) {
                    val tps = Bukkit.getServer().tps[0]
                    if (tps < c.getInt("Players.DisableElytraOnLowTps.Tps")) {
                        e.player.isGliding = false
                        e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString(
                                "Players.DisableElytraOnLowTps.Message")))
                    }
                }
            }
            if (!c.getBoolean("Dimensions.Nether.NetherTop.Enabled")) {
                if (e.player.world.environment == World.Environment.NETHER && e.player.location == l) {
                    e.player.teleport(l)
                    e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("Dimensions.Nether.NetherTop.Message")))
                }
            }
        }
    }

    @EventHandler
    fun onEndCityTravel(e: PlayerTeleportEndGatewayEvent) {
        val c = plugin.config
        if (!e.player.isOp) {
            if (!c.getBoolean("End.Gateway.Enabled")) {
                e.isCancelled = true
                e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("End.Gateway.Message")))
            }
        }
    }
}