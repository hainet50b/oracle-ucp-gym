package com.programacho.oracleucpspringboot;

import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TimeoutService {

    private final JdbcTemplate jdbcTemplate;

    private static final String SLOW_QUERY = "DECLARE " +
            "  sum_val NUMBER := 0; " +
            "BEGIN " +
            "  FOR i IN 1..100000000 LOOP " +
            "    sum_val := sum_val + i; " +
            "  END LOOP; " +
            "END;";

    public TimeoutService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void queryTimeout() {
        try {
            jdbcTemplate.execute(SLOW_QUERY);
        } catch (QueryTimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void withTransaction() {
        try {
            jdbcTemplate.update("INSERT INTO emp (name) VALUES (?)", UUID.randomUUID().toString());
            jdbcTemplate.execute(SLOW_QUERY);
        } catch (QueryTimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void withNoTransaction() {
        try {
            jdbcTemplate.update("INSERT INTO emp (name) VALUES (?)", UUID.randomUUID().toString());
            jdbcTemplate.execute(SLOW_QUERY);
        } catch (QueryTimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
