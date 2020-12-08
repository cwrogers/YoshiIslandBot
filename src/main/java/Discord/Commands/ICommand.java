package Discord.Commands;


import org.javacord.api.entity.message.Message;

public interface ICommand {


    void run(Message s);

    void run(Message s, String[] args);

    String getIdentifier();
    String getDescription();
    String getHelp();
    String[] getParams();


}
