package com.example.realTimeTicketingSystem.controller;

import com.example.realTimeTicketingSystem.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @PostMapping("/start")
    public String startSimulation(
            @RequestParam int totalTickets,
            @RequestParam int ticketReleaseRate,
            @RequestParam int customerRetrievalRate,
            @RequestParam int maxTicketCapacity,
            @RequestParam int numVendors,
            @RequestParam int numCustomers) {

        if (totalTickets <= 0 || ticketReleaseRate <= 0 || customerRetrievalRate <= 0 ||
                maxTicketCapacity <= 0 || numVendors <= 0 || numCustomers <= 0) {
            return "All input values must be greater than zero.";
        }

        simulationService.startSimulation(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numVendors, numCustomers);
        return "Simulation started successfully!";
    }

    @PostMapping("/stop")
    public String stopSimulation() {
        simulationService.stopSimulation();
        return "Simulation stopped successfully.";
    }
}
