/**
 * REST controller for managing the real-time ticketing system simulation.
 * Provides endpoints for starting and stopping the simulation, as well as retrieving
 * the current ticket count and simulation logs.
 *
 * @author [Sakith Umagiliya]
 * @version 1.0
 * @since 204-11-20
 */
package com.example.realTimeTicketingSystem.controller;

import com.example.realTimeTicketingSystem.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
/**
 * REST controller for managing the real-time ticketing system simulation.
 */
@RestController
@RequestMapping("/api/simulation")
@CrossOrigin
public class SimulationController {
    /**
     * Service class for managing the simulation.
     */
    @Autowired
    private SimulationService simulationService;

    /**
     * Starts a new simulation with parameters provided in the request body.
     *
     * @param simulationData Map containing simulation parameters:
     *                      totalTickets, ticketReleaseRate, customerRetrievalRate,
     *                      maxTicketCapacity, numVendors, numCustomers
     * @return A success message if the simulation starts successfully
     */

    @PostMapping("/start")
    public String startSimulation(@RequestBody Map<String, Object> simulationData) {
        int totalTickets = (int) simulationData.get("totalTickets");
        int ticketReleaseRate = (int) simulationData.get("ticketReleaseRate");
        int customerRetrievalRate = (int) simulationData.get("customerRetrievalRate");
        int maxTicketCapacity = (int) simulationData.get("maxTicketCapacity");
        int numVendors = (int) simulationData.get("numVendors");
        int numCustomers = (int) simulationData.get("numCustomers");

        simulationService.startSimulation(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numVendors, numCustomers);
        return "Simulation started successfully!";
    }

    /**
     * Stops the currently running simulation.
     *
     * @return A success message if the simulation stops successfully
     */
    @PostMapping("/stop")
    public String stopSimulation() {
        simulationService.stopSimulation();
        return "Simulation stopped successfully.";
    }

    /**
     * Gets the current number of tickets in the ticket pool.
     *
     * @return The current number of tickets in the pool
     */
    @GetMapping("/ticketCount")
    public int getTicketCount() {
        return simulationService.getTicketCount();
    }

    /**
     * Gets the list of simulation logs.
     *
     * @return A list of log messages from the simulation
     */
    @GetMapping("/logs")
    public List<String> getLogs() {
        return simulationService.getLogs();
    }

}
