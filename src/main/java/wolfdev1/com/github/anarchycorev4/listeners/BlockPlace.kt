package wolfdev1.com.github.anarchycorev4.listeners

import net.dv8tion.jda.api.EmbedBuilder
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4
import java.awt.Color
import java.time.Instant

class BlockPlace(var plugin: AnarchyCoreV4) : Listener {
    @EventHandler
    fun onBlockPlace(e: BlockPlaceEvent) {
        val c = plugin.config
        val b = e.block
        if (!e.player.isOp) {
            if (!c.getBoolean("Dimensions.End.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Overworld.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Nether.IllegalBlocks.Status")) {
                try {
                    val eb = EmbedBuilder()
                            .setAuthor(e.player.name, null, "https://crafatar.com/avatars/" + e.player.uniqueId)
                            .setTitle("!Illegal Block Placed!")
                            .setDescription("""**World: **${b.world.worldType.getName()}
**Location:** X ${b.location.blockX} Y${b.location.blockY} Z ${b.location.blockZ}
**Block Type: ** ${b.type.name}""")
                            .setColor(Color.decode("#130c24"))
                            .setTimestamp(Instant.now())
                            .setFooter("AnarchyCoreV4")
                    AnarchyCoreV4.Companion.jda!!.getTextChannelById(c.getString("Discord.DiscordChannelId"))!!.sendMessageEmbeds(eb.build()).queue()
                } catch (ex: NullPointerException) {
                    return
                }
                e.isCancelled = false
            } else {
                if (c.getBoolean("IllegalBlocks.Bedrock.Status")) {
                    if (b.type == Material.BEDROCK) {
                        e.isCancelled = true
                        e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.Bedrock.Message")))
                    }
                }
                if (c.getBoolean("IllegalBlocks.Barrier.Status")) {
                    if (b.type == Material.BARRIER) {
                        e.isCancelled = true
                        e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.Barrier.Message")))
                    }
                }
                if (c.getBoolean("IllegalBlocks.EndPortal.Status")) {
                    if (b.type == Material.ENDER_PORTAL) {
                        e.isCancelled = true
                        e.player.sendMessage(c.getString(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.EndPortal.Message"))))
                    }
                }
                if (c.getBoolean("IllegalBlocks.MobSpawner.Status")) {
                    if (b.type == Material.MOB_SPAWNER) {
                        e.isCancelled = true
                        e.player.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.MobSpawner.Message")))
                    }
                }
            }
        }
    }
}