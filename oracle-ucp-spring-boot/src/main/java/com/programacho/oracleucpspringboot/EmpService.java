package com.programacho.oracleucpspringboot;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class EmpService {

    private final JdbcTemplate jdbcTemplate;

    public EmpService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM emp");
    }

    public void save() {
        jdbcTemplate.update("INSERT INTO emp (name) VALUES (?)", UUID.randomUUID().toString());
    }
}
