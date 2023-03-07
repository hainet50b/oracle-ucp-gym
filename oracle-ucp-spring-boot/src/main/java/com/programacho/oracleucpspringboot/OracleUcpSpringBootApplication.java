package com.programacho.oracleucpspringboot;

import oracle.ucp.jdbc.PoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class OracleUcpSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(OracleUcpSpringBootApplication.class, args);
    }

    private final Logger log = LoggerFactory.getLogger(OracleUcpSpringBootApplication.class);

    private final JdbcTemplate jdbcTemplate;

    private final PoolDataSource dataSource;

    public OracleUcpSpringBootApplication(
            JdbcTemplate jdbcTemplate,
            PoolDataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.dataSource = dataSource;
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            log.info("{}", jdbcTemplate.queryForObject("SELECT 1 FROM DUAL;", String.class));
            log.info(dataSource.getConnectionPoolName());
        };
    }
}
