package com.programacho.oracleucpspringboot;

import oracle.ucp.jdbc.PoolDataSource;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.UUID;

@Service
public class TimeoutService {

    private final JdbcTemplate jdbcTemplate;

    private final PoolDataSource dataSource;

    public TimeoutService(
            JdbcTemplate jdbcTemplate,
            DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = (PoolDataSource) dataSource;
    }

    public void setValidationDelay(int timeout) {
        try {
            dataSource.setSQLForValidateConnection(String.format("SELECT SLEEP(%d);", timeout));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void queryTimeout() {
        try {
            jdbcTemplate.queryForObject("SELECT SLEEP(5);", Long.class);
        } catch (QueryTimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void withTransaction() {
        jdbcTemplate.update("INSERT INTO emp (name) VALUES (?);", UUID.randomUUID().toString());
        jdbcTemplate.queryForObject("SELECT SLEEP(5);", Long.class);
    }

    public void withNoTransaction() {
        jdbcTemplate.update("INSERT INTO emp (name) VALUES (?);", UUID.randomUUID().toString());
        jdbcTemplate.queryForObject("SELECT SLEEP(5);", Long.class);
    }
}
