package wolfdev1.com.github.anarchycorev4.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

public class DeathEvent implements Listener {


    AnarchyCoreV4 plugin;

    public DeathEvent(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        FileConfiguration c = plugin.getConfig();

        if(c.getBoolean("Players.DeathMessage.Enabled")) {

            String[] args = e.getDeathMessage().split(" ");
            StringBuilder reason = new StringBuilder();
            for(int i = 1; i < args.length; i++)
            {
                reason.append(args[i]).append(" ");
            }

            e.setDeathMessage(ChatColor.translateAlternateColorCodes
                    ('&', c.getString("Players.DeathMessage.Message")
                            .replace("{reason}", reason)
                            .replace("{player}", e.getEntity().getPlayer().getName())));
        }else{
            Bukkit.getWorld("world").setGameRuleValue("showDeathMessages", "false");
            Bukkit.getWorld("world_nether").setGameRuleValue("showDeathMessages", "false");
            Bukkit.getWorld("world_end").setGameRuleValue("showDeathMessages", "false");
        }


    }
}
