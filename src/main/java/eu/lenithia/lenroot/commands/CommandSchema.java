package eu.lenithia.lenroot.commands;

import eu.lenithia.lenroot.LenRoot;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.incendo.cloud.Command;
import org.incendo.cloud.paper.PaperCommandManager;

@SuppressWarnings("UnstableApiUsage")
public abstract class CommandSchema {

    public abstract String getName();

    public LenRoot lenRoot;

    public CommandSchema(LenRoot lenRoot) {
        this.lenRoot = lenRoot;
    }

    public abstract Command<CommandSourceStack> buildCommand(PaperCommandManager<CommandSourceStack> commandManager);

}

