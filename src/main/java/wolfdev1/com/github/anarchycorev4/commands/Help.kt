package wolfdev1.com.github.anarchycorev4.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import wolfdev1.com.github.anarchycorev4.AnarchyCoreV4

class Help : CommandExecutor {
    var plugin: AnarchyCoreV4? = null
    override fun onCommand(s: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        val c = plugin!!.config
        if (args.size == 0) {
            s.sendMessage("""
-----------| Help |-----------
    You really need this? :)
------------------------------""")
        }
        return false
    }
}