package Discord.Commands;

import org.javacord.api.entity.message.Message;

public abstract class AbstractCommand implements ICommand {
    String identifier;
    String description;
    String help;
    String[] params;

    public String getIdentifier() {
        return this.identifier;
    }

    public String getDescription() {
        return this.description;
    }

    public String getHelp() {
        return this.help;
    }

    public String[] getParams() {
        return this.params;
    }

    @Override
    public void run(Message s, String[] args) {
        this.run(s);
    }
}
