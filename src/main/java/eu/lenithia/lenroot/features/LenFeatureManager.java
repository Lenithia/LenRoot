package eu.lenithia.lenroot.features;

import eu.lenithia.lenroot.LenRoot;
import eu.lenithia.lenroot.features.economy.Economy;
import eu.lenithia.lenroot.features.helloworld.HelloWorld;
import eu.lenithia.lenroot.features.leveling.Leveling;
import eu.lenithia.lenroot.features.stackmanager.StackManager;
import eu.lenithia.lenroot.features.visualcohesion.VisualCohesion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LenFeatureManager {

    private final LenRoot lenRoot;

    public static List<LenFeature> features = new ArrayList<>();

    public LenFeatureManager(LenRoot lenRoot) {
        this.lenRoot = lenRoot;
    }


    public void register(LenFeature... features) {
        Arrays.stream(features).forEach(feature -> {
            lenRoot.getLogger().info("Registering LenFeature: " + feature.getName());
            LenFeatureManager.features.add(feature);
            feature.setLenRoot(lenRoot);
            load(feature);
        });
    }

    public void unregister(LenFeature feature) {
        lenRoot.getLogger().info("Unregistering LenFeature: " + feature.getName());
        unload(feature);
        features.remove(feature);
    }


    public void load(LenFeature feature) {
        try {
            lenRoot.getLogger().info("Loading LenFeature: " + feature.getName());
            feature.setEnabled(true, true);
        } catch (Exception e) {
            feature.setEnabled(false, false);
            lenRoot.getLogger().severe("Failed to load LenFeature: " + feature.getName());
            lenRoot.getLogger().severe(e.getMessage());
        }
    }

    public void unload(LenFeature feature) {
        lenRoot.getLogger().info("Unloading LenFeature: " + feature.getName());
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
        featuresCopy.forEach(this::unregister);
    }


    public void registerBuiltInFeatures(){
        lenRoot.getLogger().info("Registering built-in LenFeatures!");

        register(new StackManager(), new VisualCohesion(), new Leveling(), new Economy(), new HelloWorld());
    }


}
