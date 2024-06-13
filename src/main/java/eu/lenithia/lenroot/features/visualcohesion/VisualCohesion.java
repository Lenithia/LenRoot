package eu.lenithia.lenroot.features.visualcohesion;

import dev.dejvokep.boostedyaml.YamlDocument;
import eu.lenithia.lenroot.features.LenFeature;
import eu.lenithia.lenroot.features.visualcohesion.api.GuiAPI;
import eu.lenithia.lenroot.other.MessageUtils;
import lombok.Getter;

import java.io.File;
import java.io.IOException;


@Getter
public class VisualCohesion extends LenFeature {


    private GuiAPI guiAPI;

    private String color1;

    private String color2;

    private String color3;

    public VisualCohesion() {
        this.name = "VisualCohesion";
        this.version = "1.0.0";
        this.prefix = "[" + getName() + "] ";
        this.description = "Make visual stuff seem cohesive!";

        this.authors.add("Len_137");

    }

    @Override
    public void enable() throws IOException {

        File folder = new File(lenRoot.getDataFolder(), "features/VisualCohesion");
        YamlDocument config = lenRoot.configAPI.initializeConfig(folder, "config", lenRoot.getResource("features/VisualCohesion/config.yml"));
        color1 = config.getString("color-scheme.color1");
        color2 = config.getString("color-scheme.color2");
        color3 = config.getString("color-scheme.color3");

        lenRoot.getComponentLogger().info(MessageUtils.deserialize( prefix + " Your default color scheme: " + color1 + "color1 " + color2 + "color2 " + color3 + "color3 "));

        guiAPI = new GuiAPI();






    }

    @Override
    public void disable() {

    }

    @Override
    public void reload() {

        File folder = new File(lenRoot.getDataFolder(), "features/VisualCohesion");
        try {
            YamlDocument config = lenRoot.configAPI.initializeConfig(folder, "config", lenRoot.getResource("features/VisualCohesion/config.yml"));
            color1 = config.getString("color-scheme.color1");
            color2 = config.getString("color-scheme.color2");
            color3 = config.getString("color-scheme.color3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
