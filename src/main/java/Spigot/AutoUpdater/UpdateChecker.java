package Spigot.AutoUpdater;

import Spigot.Spigot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UpdateChecker extends BukkitRunnable {

    PluginUpdater updater;
    public static Boolean updateDownloaded;


    public UpdateChecker(JavaPlugin p) {
        updater = new PluginUpdater(p);
        updateDownloaded = false;
    }

    @Override
    public void run() {
        if(updater.needsUpdate()) {
            updater.externalUpdate();
        }

    }
}
