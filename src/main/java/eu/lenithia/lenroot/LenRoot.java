package eu.lenithia.lenroot;


import eu.lenithia.lenroot.api.ConfigAPI;
import eu.lenithia.lenroot.api.DatabaseAPI;
import eu.lenithia.lenroot.api.LenFeatureAPI;
import eu.lenithia.lenroot.commands.Len;
import eu.lenithia.lenroot.database.DatabaseManager;
import eu.lenithia.lenroot.features.LenFeatureManager;
import eu.lenithia.lenroot.other.ConfigManager;
import lombok.Getter;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@Getter
public final class LenRoot extends JavaPlugin {

    /**
     * Use this to interact with LenFeature Modules.
     */
    public LenFeatureAPI lenFeatureAPI;

    /**
     * Use this to interact with MySQL database.
     */
    public DatabaseAPI databaseAPI;

    /**
     * Used for interacting with the config files.
     */
    public ConfigAPI configAPI;

    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Plugin is starting up!");
        getLogger().info("THX for trying out LenRoot :)");
        getLogger().info(" ----------------------------- ");

        // Bstats
        int pluginId = 22166;
        Metrics metrics = new Metrics(this, pluginId);

        // Starting config
        ConfigManager configManager = new ConfigManager(this);
        configAPI = new ConfigAPI(configManager);

        // Starting database
        DatabaseManager databaseManager = new DatabaseManager(this);
        databaseAPI = new DatabaseAPI(databaseManager);
        databaseAPI.startDatabase();

        // Staring LenFeatures
        LenFeatureManager lenFeatureManager = new LenFeatureManager(this);
        lenFeatureAPI = new LenFeatureAPI(lenFeatureManager);
        lenFeatureManager.registerBuiltInFeatures();

        // Registering command
        Objects.requireNonNull(getCommand("len")).setExecutor(new Len(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        if (lenFeatureAPI != null) {
            lenFeatureAPI.unregisterAllFeatures();
        }

        getLogger().info("Plugin died :/");

    }
}
