package wolfdev1.com.github.anarchycorev4

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import wolfdev1.com.github.anarchycorev4.commands.Help
import wolfdev1.com.github.anarchycorev4.commands.SeeInventory
import wolfdev1.com.github.anarchycorev4.listeners.*
import javax.security.auth.login.LoginException

class AnarchyCoreV4 : JavaPlugin() {
    override fun onEnable() {
        config.options().copyDefaults()
        saveDefaultConfig()
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('8', "&f&m------------------------------------------"))
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('8', "              &c&lAnarchyCoreV4"))
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('8', "               Enabling plugin."))
        Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('8', "&f&m------------------------------------------"))
        if (config.getBoolean("Discord.Enabled")) {
            try {
                jda = JDABuilder.createDefault(config.getString("Discord.DiscordBotToken"))
                        .setActivity(Activity.playing(config.getString("Discord.DiscordBotActivity")))
                        .addEventListeners(DiscordManager(this))
                        .build()
            } catch (e: LoginException) {
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('8',
                        "&l&9[AnarchyCore] &r&7Discord token in config.yml is invalid, set an a valid discord bot token."))
            }
        }
        server.pluginManager.registerEvents(BlockPlace(this), this)
        server.pluginManager.registerEvents(PlayerPickup(this), this)
        server.pluginManager.registerEvents(JoinEvent(this), this)
        server.pluginManager.registerEvents(BlackList(this), this)
        server.pluginManager.registerEvents(KickEvent(this), this)
        server.pluginManager.registerEvents(Chat(this), this)
        server.pluginManager.registerEvents(DeathEvent(this), this)
        server.pluginManager.registerEvents(MoveEvent(this), this)
        server.pluginManager.registerEvents(BlockBreak(this), this)
        getCommand("seeinventory").executor = SeeInventory(this)
        getCommand("help").executor = Help()
    }

    override fun onDisable() {
        Bukkit.getLogger().info("&f&m------------------------------------------")
        Bukkit.getLogger().info("              &c&lAnarchyCoreV4")
        Bukkit.getLogger().info("               Disabling plugin")
        Bukkit.getLogger().info("&f&m------------------------------------------")
    }

    companion object {
        var jda: JDA? = null
    }
}