package com.example.realTimeTicketingSystem.repository;


import com.example.realTimeTicketingSystem.model.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemLogRepository extends JpaRepository<SystemLog, Long> {
}

