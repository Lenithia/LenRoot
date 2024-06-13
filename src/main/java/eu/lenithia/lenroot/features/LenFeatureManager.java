package eu.lenithia.lenroot.features;

import eu.lenithia.lenroot.LenRoot;
import eu.lenithia.lenroot.features.helloworld.HelloWorld;
import eu.lenithia.lenroot.features.stackmanager.StackManager;
import eu.lenithia.lenroot.features.visualcohesion.VisualCohesion;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LenFeatureManager {

    // TODO: system with events to prevent bugs caused by disabling their dependency
    // TODO: ability to register built-in features after being disabled
    // TODO: uninstalling features ??
    // TODO: built-in features list that will be using for warning about disabling them

    private final LenRoot lenRoot;

    public static List<LenFeature> lenFeaturesList = new ArrayList<>();

    public static List<LenFeature> builtInLenFeatures = new ArrayList<>();

    public LenFeatureManager(LenRoot lenRoot) {
        this.lenRoot = lenRoot;
    }


    public void register(LenFeature... features) {
        Arrays.stream(features).forEach(feature -> {
            lenRoot.getLogger().info("Registering LenFeature: " + feature.getName());
            LenFeatureManager.lenFeaturesList.add(feature);
            feature.setLenRoot(lenRoot);
            load(feature);
        });
    }

    public void unregister(LenFeature feature) {
        lenRoot.getLogger().info("Unregistering LenFeature: " + feature.getName());
        unload(feature);
        lenFeaturesList.remove(feature);
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
        return lenFeaturesList.stream()
                .filter(feature -> feature.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<LenFeature> getRegisteredLenFeatures() {
        return lenFeaturesList;
    }

    public void unregisterAllFeatures() {
        List<LenFeature> featuresCopy = new ArrayList<>(lenFeaturesList);
        featuresCopy.forEach(this::unregister);
    }


    public void registerBuiltInFeatures(){
        lenRoot.getLogger().info("Registering built-in LenFeatures!");

        // new StackManager(), new VisualCohesion(), new Leveling(), new Economy(), new HelloWorld()
        register(new VisualCohesion(),new StackManager(), new HelloWorld());
    }


}
