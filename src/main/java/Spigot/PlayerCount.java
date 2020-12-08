package Spigot;

import Discord.DiscordBot;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerCount implements Listener {

    int playerCount = 0;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        updatePlayCount();
    }


    @EventHandler
    public void onDisconnect(PlayerQuitEvent e) {
        updatePlayCount();
    }

    private void updatePlayCount() {
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();
        Spigot.discordBot.setStatus(String.format("%s of %s players online.", playerCount, Bukkit.getServer().getMaxPlayers()));
    }

}
