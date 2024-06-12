package eu.lenithia.lenroot.features.visualcohesion;

import eu.lenithia.lenroot.features.LenFeature;
import eu.lenithia.lenroot.features.visualcohesion.api.VisualCohesionAPI;
import lombok.Getter;

@Getter
public class VisualCohesion extends LenFeature {


    private VisualCohesionAPI visualCohesionAPI;

    private String color1;

    private String color2;

    private String color3;

    public VisualCohesion() {
        this.name = "VisualCohesion";
        this.version = "1.0.0";
        this.prefix = "[" + getName() + "] ";
        this.description = "A visual cohesion system for your server!";

    }

    @Override
    public void enable() {

        visualCohesionAPI = new VisualCohesionAPI();






    }

    @Override
    public void disable() {

    }

    @Override
    public void reload() {

    }
    

}
