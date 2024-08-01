package eu.lenithia.lenroot.commands;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.context.SuggestionContext;
import com.sun.jdi.connect.Connector;
import eu.lenithia.lenroot.LenRoot;
import eu.lenithia.lenroot.features.LenFeature;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.command.CommandSender;
import org.codehaus.plexus.util.cli.Commandline;
import org.incendo.cloud.Command;
import org.incendo.cloud.brigadier.CloudBrigadierManager;
import org.incendo.cloud.component.CommandComponent;
import org.incendo.cloud.paper.PaperCommandManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("UnstableApiUsage")
public class LenCommand extends CommandSchema {


    public LenCommand(LenRoot lenRoot) {
        super(lenRoot);
    }

    @Override
    public String getName() {
        return "lenroot";
    }

    @Override
    public Command<CommandSourceStack> buildCommand(PaperCommandManager<CommandSourceStack> commandManager) {
        Command.Builder<CommandSourceStack> builder = commandManager.commandBuilder("lenroot", "lr", "len")
                .handler(context -> {
                    context.sender().getSender().sendMessage("LenRoot command");
                });

        Command.Builder<CommandSourceStack> helpCommand = builder.literal("help")
                .handler(context -> {
                    context.sender().getSender().sendMessage("Help message here");
                });

        List<String> featureNames = lenRoot.getLenFeatureAPI().getRegisteredLenFeatures().stream()
                .map(LenFeature::getName)
                .toList();


        builder.argument(commandManager.brigadierManager().)
                .suggests((context, builder1) -> {
                    String input = context.getInput();
                    return featureNames.stream()
                            .filter(name -> name.startsWith(input))
                            .collect(Collectors.toList());
                });

        Command.Builder<CommandSourceStack> loadCommand = builder.literal("load")
                .argument()
                .handler(context -> {
                    context.command().commandDescription().description().toString();
                    context.sender().getSender().sendMessage("Load message with feature: " );
                });



        commandManager.command(helpCommand);
        //commandManager.command(loadCommand);

        lenRoot.getLogger().info("Command built");
        return builder.build();
    }
}
