package Spigot;

import Spigot.Commands.PluginCommand;
import Spigot.Listeners.PlayerCount;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class PluginRegistry {


    private static PluginCommand[] commands = {};
    private static Listener[] listeners = {
            new PlayerCount(),
    };

    public static void registerPlugins(JavaPlugin plugin) {
        registerCommands(plugin);
        registerListeners(plugin);
    }

    private static void registerCommands(JavaPlugin plugin) {
        for (PluginCommand cx : commands) {
        }
    }

    private static void registerListeners(JavaPlugin plugin) {
        for(Listener l : listeners) {
            Bukkit.getPluginManager().registerEvents(l, plugin);
        }
    }

}
