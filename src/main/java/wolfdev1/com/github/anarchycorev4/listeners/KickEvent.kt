package wolfdev1.com.github.anarchycorev4.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

public class KickEvent implements Listener {

    AnarchyCoreV4 plugin;

    public KickEvent(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onKick(PlayerKickEvent e) {
        FileConfiguration c = plugin.getConfig();
        if(e.getReason().equalsIgnoreCase("Kicked for spamming")) {
            e.setCancelled(true);
        }
    }
}
