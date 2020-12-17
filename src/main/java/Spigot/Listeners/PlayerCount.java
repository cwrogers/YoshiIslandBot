package Spigot.Listeners;

import Discord.DiscordBot;
import Spigot.Spigot;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerCount implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();
        Spigot.statusUpdater.setPlayerCount(playerCount);
    }


    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        int playerCount = Bukkit.getServer().getOnlinePlayers().size() - 1;
        Spigot.statusUpdater.setPlayerCount(playerCount);
    }
}
