package eu.lenithia.lenroot.api;

import dev.dejvokep.boostedyaml.YamlDocument;
import eu.lenithia.lenroot.other.ConfigManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ConfigAPI {

    ConfigManager configManager;

    public ConfigAPI(ConfigManager configManager) {
        this.configManager = configManager;
    }

    /** Getter for the main config file.
     *
     * @return config.yml of LenRoot
     */
    public YamlDocument getConfig() { return configManager.getConfig(); }

    /** Initializes a config file of your choice.
     *
     * @param folder where should the config be located in server files
     * @param fileName name of the file
     * @param resource where should the config be loaded from (probably instance.getResource("config.yml") - where instance is your external LenFeature module)
     * @return YamlDocument of the config file that can be used to get values from
     * @throws IOException if the file cannot be loaded
     */
    public YamlDocument initializeConfig(File folder, String fileName, InputStream resource) throws IOException { return configManager.initializeConfig(folder, fileName, resource); }

}
