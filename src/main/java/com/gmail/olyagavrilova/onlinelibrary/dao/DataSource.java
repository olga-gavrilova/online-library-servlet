package com.gmail.olyagavrilova.onlinelibrary.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

class DataSource {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        try {
            config.setJdbcUrl("jdbc:mysql://localhost:3306/servlet_library?useUnicode=true&serverTimezone=UTC");
            config.setUsername("root");
            config.setPassword("1111");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds = new HikariDataSource(config);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private DataSource() {
    }

    static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
