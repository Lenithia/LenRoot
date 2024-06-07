package eu.lenithia.lenroot.features;

import eu.lenithia.lenroot.LenRoot;
import eu.lenithia.lenroot.features.economy.Economy;
import eu.lenithia.lenroot.features.helloworld.HelloWorld;
import eu.lenithia.lenroot.features.leveling.Leveling;
import eu.lenithia.lenroot.features.stackmanager.StackManager;

import java.util.ArrayList;
import java.util.List;

public class LenFeatureManager {

    private final LenRoot instance;

    public static List<LenFeature> features = new ArrayList<>();

    public LenFeatureManager(LenRoot instance) {
        this.instance = instance;
    }


    public void register(LenFeature feature) {
        instance.getLogger().info("Registering LenFeature: " + feature.getName());
        features.add(feature);
        feature.setLenRoot(instance);
        load(feature);
    }

    public void unregister(LenFeature feature) {
        instance.getLogger().info("Unregistering LenFeature: " + feature.getName());
        unload(feature);
        features.remove(feature);
    }


    public void load(LenFeature feature) {
        try {
            instance.getLogger().info("Loading LenFeature: " + feature.getName());
            feature.setEnabled(true, true);
        } catch (Exception e) {
            feature.setEnabled(false, false);
            throw new RuntimeException(e);
        }
    }

    public void unload(LenFeature feature) {
        instance.getLogger().info("Unloading LenFeature: " + feature.getName());
        feature.setEnabled(false, true);
    }


    public LenFeature getLenFeature(String name) {
        return features.stream()
                .filter(feature -> feature.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<LenFeature> getRegisteredLenFeatures() {
        return features;
    }

    public void unregisterAllFeatures() {
        List<LenFeature> featuresCopy = new ArrayList<>(features);
        for (LenFeature feature : featuresCopy) {
            unregister(feature);
        }
    }


    public void registerBuiltInFeatures(){
        instance.getLogger().info("Registering built-in LenFeatures!");

        register(new StackManager());

        register(new Leveling());

        register(new Economy());

        register(new HelloWorld());
    }


}
