package eu.lenithia.lenroot.features;

import eu.lenithia.lenroot.LenRoot;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class LenFeature {

    /**
     * name of the LenFeature module, IT MUST BE UNIQUE!!!
     */
    protected String name;

    /**
     * prefix for console, so you know which module is printing what.
     */
    protected String prefix;

    /**
     * LenFeature module version, intended for checking compatibility with other LenFeature modules.
     */
    protected String version;

    /**
     * Description of the LenFeature module, intended for showing in help command.
     */
    protected String description;

    /**
     * List of authors of the LenFeature module, intended for showing in help command.
     */
    protected List<String> authors = new ArrayList<>();

    /**
     * State of LenFeature module, intended for checking if the LenFeature module can be used by other LenFeature modules.
     */
    protected boolean enabled = false;

    /**
     * Instance of LenRoot should be here, used easy access.
     */
    @Setter
    protected LenRoot lenRoot;


    /** This is used change state of the LenFeature module.
     *
     * @param enabled changes the state value (the state is used to determine if the module is active and can be used by other LenFeature modules)
     * @param load if true, it will also call enable() or disable() functions of the LenFeature module based on the enabled value
     */
    public void setEnabled(boolean enabled, boolean load) {
        this.enabled = enabled;
        if (load) {
            if (enabled) {
                enable();
            } else {
                disable();
            }
        }
    }


    /**
     * LenFeature module enable logic. Modify this function to run code when the LenFeature module becomes active, works similarly to onEnable() function from Bukkit.
     */
    public abstract void enable();

    /**
     * LenFeature module shutdown logic. Make sure it really disables everything that your LenFeature module is doing.
     */
    public abstract void disable();

    /**
     * LenFeature module reload logic. Easiest implementation could be: disable(); enable(); but you can use it for reloading configs or whatever.
     */
    public abstract void reload();

}
