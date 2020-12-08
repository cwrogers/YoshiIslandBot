package Discord.Commands;

import Discord.CommandRegistry;
import Discord.DiscordBot;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class Help extends AbstractCommand {

    public Help() {
        this.identifier = "help";
        this.description = "get help";
        this.help = "help _command_";
    }

    public void run(Message s) {
        StringBuilder res = new StringBuilder();
        for (ICommand command : CommandRegistry.commands) {
            res.append(String.format("%c%s - %s\n", DiscordBot.commandIdentifier, command.getIdentifier(), command.getDescription()));
        }
        s.getChannel().sendMessage(res.toString());
    }

    public void run(Message s, String[] args) {
        ICommand command = CommandRegistry.getByIdentifier(args[0]);
        if (command != null) {
            String msg = "**Description: ** " + command.getDescription() + "\n";
            msg += "**Usage:** " + command.getHelp() + "\n";
            EmbedBuilder eb = new EmbedBuilder()
                    .setAuthor("Help")
                    .setTitle(command.getIdentifier())
                    .setDescription(msg);
            s.getChannel().sendMessage(eb);
        }
    }

}
