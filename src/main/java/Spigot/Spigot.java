package Spigot;

import Discord.DiscordBot;
import Spigot.AutoUpdater.UpdateChecker;
import Spigot.Listeners.PlayerCount;
import Spigot.Runner.StatusUpdater;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Spigot extends JavaPlugin {

    public static DiscordBot discordBot;
    public static String PLUGIN_VERSION = "1.0.0";
    public static StatusUpdater statusUpdater;

    @Override
    public void onEnable() {
        System.out.println("Bot connecting") ;
        discordBot = new DiscordBot(this);
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();
        String s = String.format("%s of %s players online.", playerCount, Bukkit.getServer().getMaxPlayers());
        discordBot.setStatus(s);
        registerEvents();
        new UpdateChecker(this).runTaskTimer(this, 0L, 20 * 20);
        statusUpdater = new StatusUpdater();
        statusUpdater.runTaskTimer(this, 0L, 20 * 5);
    }


    @Override
    public void onDisable() {
        if(discordBot != null)
            discordBot.getBot().disconnect();
    }


    public void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerCount(), this);
    }

}
