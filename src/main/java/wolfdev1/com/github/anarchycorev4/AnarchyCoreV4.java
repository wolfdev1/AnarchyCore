package wolfdev1.com.github.anarchycorev4;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import wolfdev1.com.github.anarchycorev4.commands.Help;
import wolfdev1.com.github.anarchycorev4.commands.SeeInventory;
import wolfdev1.com.github.anarchycorev4.listeners.*;

import javax.security.auth.login.LoginException;

public final class AnarchyCoreV4 extends JavaPlugin {

    public static JDA jda;

    @Override
    public void onEnable() {

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('8',"&f&m------------------------------------------"));
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('8',"              &c&lAnarchyCoreV4"));
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('8',"               Enabling plugin."));
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('8',"&f&m------------------------------------------"));

        if (getConfig().getBoolean("Discord.Enabled")) {
            try {
                jda = JDABuilder.createDefault(getConfig().getString("Discord.DiscordBotToken"))
                        .setActivity(Activity.playing(getConfig().getString("Discord.DiscordBotActivity")))
                        .addEventListeners(new DiscordManager(this))
                        .build();
            } catch (LoginException e) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('8',
                        "&l&9[AnarchyCore] &r&7Discord token in config.yml is invalid, set an a valid discord bot token."));
            }
        }



            getServer().getPluginManager().registerEvents(new BlockPlace(this), this);
            getServer().getPluginManager().registerEvents(new PlayerPickup(this), this);
            getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
            getServer().getPluginManager().registerEvents(new BlackList(this), this);
            getServer().getPluginManager().registerEvents(new KickEvent(this), this);
            getServer().getPluginManager().registerEvents(new Chat(this), this);
            getServer().getPluginManager().registerEvents(new DeathEvent(this), this);
            getServer().getPluginManager().registerEvents(new MoveEvent(this), this);
            getServer().getPluginManager().registerEvents(new BlockBreak(this), this);

            getCommand("seeinventory").setExecutor(new SeeInventory(this));
            getCommand("help").setExecutor(new Help());


    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("&f&m------------------------------------------");
        Bukkit.getLogger().info("              &c&lAnarchyCoreV4");
        Bukkit.getLogger().info("               Disabling plugin");
        Bukkit.getLogger().info("&f&m------------------------------------------");
    }
}

