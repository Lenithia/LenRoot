package eu.lenithia.lenroot.commands;

import eu.lenithia.lenroot.LenRoot;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import lombok.Getter;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.Command;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.PaperCommandManager;

import java.util.HashMap;
import java.util.Map;

@Getter
@SuppressWarnings("UnstableApiUsage")
public class CommandManager {

    private final PaperCommandManager<CommandSourceStack> commandManager;
    private final Map<String, Command<CommandSourceStack>> registeredCommands = new HashMap<>();

    public CommandManager(LenRoot lenRoot) {
        this.commandManager = PaperCommandManager.builder()
                .executionCoordinator(ExecutionCoordinator.simpleCoordinator())
                .buildOnEnable(lenRoot);

    }

    public void registerCommand(CommandSchema command) {
        Command<CommandSourceStack> Command = command.buildCommand(this.commandManager);
        this.commandManager.command(Command);
        this.registeredCommands.put(command.getName(), Command);
    }

    public void unregisterCommand(String commandName) {
        Command<CommandSourceStack> command = this.registeredCommands.remove(commandName);
        if (command != null) {
            this.commandManager.deleteRootCommand(command.rootComponent().name());
        }
    }

}
