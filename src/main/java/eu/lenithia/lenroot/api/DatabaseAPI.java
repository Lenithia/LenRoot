package eu.lenithia.lenroot.api;

import eu.lenithia.lenroot.database.DatabaseManager;

import javax.imageio.IIOException;
import java.io.IOException;

public class DatabaseAPI {

    public final DatabaseManager databaseManager;

    public DatabaseAPI(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void startDatabase() {
        databaseManager.initializeDatabase();
    }


}
