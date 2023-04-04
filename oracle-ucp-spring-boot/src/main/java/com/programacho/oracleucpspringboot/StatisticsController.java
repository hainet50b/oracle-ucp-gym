package com.programacho.oracleucpspringboot;

import oracle.ucp.jdbc.JDBCConnectionPoolStatistics;
import oracle.ucp.jdbc.PoolDataSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@RestController
public class StatisticsController {

    private final PoolDataSource dataSource;

    public StatisticsController(DataSource dataSource) {
        this.dataSource = (PoolDataSource) dataSource;
    }

    @GetMapping("/statistics")
    public JDBCConnectionPoolStatistics object() {
        return dataSource.getStatistics();
    }
}