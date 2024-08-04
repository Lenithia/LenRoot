package eu.lenithia.lenroot.other;

public enum Permissions {

    LEN_INFO("command.information.info"),
    LEN_HELP("command.information.help"),
    LEN_ACTIVE("command.information.active"),

    LEN_LOAD("command.loading.load"),
    LEN_UNLOAD("command.loading.unload"),
    LEN_RELOAD("command.loading.reload"),

    LEN_GUI("command.gui"),

    LEN_REGISTER("command.register"),
    LEN_UNREGISTER("command.unregister"),



    ;

    private final String permission;

    Permissions(String permission){
        this.permission = permission;
    }

    public String get(){
        return "len.root." + permission;
    }
}
