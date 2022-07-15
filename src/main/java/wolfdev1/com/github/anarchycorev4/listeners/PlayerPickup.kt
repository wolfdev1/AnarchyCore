package wolfdev1.com.github.anarchycorev4.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

public class PlayerPickup implements Listener {

    AnarchyCoreV4 plugin;

    public PlayerPickup(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {

        FileConfiguration c = plugin.getConfig();
        Material b = e.getItem().getItemStack().getType();

        if(!e.getPlayer().isOp()) {
            if(!c.getBoolean("Dimensions.End.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Overworld.IllegalBlocks.Status") ||
                    !c.getBoolean("Dimensions.Nether.IllegalBlocks.Status")) {
                e.setCancelled(false);
            }else {
                if (c.getBoolean("IllegalBlocks.Bedrock.Status")) {
                    if (b.equals(Material.BEDROCK)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.Bedrock.Message")));
                    }
                }
                if (c.getBoolean("IllegalBlocks.Barrier.Status")) {
                    if (b.equals(Material.BARRIER)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.Barrier.Message")));
                    }
                }
                if (c.getBoolean("IllegalBlocks.EndPortal.Status")) {
                    if (b.equals(Material.ENDER_PORTAL)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.EndPortal.Message")));
                    }
                }
                if (c.getBoolean("IllegalBlocks.MobSpawner.Status")) {
                    if (b.equals(Material.MOB_SPAWNER)) {
                        e.setCancelled(true);
                        e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("IllegalBlocks.MobSpawner.Message")));
                    }
                }
            }
        }

    }
}
