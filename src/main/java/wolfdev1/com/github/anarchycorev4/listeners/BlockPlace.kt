package wolfdev1.com.github.anarchycorev4.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

import java.awt.*;
import java.time.Instant;

public class BlockPlace implements Listener {

    AnarchyCoreV4 plugin;

    public BlockPlace(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        FileConfiguration c = plugin.getConfig();
        Block b = e.getBlock();
        if(!e.getPlayer().isOp()) {
            if (!c.getBoolean("Dimensions.End.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Overworld.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Nether.IllegalBlocks.Status")) {
                try {
                    EmbedBuilder eb = new EmbedBuilder()
                            .setAuthor(e.getPlayer().getName(), null, "https://crafatar.com/avatars/" + e.getPlayer().getUniqueId())
                            .setTitle("!Illegal Block Placed!")
                            .setDescription("**World: **" + b.getWorld().getWorldType().getName() + "\n" +
                                    "**Location:** X " + b.getLocation().getBlockX() + " Y" + b.getLocation().getBlockY() + " Z " + b.getLocation().getBlockZ() + "\n" +
                                    "**Block Type: ** " + b.getType().name())
                            .setColor(Color.decode("#130c24"))
                            .setTimestamp(Instant.now())
                            .setFooter("AnarchyCoreV4");

                    AnarchyCoreV4.jda.getTextChannelById(c.getString("Discord.DiscordChannelId")).sendMessageEmbeds(eb.build()).queue();
                } catch (NullPointerException ex) {
                    return;
                }
                e.setCancelled(false);
            } else {
                if (c.getBoolean("IllegalBlocks.Bedrock.Status")) {
                    if (b.getType().equals(Material.BEDROCK)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.Bedrock.Message")));
                    }
                }
                if (c.getBoolean("IllegalBlocks.Barrier.Status")) {
                    if (b.getType().equals(Material.BARRIER)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.Barrier.Message")));
                    }
                }
                if (c.getBoolean("IllegalBlocks.EndPortal.Status")) {
                    if (b.getType().equals(Material.ENDER_PORTAL)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(c.getString(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.EndPortal.Message"))));
                    }
                }
                if (c.getBoolean("IllegalBlocks.MobSpawner.Status")) {
                    if (b.getType().equals(Material.MOB_SPAWNER)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.MobSpawner.Message")));
                    }
                }
            }
        }
    }
}
