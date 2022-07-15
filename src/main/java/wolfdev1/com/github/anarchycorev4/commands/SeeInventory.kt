package wolfdev1.com.github.anarchycorev4.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

public class SeeInventory implements CommandExecutor {

    AnarchyCoreV4 plugin;

    public SeeInventory(AnarchyCoreV4 plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String l, String[] args) {

        FileConfiguration c = plugin.getConfig();
        if(s instanceof Player) {

            Player sender = (Player) s;

            if(args.length < 1) {
                s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                        c.getString(
                                "CommandsMessages.General.NoPlayerSelected")));
            }else{
                if(Bukkit.getPlayer
                        (args[0]) == null) {
                    s.sendMessage(ChatColor.translateAlternateColorCodes('&',c.getString(
                            "CommandsMessages.General.PlayerNotFound")));
                }else{

                    if(Bukkit.getPlayer
                            (args[0]).getName() == s.getName()) {
                        s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                c.getString("CommandsMessages.General.SelectedIsSender")));
                    }else{
                                Player selected = Bukkit.getPlayer
                                (args[0]);

                        sender.openInventory(selected.getInventory());
                    }
                }
            }
        }else{
            s.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    c.getString("CommandsMessages.General.OnlyPlayerCanExecute")));
        }
        return false;
    }
}
