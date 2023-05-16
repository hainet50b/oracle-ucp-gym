package com.programacho.oracleucpspringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmpController {

    private final EmpService service;

    public EmpController(EmpService service) {
        this.service = service;
    }

    @GetMapping
    public List<Map<String, Object>> findAll() {
        return service.findAll();
    }

    @PostMapping
    public void save() {
        service.save();
    }
}
