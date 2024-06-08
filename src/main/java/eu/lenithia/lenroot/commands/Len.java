package eu.lenithia.lenroot.commands;

import eu.lenithia.lenroot.LenRoot;
import eu.lenithia.lenroot.features.LenFeature;
import eu.lenithia.lenroot.other.MessageUtils;
import io.papermc.paper.plugin.configuration.PluginMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Len implements TabExecutor {

    LenRoot lenRoot;

    public Len(LenRoot instance) {
        this.lenRoot = instance;
    }

    /*

    len

    len help

    len gui ??

    len load <module>
    len unload <module>
    len register <module>
    len unregister <module>
    len reload <module>


     */

    private static HashMap<UUID, Time> confirm = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (args.length == 0) {
            commandSender.sendMessage(plInfo());
            return true;
        } else {
            switch (args[0]) {
                case "help":
                    commandSender.sendMessage(help());
                    break;
                case "load":
                    if (args.length == 2) {
                        load(commandSender, args[1]);
                    } else {
                        commandSender.sendMessage("<red> Invalid usage. Use /len load <module>");
                    }
                    break;
                case "unload":
                    if (args.length == 2) {
                        unload(commandSender, args[1]);
                    } else {
                        commandSender.sendMessage("<red> Invalid usage. Use /len unload <module>");
                    }
                    break;
                case "reload":
                    if (args.length == 2) {
                        reload(commandSender, args[1]);
                    } else {
                        commandSender.sendMessage("<red> Invalid usage. Use /len reload <module>");
                    }
                    break;

                // TODO: Implement these commands (register & unregister)
                case "register":

                case "unregister":

                default:
                    commandSender.sendMessage("<red> Unknown command. Use /len help for help.");
                    break;
            }
        }


        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
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
                .append(MessageUtils.deserialize("<#ea76f5>   Help command: <#ffffff><hover:show_text:'<#ea76f5>Click to execute!'><click:suggest_command:len help>/len help</click></hover>" ))
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


    // TODO: Implement confirmation system for loading/unloading/reloading features
    private boolean conformation(CommandSender sender) {
        return true;
    }




    private void load(CommandSender sender ,String name) {
        LenFeature feature = lenRoot.getLenFeatureAPI().getLenFeature(name);
        if (feature != null) {
            lenRoot.getLenFeatureAPI().load(feature);
            sender.sendMessage(MessageUtils.deserialize("<#ffffff> LenFeature <#ea76f5>" + name + "<#ffffff> was loaded!"));
        }
    }

    private void unload(CommandSender sender, String name) {
        LenFeature feature = lenRoot.getLenFeatureAPI().getLenFeature(name);
        if (feature != null) {
            lenRoot.getLenFeatureAPI().unload(feature);
            sender.sendMessage(MessageUtils.deserialize("<#ffffff> LenFeature <#ea76f5>" + name + "<#ffffff> was unloaded!"));
        }
    }

    private void reload(CommandSender sender, String name) {
        LenFeature feature = lenRoot.getLenFeatureAPI().getLenFeature(name);
        if (feature != null) {
            feature.reload();
            sender.sendMessage(MessageUtils.deserialize("<#ffffff> LenFeature <#ea76f5>" + name + "<#ffffff> was reloaded!"));
        }
    }

}
