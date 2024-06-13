package eu.lenithia.lenroot.other;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;
import eu.lenithia.lenroot.LenRoot;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class ConfigManager {

    private final LenRoot lenRoot;

    @Getter
    private YamlDocument config;

    @Getter
    private YamlDocument messages;

    public ConfigManager(LenRoot lenRoot) {
        this.lenRoot = lenRoot;
    }

    public void loadConfig() {
        try {
            config = initializeConfig(lenRoot.getDataFolder(), "config", lenRoot.getResource("config.yml"));
        } catch (IOException e) {
            lenRoot.getLogger().severe("Failed to load configs!!! The plugin will now disable. " + e.getMessage());
            lenRoot.getServer().getPluginManager().disablePlugin(lenRoot);
        }
    }

    public void loadMessages() {
        try {
            messages = initializeConfig(lenRoot.getDataFolder(), "messages", lenRoot.getResource("messages.yml"));
        } catch (IOException e) {
            lenRoot.getLogger().severe("Failed to load messages!!! The plugin will now disable. " + e.getMessage());
            lenRoot.getServer().getPluginManager().disablePlugin(lenRoot);
        }
    }


    public YamlDocument initializeConfig(File folder, String fileName, InputStream resource) throws IOException {

        YamlDocument yamlDocument = YamlDocument.create(new File(folder, fileName + ".yml"),
                Objects.requireNonNull(resource),
                GeneralSettings.DEFAULT,
                LoaderSettings.builder().setAutoUpdate(true).build(),
                DumperSettings.DEFAULT,
                UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version"))
                        .setOptionSorting(UpdaterSettings.OptionSorting.SORT_BY_DEFAULTS).build()
        );
        yamlDocument.update();
        yamlDocument.save();
        return yamlDocument;
    }


}
