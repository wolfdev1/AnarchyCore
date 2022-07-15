package wolfdev1.com.github.anarchycorev4.listeners

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4

class BlackList(var plugin: AnarchyCoreV4) : Listener {
    @EventHandler
    fun onPreLogin(e: AsyncPlayerPreLoginEvent) {
        val c = plugin.config
        if (c.getBoolean("Players.Blacklist.Enabled")) {
            val bl = c.getString("Players.Blacklist.Players").split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val sb = StringBuilder()
            for (i in 2 until
                    bl.size) {
                sb.append(bl[i])
                        .append(" ")
            }
            if (sb.toString().contains(e.playerProfile.name!!)) {
                e.loginResult = AsyncPlayerPreLoginEvent.Result.KICK_BANNED
                e.kickMessage = ChatColor.translateAlternateColorCodes('&', """
     
     &0[&6Anarchy&r&dCoreV4&0] &4&lError!&r &3Your &4user&r&3 is on the blacklist
     """.trimIndent())
            }
        }
    }

    companion object {
        var blacklist = ArrayList<Player>(100)
    }
}