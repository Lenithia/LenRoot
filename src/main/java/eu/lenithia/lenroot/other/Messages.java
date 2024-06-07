package eu.lenithia.lenroot.other;

public enum Messages {

    NOPERMISSION(""),


    ;

    private final String message;

    Messages(String permission){
        this.message = permission;
    }

    public String get(){
        return message;
    }
}
