package eu.lenithia.lenroot.features.stackmanager;

import eu.lenithia.lenroot.features.LenFeature;
import eu.lenithia.lenroot.features.stackmanager.api.LenStackAPI;
import eu.lenithia.lenroot.features.stackmanager.api.StashAPI;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackManager extends LenFeature {

    @Getter
    private LenStackAPI lenStackAPI;

    @Getter
    private StashAPI stashAPI;


    public StackManager() {
        this.name = "StackManager";
        this.version = "1.0.0";
        this.prefix = "[" + getName() + "] ";
        this.description = "Built-in LenFeature for managing items and stashes.";

        this.authors.add("Len_137");

    }

    @Override
    public void enable() {

        lenStackAPI = new LenStackAPI();
        stashAPI = new StashAPI();

    }

    @Override
    public void disable() {

    }

    @Override
    public void reload() {

    }

    public void test() {
        System.out.println("StackManager test");
    }

}
