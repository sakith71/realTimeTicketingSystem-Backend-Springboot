package com.example.realTimeTicketingSystem.controller;

import com.example.realTimeTicketingSystem.service.ConfigurationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {
    private final ConfigurationService service;

    public ConfigurationController(ConfigurationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Configuration> getConfiguration() {
        return service.getConfiguration()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Configuration> saveConfiguration(@RequestBody Configuration config) {
        return ResponseEntity.ok(service.save(config));
    }
}


