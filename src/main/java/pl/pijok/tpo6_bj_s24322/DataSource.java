package pl.pijok.tpo6_bj_s24322;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config;
    private static HikariDataSource ds;

    public static void initProps() {
        System.out.println("Setting connection props");
        Properties props = new Properties();

        props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        props.setProperty("dataSource.user", "pjatk");
        props.setProperty("dataSource.password", "xyz");
        props.setProperty("dataSource.databaseName", "xyz");
        props.setProperty("dataSource.serverName", "xyz");
        props.put("dataSource.logWriter", new PrintWriter(System.out));

        config = new HikariConfig(props);
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() {
        try {
            Connection connection = ds.getConnection();
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
