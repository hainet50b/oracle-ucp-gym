package com.programacho.oracleucpspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TimeoutService {

    private final Logger log = LoggerFactory.getLogger(TimeoutService.class);

    private final JdbcTemplate jdbcTemplate;

    public TimeoutService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void queryTimeout() {
        try {
            jdbcTemplate.queryForObject("SELECT SLEEP(5000);", Long.class);
        } catch (QueryTimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void withTransaction() {
        jdbcTemplate.update("INSERT INTO emp (name) VALUES (?);", UUID.randomUUID().toString());
        jdbcTemplate.queryForObject("SELECT SLEEP(5000);", Long.class);
    }

    public void withNoTransaction() {
        jdbcTemplate.update("INSERT INTO emp (name) VALUES (?);", UUID.randomUUID().toString());
        jdbcTemplate.queryForObject("SELECT SLEEP(5000);", Long.class);
    }
}
