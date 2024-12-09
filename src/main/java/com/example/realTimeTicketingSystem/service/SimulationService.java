package com.example.realTimeTicketingSystem.service;

import com.example.realTimeTicketingSystem.model.Customer;
import com.example.realTimeTicketingSystem.model.SimulationLog;
import com.example.realTimeTicketingSystem.model.TicketPool;
import com.example.realTimeTicketingSystem.model.Vendor;
import com.example.realTimeTicketingSystem.repository.SimulationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimulationService {

    @Autowired
    private SimulationLogRepository simulationLogRepository;

    private TicketPool ticketPool;
    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();
    private boolean isSimulationRunning = false;

    public void startSimulation(int totalTickets, int ticketReleaseRate, int customerRetrievalRate,
                                int maxTicketCapacity, int numVendors, int numCustomers) {
        if (isSimulationRunning) {
            throw new IllegalStateException("Simulation is already running. Stop it before starting again.");
        }

        // Save simulation details to the database
        SimulationLog simulationLog = new SimulationLog(
                totalTickets, ticketReleaseRate, customerRetrievalRate,
                maxTicketCapacity, numVendors, numCustomers, "STARTED"
        );
        simulationLogRepository.save(simulationLog);

        // Initialize TicketPool
        ticketPool = new TicketPool(maxTicketCapacity);

        // Start Vendor Threads
        for (int i = 1; i <= numVendors; i++) {
            int ticketsPerVendor = totalTickets / numVendors;
            if (i == numVendors - 1) {
                ticketsPerVendor += totalTickets % numVendors;
            }
            Vendor vendor = new Vendor(ticketPool, ticketReleaseRate, ticketsPerVendor);
            Thread vendorThread = new Thread(vendor, "Vendor-" + i);
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        // Start Customer Threads
        for (int i = 1; i <= numCustomers; i++) {
            Customer customer = new Customer(ticketPool, customerRetrievalRate);
            Thread customerThread = new Thread(customer, "Customer-" + i);
            customerThreads.add(customerThread);
            customerThread.start();
        }

        isSimulationRunning = true;
    }

    public void stopSimulation() {
        if (!isSimulationRunning) {
            System.out.println("System is not running.");
            return;
        }

        // Stop all vendor threads
        for (Thread vendorThread : vendorThreads) {
            if (vendorThread.isAlive()) {
                vendorThread.interrupt();
            }
        }
        vendorThreads.clear();

        // Stop all customer threads
        for (Thread customerThread : customerThreads) {
            if (customerThread.isAlive()) {
                customerThread.interrupt();
            }
        }
        customerThreads.clear();

        // Update simulation log
        SimulationLog lastLog = simulationLogRepository.findAll().stream()
                .filter(log -> "STARTED".equals(log.getStatus()))
                .findFirst()
                .orElse(null);

        if (lastLog != null) {
            lastLog.setStatus("STOPPED");
            simulationLogRepository.save(lastLog);
        }

        isSimulationRunning = false;
    }
}
