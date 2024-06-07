package eu.lenithia.lenroot.other;

public enum Permissions {

    LEN_HELP("command.help"),
    LEN_GUI("command.gui"),

    LEN_LOAD("command.load"),
    LEN_UNLOAD("command.unload"),

    LEN_REGISTER("command.register"),
    LEN_UNREGISTER("command.unregister"),

    LEN_RELOAD("command.reload"),


    ;

    private final String permission;

    Permissions(String permission){
        this.permission = permission;
    }

    public String get(){
        return "len.root." + permission;
    }
}
