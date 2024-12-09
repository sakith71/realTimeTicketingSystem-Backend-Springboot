package com.example.realTimeTicketingSystem.repository;

import com.example.realTimeTicketingSystem.model.SimulationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulationLogRepository extends JpaRepository<SimulationLog, Long> {
}
