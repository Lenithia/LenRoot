package eu.lenithia.lenroot.features.helloworld;

import eu.lenithia.lenroot.features.LenFeature;
import eu.lenithia.lenroot.features.stackmanager.StackManager;

public class HelloWorld extends LenFeature {

    public HelloWorld() {
        this.name = "HelloWorld";
        this.version = "1.0.69";
        this.prefix = "[" + getName() + "] ";
    }

    @Override
    public void enable() {
        lenRoot.getLogger().info(getPrefix() + "Hello World");
        lenRoot.getLogger().info(getPrefix() + getName());

        //lenRoot.getLenFeatureAPI().getLenFeature("kokot");

        test();

        LenFeature testFeature = lenRoot.getLenFeatureAPI().getLenFeature("StackManager");
        if (testFeature instanceof StackManager) {

            StackManager stackManager = (StackManager) testFeature;
            stackManager.test();
        }

    }

    @Override
    public void disable() {
        lenRoot.getLogger().info("Goodbye World");
    }

    @Override
    public void reload() {
        lenRoot.getLogger().info("Reloading Hello World");
    }

    public void test() {
        /*LenFeatures lenFeatures = new LenFeatureManager(instance);
        LenFeature feature = lenFeatures.getLenFeature("HelloWorld");
        instance.getLogger().info(feature.getName());
        instance.getLogger().info(feature.getVersion());
         */
    }

}
