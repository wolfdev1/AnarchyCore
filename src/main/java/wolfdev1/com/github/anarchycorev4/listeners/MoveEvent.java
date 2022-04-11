package wolfdev1.com.github.anarchycorev4.listeners;

import com.destroystokyo.paper.event.player.PlayerTeleportEndGatewayEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

public class MoveEvent implements Listener {
    AnarchyCoreV4 plugin;

    public MoveEvent(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        int x = e.getPlayer().getLocation().getBlockX();
        int z = e.getPlayer().getLocation().getBlockZ();
        Location l = new Location(e.getPlayer().getWorld(), z, 120, x);
        FileConfiguration c = plugin.getConfig();
        if(!e.getPlayer().isOp()) {
            if(c.getBoolean("Players.DisableElytraOnLowTps.Status")) {
                if(e.getPlayer().isGliding()) {
                    double tps = Bukkit.getServer().getTPS()[0];
                    if(tps < c.getInt("Players.DisableElytraOnLowTps.Tps")) {
                        e.getPlayer().setGliding(false);
                        e.getPlayer().sendMessage
                                (ChatColor.
                                        translateAlternateColorCodes
                                                ('&' ,c.getString(
                                                        "Players.DisableElytraOnLowTps.Message")));
                    }
                }
            }
            if (!c.getBoolean("Dimensions.Nether.NetherTop.Enabled")) {
                if (e.getPlayer().getWorld().getEnvironment().equals(World.Environment.NETHER) &&
                        e.getPlayer().getLocation().equals(l)) {
                    e.getPlayer().teleport(l);
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("Dimensions.Nether.NetherTop.Message")));
                }
            }
        }


    }
    @EventHandler
    public void onEndCityTravel(PlayerTeleportEndGatewayEvent e) {
        FileConfiguration c = plugin.getConfig();
        if (!e.getPlayer().isOp()) {
            if (!c.getBoolean("End.Gateway.Enabled")) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString("End.Gateway.Message")));
            }
        }
    }
}
