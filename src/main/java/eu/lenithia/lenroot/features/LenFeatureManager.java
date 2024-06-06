package eu.lenithia.lenroot.features;

import eu.lenithia.lenroot.LenRoot;
import eu.lenithia.lenroot.features.helloworld.HelloWorld;

import java.util.ArrayList;
import java.util.List;

public class LenFeatureManager {

    private final LenRoot instance;

    public static List<LenFeature> features = new ArrayList<>();

    public LenFeatureManager(LenRoot instance) {
        this.instance = instance;
        registerBuiltInFeatures();
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
        instance.getLogger().info("Loading LenFeature: " + feature.getName());
        feature.setEnabled(true, true);
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



    private void registerBuiltInFeatures(){
        instance.getLogger().info("Registering built-in LenFeatures!");
        register(new HelloWorld());
    }

}
