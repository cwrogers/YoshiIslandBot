package Discord.Commands;


import org.javacord.api.entity.message.Message;

public class MinecraftIP extends AbstractCommand {

    public MinecraftIP() {
        this.identifier = "mcip";
        this.description = "Gives the minecraft server's IP";
        this.help = "mcip";
    }

    public void run(Message s) {
        s.getChannel().sendMessage("yoshicraft.mcs.cx");
    }

    public void run(Message s, String[] args) {
        this.run(s);
    }

}
