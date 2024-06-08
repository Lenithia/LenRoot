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

import java.util.List;

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


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if (args.length == 0) {
            commandSender.sendMessage(plInfo());
            return true;
        }


        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }

    private void load(CommandSender sender ,String name) {
        LenFeature feature = lenRoot.getLenFeatureAPI().getLenFeature(name);
        if (feature != null) {
            lenRoot.getLenFeatureAPI().load(feature);
            sender.sendMessage("LenFeature " + name + " was loaded!");
        }
    }

    private Component plInfo() {

        PluginMeta pluginMeta = lenRoot.getPluginMeta();

        String pluginName = pluginMeta.getName();
        String pluginDescription = pluginMeta.getDescription();
        List<String> pluginAuthors = pluginMeta.getAuthors();
        String pluginVersion = pluginMeta.getVersion();

        TextComponent.Builder component = Component.text()
                .append(MessageUtils.deserialize("<gradient:#ea76f5:#ed9ff5><strikethrough>                                                                                 "))
                .appendNewline()
                .appendNewline()
                .append(MessageUtils.deserialize("   <#ea76f5>" + pluginName ))
                .appendNewline()
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   Description: <#ffffff>" + pluginDescription ))
                .appendNewline()
                .append(MessageUtils.deserialize(String.format("<#ea76f5>   Authors: <#ffffff>%s", String.join("<#9bd7fa>,<#3690fa> ", pluginAuthors))))
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   Version: <#ffffff>" + pluginVersion ))
                .appendNewline()
                .appendNewline()
                .append(MessageUtils.deserialize("<#ea76f5>   Help command: <#ffffff><hover:show_text:'<#ea76f5>click to execute!'><click:run_command:len help>/len help</click></hover>" ))
                .appendNewline()
                .appendNewline()
                .append(MessageUtils.deserialize("<gradient:#ea76f5:#ed9ff5><strikethrough>                                                                                 "))
                ;
        return component.build();
    }


}
