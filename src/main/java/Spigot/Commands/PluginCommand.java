package Spigot.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;

public abstract class PluginCommand implements CommandExecutor {

    private String commandIdentifier;

    public void register() {
        Bukkit.getPluginCommand(this.commandIdentifier).setExecutor(this);
    }

    public String getCommandIdentifier() {
        return commandIdentifier;
    }


}
