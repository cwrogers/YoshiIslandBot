package Spigot.Runner;

import Spigot.Spigot;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class StatusUpdater extends BukkitRunnable {

    private boolean updateAvailable;
    private boolean showingUpdate;
    private int playerCount;

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
        if(!(updateAvailable && showingUpdate))
            this.showPlayerCount();
    }

    public void setUpdateAvailable(boolean updateAvailable) {
        this.updateAvailable = updateAvailable;
    }

    @Override
    public void run() {
        if(updateAvailable) {
            if(showingUpdate)
                this.showPlayerCount();
             else
                Spigot.discordBot.setStatus(String.format("Update ready. Restart to load.", playerCount, Bukkit.getServer().getMaxPlayers()));

            showingUpdate = !showingUpdate;
        }
    }

    public void showPlayerCount() {
        Spigot.discordBot.setStatus(String.format("%s of %s players online.", playerCount, Bukkit.getServer().getMaxPlayers()));
    }

}
