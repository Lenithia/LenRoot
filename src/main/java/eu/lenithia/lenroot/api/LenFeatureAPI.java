package eu.lenithia.lenroot.api;

import eu.lenithia.lenroot.features.LenFeature;
import eu.lenithia.lenroot.features.LenFeatureManager;

import java.io.IOException;
import java.util.List;

/**
 * Use this to interact with LenFeature modules.
 */
@SuppressWarnings({"unused"})
public class LenFeatureAPI {

    private final LenFeatureManager lenFeatureManager;

    public LenFeatureAPI(LenFeatureManager lenFeatureManager) {
        this.lenFeatureManager = lenFeatureManager;
    }

    /** Get LenFeature module based on the name
     *
     * @param name name of the module as String
     * @return LenFeature object
     */
    public LenFeature getLenFeature(String name) { return lenFeatureManager.getLenFeature(name); }

    /** Check if LenFeature module is registered
     *
     * @param name name of the module as String
     * @return true if the module is registered
     */
    public Boolean isLenFeatureRegistered(String name) { return lenFeatureManager.isLenFeatureRegistered(name); }

    /** Get all registered modules
     *
     * @return all registered modules
     */
    public List<LenFeature> getRegisteredLenFeatures() { return  lenFeatureManager.getRegisteredLenFeatures(); }

    /** Register & load LenFeature modules
     *
     * @param feature Class that extends LenFeature
     */
    public void register(LenFeature feature) { lenFeatureManager.register(feature); }

    /** Unregister & unload LenFeature modules
     *
     * @param feature Class that extends LenFeature
     */
    public void unregister(LenFeature feature) { lenFeatureManager.unregister(feature); }

    /** Load LenFeature module (executes enable() function)
     *
     * @param feature Class that extends LenFeature
     */
    public void load(LenFeature feature) { lenFeatureManager.load(feature); }

    /** Unoad LenFeature module (executes disable() function)
     *
     * @param feature Class that extends LenFeature
     */
    public void unload(LenFeature feature) { lenFeatureManager.unload(feature); }

    /** Reload LenFeature module (executes reload() function)
     *
     * @param feature Class that extends LenFeature
     */
    public void reload(LenFeature feature) throws IOException { feature.reload(); }

    /**
     * Scary shit to use. Good thing for LenFeature modules intended to troll server owners.
     */
    public void unregisterAllFeatures() { lenFeatureManager.unregisterAllFeatures(); }


}
