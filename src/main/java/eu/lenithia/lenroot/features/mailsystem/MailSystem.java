package eu.lenithia.lenroot.features.mailsystem;

import eu.lenithia.lenroot.features.LenFeature;

public class MailSystem extends LenFeature {

    public MailSystem() {
        this.name = "MailSystem";
        this.version = "1.0.0";
        this.prefix = "[" + getName() + "] ";
        this.description = "A mail system for your server!";

    }

    @Override
    public void enable() {
        lenRoot.getLogger().info("MailSystem has been enabled!");
    }

    @Override
    public void disable() {

    }

    @Override
    public void reload() {

    }
}
