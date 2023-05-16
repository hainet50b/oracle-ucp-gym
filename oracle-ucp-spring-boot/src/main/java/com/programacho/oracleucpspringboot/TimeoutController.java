package com.programacho.oracleucpspringboot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timeout")
public class TimeoutController {

    private final TimeoutService service;

    public TimeoutController(TimeoutService service) {
        this.service = service;
    }

    @PostMapping("/query-timeout")
    public void queryTimeout() {
        service.queryTimeout();
    }

    @PostMapping("/with-transaction")
    public void withTransaction() {
        service.withTransaction();
    }

    @PostMapping("/with-no-transaction")
    public void withNoTransaction() {
        service.withNoTransaction();
    }
}
