package eu.lenithia.lenroot.features.stackmanager;

import eu.lenithia.lenroot.features.LenFeature;

import java.io.IOException;

public class StackManager extends LenFeature {

    public StackManager() {
        this.name = "StackManager";
        this.version = "1.0.0";
        this.prefix = "[" + getName() + "] ";
    }

    @Override
    public void enable() {

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
