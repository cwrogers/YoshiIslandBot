package Discord.Commands;

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

}
