package eu.lenithia.lenroot.other;

public enum Messages {

    // Stash
    STASH_USE("stash.use"),
    STASH_ADMIN("stash.admin"),

    // Economy


    ;

    private final String message;

    Messages(String permission){
        this.message = permission;
    }

    public String get(){
        return message;
    }
}
