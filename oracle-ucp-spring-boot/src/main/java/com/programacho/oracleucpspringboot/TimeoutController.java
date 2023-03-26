package com.programacho.oracleucpspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/validation-delay/{timeout}")
    public void validationDelay(@PathVariable int timeout) {
        service.setValidationDelay(timeout);
    }

    @GetMapping("/query-timeout")
    public void queryTimeout() {
        service.queryTimeout();
    }

    @GetMapping("/with-transaction")
    public void withTransaction() {
        service.withTransaction();
    }

    @GetMapping("/with-no-transaction")
    public void withNoTransaction() {
        service.withNoTransaction();
    }
}
