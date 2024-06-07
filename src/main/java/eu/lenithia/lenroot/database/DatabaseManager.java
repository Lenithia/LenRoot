package eu.lenithia.lenroot.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.dejvokep.boostedyaml.YamlDocument;
import eu.lenithia.lenroot.LenRoot;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {

    private final LenRoot lenRoot;

    private final YamlDocument config;

    public DatabaseManager(LenRoot lenRoot) {
        this.lenRoot = lenRoot;
        this.config = lenRoot.getConfigAPI().getConfig();
    }

    private HikariDataSource dataSource;


    private HikariConfig setupDatabase() {

        HikariConfig hikariConfig = new HikariConfig();

        String jdbcUrl = "jdbc:mysql://" + config.getString("database.ip") + ":" + config.getInt("database.port") + "/" + config.getString("database.databaseName");

        hikariConfig.setJdbcUrl(jdbcUrl);

        hikariConfig.setUsername(config.getString("database.username"));
        hikariConfig.setPassword(config.getString("database.password"));
        hikariConfig.setMaximumPoolSize(config.getInt("database.maxPoolSize", 1500));

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        hikariConfig.setConnectionTimeout(30000); // 30 seconds
        hikariConfig.setIdleTimeout(300000); // 5 minutes
        hikariConfig.setMaxLifetime(600000); // 10 minutes
        hikariConfig.setMinimumIdle(20); // 10 idle connections
        hikariConfig.setLeakDetectionThreshold(2000); // 2 seconds

        return hikariConfig;
    }

    public void initializeDatabase() {
         try {
             HikariConfig hikariConfig = setupDatabase();
             dataSource = new HikariDataSource(hikariConfig);
             lenRoot.getLogger().info("Database connection established.");
         } catch (Exception e) {
             lenRoot.getLogger().severe("Failed to initialize database connection. Disabling plugin.");
             lenRoot.getServer().getPluginManager().disablePlugin(lenRoot);
             throw e;
         }
    }

    public void closeDatabase() {
        if (dataSource != null) {
            dataSource.close();
            lenRoot.getLogger().info("Database connection closed.");
        }
    }

    public Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            lenRoot.getLogger().severe("Failed to get connection from the pool.");
            lenRoot.getServer().getPluginManager().disablePlugin(lenRoot);
            throw e;
        }
    }


}
