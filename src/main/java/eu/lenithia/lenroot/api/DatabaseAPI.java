package eu.lenithia.lenroot.api;

import eu.lenithia.lenroot.database.DatabaseManager;

public class DatabaseAPI {

    public final DatabaseManager databaseManager;

    public DatabaseAPI(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

}
