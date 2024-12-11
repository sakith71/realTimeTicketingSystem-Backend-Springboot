package com.example.realTimeTicketingSystem.controller;

import com.example.realTimeTicketingSystem.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/simulation")
@CrossOrigin
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

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

    @PostMapping("/stop")
    public String stopSimulation() {
        simulationService.stopSimulation();
        return "Simulation stopped successfully.";
    }
    @GetMapping("/ticketCount")
    public int getTicketCount() {
        return simulationService.getTicketCount();
    }

    @GetMapping("/logs")
    public List<String> getLogs() {
        return simulationService.getLogs();
    }

}
