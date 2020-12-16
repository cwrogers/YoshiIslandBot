package Spigot;

import Discord.DiscordBot;
import Spigot.Listeners.PlayerCount;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Spigot extends JavaPlugin {

    public static DiscordBot discordBot;

    public static void main(String[] args) {

    }

    @Override
    public void onEnable() {
        System.out.println("Bot connecting") ;
        discordBot = new DiscordBot(this);
        int playerCount = Bukkit.getServer().getOnlinePlayers().size();
        String s = String.format("%s of %s players online.", playerCount, Bukkit.getServer().getMaxPlayers());
        discordBot.setStatus(s);

        registerEvents();


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
