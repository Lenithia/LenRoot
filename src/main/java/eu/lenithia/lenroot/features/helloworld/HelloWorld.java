package eu.lenithia.lenroot.features.helloworld;

import eu.lenithia.lenroot.features.LenFeature;
import eu.lenithia.lenroot.features.stackmanager.StackManager;
import eu.lenithia.lenroot.features.visualcohesion.VisualCohesion;
import eu.lenithia.lenroot.other.MessageUtils;

public class HelloWorld extends LenFeature {

    public HelloWorld() {
        this.name = "HelloWorld";
        this.version = "1.0.69";
        this.prefix = "[" + getName() + "] ";
    }

    @Override
    public void enable() {
        lenRoot.getLogger().info(prefix + "Hello World");
        lenRoot.getLogger().info(prefix + getName());

        //lenRoot.getLenFeatureAPI().getLenFeature("kokot");

        LenFeature VisualCohesion = lenRoot.getLenFeatureAPI().getLenFeature("VisualCohesion");
        if (VisualCohesion instanceof VisualCohesion visualCohesion) {
            lenRoot.getComponentLogger().info(MessageUtils.deserialize(prefix + visualCohesion.getColor1() + "Hello World with Visual Cohesion colors!"));
        }

        test();

        LenFeature testFeature = lenRoot.getLenFeatureAPI().getLenFeature("StackManager");
        if (testFeature instanceof StackManager stackManager) {

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
