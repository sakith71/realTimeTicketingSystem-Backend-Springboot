package com.example.realTimeTicketingSystem.service;

import com.example.realTimeTicketingSystem.repository.ConfigurationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfigurationService {
    private final ConfigurationRepository repository;

    public ConfigurationService(ConfigurationRepository repository) {
        this.repository = repository;
    }

    public Configuration save(Configuration config) {
        return repository.save(config);
    }

    public Optional<Configuration> getConfiguration() {
        return repository.findAll().stream().findFirst();
    }
}


