package Discord;

import Discord.Commands.Help;
import Discord.Commands.ICommand;
import Discord.Commands.MinecraftIP;

import java.util.ArrayList;

public class CommandRegistry {

    public static ArrayList<ICommand> commands = new ArrayList<ICommand>();


    public static ICommand getByIdentifier(String id) {
        int index = -1;
        boolean found = false;
        for(int i = 0; i < commands.size(); i++) {
            System.out.println("Checking command: ");
            System.out.println(commands.get(i));
            if(commands.get(i).getIdentifier().equals(id)) {
                found = true;
                index = i;
                break;
            }
        }
        return found ? commands.get(index) : null;
    }


    private static void initCommands() {
        // Add commands here.
        // commands.add(new Command());
        commands.add(new MinecraftIP());
        commands.add(new Help());
    }

}
