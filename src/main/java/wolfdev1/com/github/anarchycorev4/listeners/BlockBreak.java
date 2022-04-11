package wolfdev1.com.github.anarchycorev4.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

public class BlockBreak implements Listener {

    AnarchyCoreV4 plugin;

    public BlockBreak(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        FileConfiguration c = plugin.getConfig();

        Block b = e.getBlock();

        if(!e.getPlayer().isOp()) {
            if (!c.getBoolean("Dimensions.End.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Overworld.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Nether.IllegalBlocks.Status")) {
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
                        e.getPlayer().sendMessage(c.getString(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.MobSpawner.Message"))));
                    }
                }
            }
        }
    }
}
