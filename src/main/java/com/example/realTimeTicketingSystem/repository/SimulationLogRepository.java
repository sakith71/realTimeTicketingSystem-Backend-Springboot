/**
 * A Spring Data JPA repository for managing simulation logs.
 * Provides basic CRUD operations for SimulationDb entities.
 *
 * @author [Sakith Umagiliya]
 * @version 1.0
 * @since 2024-11-20
 */
package com.example.realTimeTicketingSystem.repository;

import com.example.realTimeTicketingSystem.model.SimulationDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * A repository for simulation logs.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
@Repository
public interface SimulationLogRepository extends JpaRepository<SimulationDb, Long> {
}
