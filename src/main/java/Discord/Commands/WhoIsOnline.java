package Discord.Commands;

import Discord.DiscordBot;
import org.bukkit.entity.Player;
import org.javacord.api.entity.message.Message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class WhoIsOnline extends AbstractCommand {

    public WhoIsOnline() {
        this.identifier = "who";
        this.description = "returns a list of players currently on the server";
        this.help = this.identifier;
    }

    @Override
    public void run(Message s) {
        if(DiscordBot.spigotPlugin.getServer().getOnlinePlayers().size() == 0) {
            s.getChannel().sendMessage("No players currently on the server.");
            return;
        }
         StringBuilder msg = new StringBuilder("The players currently on the server are:```\n");
         for(Player p : DiscordBot.spigotPlugin.getServer().getOnlinePlayers()) {
             msg.append(p.getDisplayName()).append("\n");
         }
         s.getChannel().sendMessage(msg.toString());
    }
}
