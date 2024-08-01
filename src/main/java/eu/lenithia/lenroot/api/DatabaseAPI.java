package eu.lenithia.lenroot.api;

import eu.lenithia.lenroot.database.DatabaseManager;

import java.sql.Connection;

@SuppressWarnings({"unused"})
public class DatabaseAPI {

    public final DatabaseManager databaseManager;

    public DatabaseAPI(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    /**
     * Starts the database.
     */
    public void startDatabase() { databaseManager.initializeDatabase(); }

    /** Get connection to the database.
     *
     * @return Connection to the database.
     */
    public Connection getConnection() { return databaseManager.getConnection(); }

    /**
     * Closes the connection to the database.
     */
    public void closeConnection() { databaseManager.closeDatabase(); }

}
