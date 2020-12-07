package Discord;

import Discord.Commands.ICommand;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;

import java.util.Arrays;


public class DiscordBot {

    private final DiscordClient client;
    private final char commandIdentifier;
    public DiscordBot() {
        this.client = DiscordClient.create(Secret.TOKEN);
        this.commandIdentifier = '!';
        GatewayDiscordClient gateway = this.client.login().block();

        assert gateway != null;
        gateway.on(MessageCreateEvent.class).subscribe(this::messageReceived);
        gateway.onDisconnect().block();


    }


    private void messageReceived(MessageCreateEvent e) {
        if(e.getMessage().getAuthor().isPresent() && !e.getMessage().getAuthor().get().isBot()) {
            if(e.getMessage().getContent().charAt(0) == this.commandIdentifier) {
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

    }

}
