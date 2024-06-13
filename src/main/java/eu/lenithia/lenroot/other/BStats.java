package eu.lenithia.lenroot.other;


import eu.lenithia.lenroot.LenRoot;
import org.bstats.bukkit.Metrics;

public class BStats {

    public BStats(LenRoot lenRoot) {
        if (lenRoot.getConfigAPI().getConfig().getBoolean("bstats.enabled")) {
            int pluginId = 22166;
            Metrics metrics = new Metrics(lenRoot, pluginId);
            lenRoot.getLogger().info("bStats metrics enabled.");
        }
    }

}
