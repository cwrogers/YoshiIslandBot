package Discord;

import Discord.Commands.ICommand;

import org.bukkit.plugin.java.JavaPlugin;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.message.Message;


import javax.security.auth.login.LoginException;
import java.util.Arrays;


public class DiscordBot  {

    public static char commandIdentifier;
    public static JavaPlugin spigotPlugin;
    private DiscordApi discord;

    public DiscordBot(JavaPlugin plugin) {
        spigotPlugin = plugin;
        CommandRegistry.initCommands();
        commandIdentifier = '!';

        discord = new DiscordApiBuilder().setToken(Secret.TOKEN).login().join();
        spigotPlugin.getLogger().info("YoshiBot online");

        discord.addMessageCreateListener(e -> {
            if(!e.getMessage().getAuthor().isBotUser()) {
                if(e.getMessage().getContent().charAt(0) == DiscordBot.commandIdentifier) {
                    String[] delimitedMsg = e.getMessage().getContent().split(" ");
                    String command = delimitedMsg[0].substring(1);
                    System.out.println(command);

                    if(delimitedMsg.length > 1) {
                        runCommand(e.getMessage(), command, Arrays.copyOfRange(delimitedMsg, 1, delimitedMsg.length));
                    } else {
                        runCommand(e.getMessage(), command);
                    }

                }
            }
        });

    }

    private void runCommand(Message message, String command) {
        try {
            ICommand com = CommandRegistry.getByIdentifier(command.toLowerCase());
            if (com != null)
                com.run(message);
        } catch (NullPointerException e) {
            System.out.println("no command found");
        }
    }

    private void runCommand(Message message, String command, String[] params) {

    }

    public void setStatus(String str) {
        discord.updateActivity(str);
    }


}
