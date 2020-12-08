package Spigot;

import Discord.DiscordBot;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Spigot extends JavaPlugin {

    public static DiscordBot discordBot;

    public static void main(String[] args) {
        }

    @Override
    public void onEnable() {
        Runnable run = () -> {
            System.out.println("Bot connecting") ;
            discordBot = new DiscordBot(this);
            int playerCount = Bukkit.getServer().getOnlinePlayers().size();
            String s = String.format("%s of %s players online.", playerCount, Bukkit.getServer().getMaxPlayers());
            System.out.println(s);
            discordBot.setStatus(s);
        };

        Thread thread = new Thread(run);
        thread.start();


    }


    @Override
    public void onDisable() {}


    public void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerCount(), this);
    }

}
