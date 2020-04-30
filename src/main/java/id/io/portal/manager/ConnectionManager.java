package id.io.portal.manager;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.jackson2.Jackson2Config;
import org.jdbi.v3.jackson2.Jackson2Plugin;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import id.io.portal.util.json.JsonHelper;
import id.io.portal.util.log.AppLogger;
import id.io.portal.util.property.Property;

public class ConnectionManager {

    private static final AppLogger log = new AppLogger(ConnectionManager.class);

    private static HikariDataSource dataSource;
    private static Jdbi jdbi;

    public static void init() {

        HikariConfig config = initConfig();

        dataSource = new HikariDataSource(config);

        initJdbi(dataSource);

        log.debug("init", "Connected Successfully to DB");
    }

    private static HikariConfig initConfig() {

        HikariConfig config = new HikariConfig();

        config.setDriverClassName(PropertyManager.getInstance().getProperty(Property.JDBC_DRIVER));
        config.setJdbcUrl(PropertyManager.getInstance().getProperty(Property.JDBC_URL));
        config.addDataSourceProperty("user", PropertyManager.getInstance().getProperty(Property.JDBC_USERNAME));
        config.addDataSourceProperty("password",
                EncryptionManager.decrypt(PropertyManager.getInstance().getProperty(Property.JDBC_PASSWORD)));

        log.debug("initConfig", "Connecting to URL : " + config.getJdbcUrl());

        // Optimization
        config.addDataSourceProperty("verifyServerCertificate", false);
        config.addDataSourceProperty("useSSL", false);
        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 250);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        config.addDataSourceProperty("useServerPrepStmts", true);

        // Pool Size
        config.setMaximumPoolSize(10);

        config.setConnectionTestQuery("SELECT 1");

        return config;

    }

    private static void initJdbi(HikariDataSource dataSource) {
        jdbi = Jdbi.create(dataSource);
        jdbi.installPlugin(new Jackson2Plugin());
        jdbi.getConfig(Jackson2Config.class).setMapper(JsonHelper.getMapper());
    }

    public static Handle getHandle() {
        if (dataSource == null) {
            init();
        }
        return jdbi.open();
    }

    public static void shutdown() {
        dataSource.close();
    }
}
