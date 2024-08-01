package eu.lenithia.lenroot.api;

import eu.lenithia.lenroot.commands.CommandManager;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import lombok.Getter;
import org.incendo.cloud.paper.PaperCommandManager;

@Getter
@SuppressWarnings({"UnstableApiUsage", "unused"})
public class CommandAPI {

    CommandManager commandManager;

    public PaperCommandManager<CommandSourceStack> getCommandManager() {
        return commandManager.getCommandManager();
    }

    public CommandAPI(CommandManager commandManager) {
        this.commandManager = commandManager;
    }



}
