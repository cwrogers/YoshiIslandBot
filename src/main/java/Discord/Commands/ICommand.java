package Discord.Commands;

import discord4j.core.object.entity.Message;

public interface ICommand {


    void run(Message s);

    void run(Message s, String[] args);

    String getIdentifier();
    String getDescription();
    String getHelp();
    String[] getParams();


}
