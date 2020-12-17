package Discord.Commands;

import Discord.DiscordBot;
import org.javacord.api.entity.message.Message;

public class Version extends AbstractCommand {

    public Version() {
        this.identifier = "ver";
        this.description = "Sends the current version of YoshiBot";
        this.help = "ver";
    }


    @Override
    public void run(Message s) {
        s.getChannel().sendMessage(
                String.format("Version: %s", DiscordBot.spigotPlugin.getDescription().getVersion())
        );
    }
}
