package eu.lenithia.lenroot.other;

public enum Permissions {

    // Stash
    STASH_USE("stash.use"),
    STASH_ADMIN("stash.admin"),

    // Economy


    ;

    private final String permission;

    Permissions(String permission){
        this.permission = permission;
    }

    public String get(){
        return "len.core." + permission;
    }
}
