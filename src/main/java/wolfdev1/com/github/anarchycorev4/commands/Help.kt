package wolfdev1.com.github.anarchycorev4.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4;

public class Help implements CommandExecutor {

    AnarchyCoreV4 plugin;


    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        FileConfiguration c = plugin.getConfig();

        if(args.length == 0) {
            s.sendMessage("\n" +
                    "-----------| Help |-----------\n" +
                    "    You really need this? :)" +
                    "\n" +
                    "------------------------------");
        }

        return false;
    }
}
