package com.programacho.oracleucpspringboot;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeoutController {

    private final JdbcTemplate jdbcTemplate;

    public TimeoutController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/query-timeout")
    public void queryTimeout() {
        jdbcTemplate.queryForObject("SELECT SLEEP(5000);", Long.class);
    }
}
