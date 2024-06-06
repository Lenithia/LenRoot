package eu.lenithia.lenroot;


import eu.lenithia.lenroot.api.DatabaseAPI;
import eu.lenithia.lenroot.api.LenFeatureAPI;
import eu.lenithia.lenroot.commands.Len;
import eu.lenithia.lenroot.database.DatabaseManager;
import eu.lenithia.lenroot.features.LenFeatureManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

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

    @Override
    public void onEnable() {
        // Plugin startup logic

        getLogger().info("Plugin is starting up!");
        getLogger().info("THX for trying out LenRoot :)");
        getLogger().info(" ----------------------------- ");

        // Starting config

        // Starting database
        DatabaseManager databaseManager = new DatabaseManager();
        databaseAPI = new DatabaseAPI(databaseManager);

        // Staring LenFeatures
        LenFeatureManager lenFeatureManager = new LenFeatureManager(this);
        lenFeatureAPI = new LenFeatureAPI(lenFeatureManager);
        getCommand("len").setExecutor(new Len(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().info("Plugin died :/");

    }
}
