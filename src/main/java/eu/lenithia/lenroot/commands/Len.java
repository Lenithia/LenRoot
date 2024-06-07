package eu.lenithia.lenroot.commands;

import eu.lenithia.lenroot.LenRoot;
import eu.lenithia.lenroot.features.LenFeature;
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
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {


        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }

    public void load(CommandSender sender ,String name) {
        LenFeature feature = lenRoot.getLenFeatureAPI().getLenFeature(name);
        if (feature != null) {
            lenRoot.getLenFeatureAPI().load(feature);
            sender.sendMessage("LenFeature " + name + " was loaded!");
        }
    }




}
