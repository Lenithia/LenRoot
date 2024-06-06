package eu.lenithia.lenroot.commands;

import eu.lenithia.lenroot.LenRoot;
import eu.lenithia.lenroot.api.LenFeatureAPI;
import eu.lenithia.lenroot.features.LenFeature;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Len implements TabExecutor {

    LenRoot instance;

    public Len(LenRoot instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {


        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }

    public void load(CommandSender sender ,String name) {
        LenFeature feature = instance.getLenFeatureAPI().getLenFeature(name);
        if (feature != null) {
            instance.getLenFeatureAPI().load(feature);
            sender.sendMessage("LenFeature " + name + " was loaded!");
        }
    }




}
