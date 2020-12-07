package Discord.Commands;

import discord4j.core.object.entity.Message;

public class MinecraftIP extends AbstractCommand {

    public MinecraftIP() {
        System.out.print("Minecraft command made");
        this.identifier = "mcip";
        this.description = "Gives the minecraft server's IP";
        this.help = "mcip";
        this.params = null;
    }

    public void run(Message s) {
        s.getChannel().block().createMessage("yoshicraft.mcs.cx").block();
    }

    public void run(Message s, String[] args) {
        this.run(s);
    }

}
