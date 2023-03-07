package com.programacho;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class OracleUcpApiApplication {

    public static void main(String[] args) {
        try {
            PoolDataSource ds = PoolDataSourceFactory.getPoolDataSource();

            ds.setConnectionFactoryClassName("org.h2.Driver");
            ds.setURL("jdbc:h2:mem:oracle-ucp-api");
            ds.setUser("sa");
            ds.setPassword("");

            ds.setConnectionPoolName("programacho-pool");
            ds.setMaxPoolSize(30);
            ds.setMinPoolSize(30);

            ds.setValidateConnectionOnBorrow(true);
            ds.setSQLForValidateConnection("SELECT 1 FROM DUAL");

            Connection conn = ds.getConnection();

            try (Statement createTable = conn.createStatement()) {
                createTable.execute("CREATE TABLE emp (id VARCHAR);");
            }

            try (PreparedStatement insert = conn.prepareStatement("INSERT INTO emp VALUES (?);")) {
                for (int i = 0; i < 10; i++) {
                    insert.setString(1, UUID.randomUUID().toString());
                    insert.execute();
                }
            }

            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM emp;");
                while (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
