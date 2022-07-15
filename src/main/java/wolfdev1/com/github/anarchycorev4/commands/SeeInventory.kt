package wolfdev1.com.github.anarchycorev4.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4

class SeeInventory(var plugin: AnarchyCoreV4) : CommandExecutor {
    override fun onCommand(s: CommandSender, cmd: Command, l: String, args: Array<String>): Boolean {
        val c = plugin.config
        if (s is Player) {
            if (args.size < 1) {
                s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        c.getString(
                                "CommandsMessages.General.NoPlayerSelected")))
            } else {
                if (Bukkit.getPlayer(args[0]) == null) {
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&', c.getString(
                            "CommandsMessages.General.PlayerNotFound")))
                } else {
                    if (Bukkit.getPlayer(args[0]).name === s.getName()) {
                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                c.getString("CommandsMessages.General.SelectedIsSender")))
                    } else {
                        val selected = Bukkit.getPlayer(args[0])
                        s.openInventory(selected.inventory)
                    }
                }
            }
        } else {
            s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    c.getString("CommandsMessages.General.OnlyPlayerCanExecute")))
        }
        return false
    }
}