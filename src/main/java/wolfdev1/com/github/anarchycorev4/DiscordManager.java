package wolfdev1.com.github.anarchycorev4;

import net.dv8tion.jda.api.entities.Channel;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class DiscordManager extends ListenerAdapter {



    AnarchyCoreV4 plugin;

    public DiscordManager(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {

        FileConfiguration config = plugin.getConfig();

                if(event.getJDA().getTextChannelById(config.getString("Discord.DiscordChannelId")) == null) {
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('8',
                            "&l&9[AnarchyCore] &r&7Discord text channel id set in config.yml is invalid, set an a valid text channel id."));
                }else{
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('8',
                            "&l&9[AnarchyCore] &r&7Discord integration successfully loaded"));
        }
    }
}
