package com.example.realTimeTicketingSystem.repository;

import com.example.realTimeTicketingSystem.model.SimulationDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationLogRepository extends JpaRepository<SimulationDb, Long> {
}
