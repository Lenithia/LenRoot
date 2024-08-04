package eu.lenithia.lenroot.commands;

import eu.lenithia.lenroot.LenRoot;
import eu.lenithia.lenroot.features.LenFeature;
import eu.lenithia.lenroot.other.MessageUtils;
import eu.lenithia.lenroot.other.Permissions;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.configuration.PluginMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.incendo.cloud.Command;
import org.incendo.cloud.paper.PaperCommandManager;

import java.util.List;

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
                .permission(Permissions.LEN_INFO.get())
                .handler(context -> {
                    context.sender().getSender().sendMessage(plInfo());
                });

        Command.Builder<CommandSourceStack> helpCommand = builder.literal("help")
                .permission(Permissions.LEN_HELP.get())
                .handler(context -> {
                    context.sender().getSender().sendMessage(help());
                });

        Command.Builder<CommandSourceStack> activeCommand = builder.literal("active")
                .permission(Permissions.LEN_ACTIVE.get())
                .handler(context -> {
                    context.sender().getSender().sendMessage(active());
                });

        Command.Builder<CommandSourceStack> loadCommand = builder.literal("load")
                .permission(Permissions.LEN_LOAD.get())
                .required("module", ModuleParser.moduleParser())
                .handler(context -> {
                    String module = context.get("module");
                    LenFeature feature = lenRoot.getLenFeatureAPI().getLenFeature(module);
                    lenRoot.getLenFeatureAPI().load(feature);
                    context.sender().getSender().sendMessage(MessageUtils.deserialize("<#ffffff> LenFeature <#ea76f5>" + module + "<#ffffff> was loaded!"));
                });

        Command.Builder<CommandSourceStack> unloadCommand = builder.literal("unload")
                .permission(Permissions.LEN_UNLOAD.get())
                .required("module", ModuleParser.moduleParser())
                .handler(context -> {
                    String module = context.get("module");
                    LenFeature feature = lenRoot.getLenFeatureAPI().getLenFeature(module);
                    lenRoot.getLenFeatureAPI().unload(feature);
                    context.sender().getSender().sendMessage(MessageUtils.deserialize("<#ffffff> LenFeature <#ea76f5>" + module + "<#ffffff> was unloaded!"));
                });

        Command.Builder<CommandSourceStack> reloadCommand = builder.literal("reload")
                .permission(Permissions.LEN_RELOAD.get())
                .required("module", ModuleParser.moduleParser())
                .handler(context -> {
                    String module = context.get("module");
                    LenFeature feature = lenRoot.getLenFeatureAPI().getLenFeature(module);
                    feature.reload();
                    context.sender().getSender().sendMessage(MessageUtils.deserialize("<#ffffff> LenFeature <#ea76f5>" + module + "<#ffffff> was reloaded!"));
                });


        commandManager.command(helpCommand);
        commandManager.command(activeCommand);

        commandManager.command(loadCommand);
        commandManager.command(unloadCommand);
        commandManager.command(reloadCommand);

        lenRoot.getLogger().info("Command built");
        return builder.build();
    }

    private Component plInfo() {

        PluginMeta pluginMeta = lenRoot.getPluginMeta();

        String pluginName = pluginMeta.getName();
        String pluginDescription = pluginMeta.getDescription();
        List<String> pluginAuthors = pluginMeta.getAuthors();
        String pluginVersion = pluginMeta.getVersion();

        TextComponent.Builder component = Component.text()
                .append(MessageUtils.deserialize("<gradient:#ea76f5:#ed9ff5><strikethrough>                                                                                "))
                .appendNewline()
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   " + pluginName ))
                .appendNewline()
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   Description: <#ffffff>" + pluginDescription ))
                .appendNewline()
                .append(MessageUtils.deserialize(String.format("<#ea76f5>   Authors: <#ffffff>%s", String.join("<#9bd7fa>,<#3690fa> ", pluginAuthors))))
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   Version: <#ffffff>" + pluginVersion ))
                .appendNewline()
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   Help command: <#ffffff><hover:show_text:'<#ea76f5>Click to execute!'><click:suggest_command:/len help>/len help</click></hover>" ))
                .appendNewline()
                .append(MessageUtils.deserialize("<gradient:#ea76f5:#ed9ff5><strikethrough>                                                                                "))
                ;
        return component.build();
    }

    private Component help() {
        TextComponent.Builder component = Component.text()
                .append(MessageUtils.deserialize("<gradient:#ea76f5:#ed9ff5><strikethrough>                                                                                "))
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   /len help: <#ffffff> Shows this help message."))
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   /len active: <#ffffff> Shows currently active modules."))
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   /len load <module>: <#ffffff> Loads a module."))
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   /len unload <module>: <#ffffff> Unloads a module."))
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   /len reload <module>: <#ffffff> Reloads a module."))
                .appendNewline()
                .append(MessageUtils.deserialize("<gradient:#ea76f5:#ed9ff5><strikethrough>                                                                                "))
                ;
        return component.build();
    }


    private Component active() {
        TextComponent.Builder component = Component.text()
                .append(MessageUtils.deserialize("<gradient:#ea76f5:#ed9ff5><strikethrough>                                                                                "))
                .appendNewline()
                ;

        for (LenFeature feature : lenRoot.getLenFeatureAPI().getRegisteredLenFeatures()) {
            String status = feature.isEnabled() ? "<green>enabled" : "<red>disabled";
            component.append(MessageUtils.deserialize("<#ea76f5>   " + feature.getName() + "<#ffffff>: " + status));
            component.appendNewline();
        }

        component.append(MessageUtils.deserialize("<gradient:#ea76f5:#ed9ff5><strikethrough>                                                                                "));

        return component.build();
    }





}



// .required("module", UUIDParser.uuidParser())
//.required("player", PlayerParser.playerParser())
//.required("module", StringParser.stringComponent().suggestionProvider(SuggestionProvider.blockingStrings((ctx, input) -> {
//    return lenRoot.getLenFeatureAPI().getRegisteredLenFeatureNames();
//})))
//.optional("module", StringParser.stringComponent().suggestionProvider(moduleSuggestions))