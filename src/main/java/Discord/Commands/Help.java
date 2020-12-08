package Discord.Commands;

import Discord.CommandRegistry;
import org.javacord.api.entity.message.Message;

public class Help extends AbstractCommand{

    public Help() {
        this.identifier = "help";
        this.description = "get help";
        this.help = "help _command_";
        this.params = new String[0];
    }

    public void run(Message s) {
        StringBuilder res = new StringBuilder();
        for(ICommand command : CommandRegistry.commands) {
            res.append(String.format("%s - %s\n", command.getIdentifier(), command.getDescription()));
        }
        s.getChannel().sendMessage(res.toString());
    }

    public void run(Message s, String[] args) {
        ICommand command = CommandRegistry.getByIdentifier(args[0]);
    }

}
