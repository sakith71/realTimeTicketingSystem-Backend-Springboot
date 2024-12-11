package com.example.realTimeTicketingSystem.service;

import com.example.realTimeTicketingSystem.model.Customer;
import com.example.realTimeTicketingSystem.model.SimulationDb;
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
    private final List<String> logs = new ArrayList<>();
    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();
//    private int ticketCount = 0;
    private boolean isSimulationRunning = false;
//    private int lastRetrievedLogIndex = 0; // Track the last index sent to the frontend


    public void startSimulation(int totalTickets, int ticketReleaseRate, int customerRetrievalRate,
                                int maxTicketCapacity, int numVendors, int numCustomers) {
        if (isSimulationRunning) {
            addLog("Simulation is already running. Stop it before starting again.");
        }

//        logs.clear(); // Reset logs for a new simulation

        // Save simulation details to the database
        SimulationDb simulationLog = new SimulationDb(
                totalTickets, ticketReleaseRate, customerRetrievalRate,
                maxTicketCapacity, numVendors, numCustomers
        );
        simulationLogRepository.save(simulationLog);


//        lastRetrievedLogIndex = 0;
//        ticketCount = totalTickets;

//        addLog("Simulation started with " + totalTickets + " tickets, "
//                + ticketReleaseRate + " release rate, "
//                + customerRetrievalRate + " retrieval rate.");
        // Initialize TicketPool
        logs.clear();
        ticketPool = new TicketPool(maxTicketCapacity);

        // Start Vendor Threads
        for (int i = 1; i <= numVendors; i++) {
            int ticketsPerVendor = totalTickets / numVendors + (i == numVendors ? totalTickets % numVendors : 0);
            Vendor vendor = new Vendor(ticketPool, ticketReleaseRate, ticketsPerVendor, this::addLog);
            Thread vendorThread = new Thread(vendor, "Vendor-" + i);
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }

        // Start Customer Threads
        for (int i = 1; i <= numCustomers; i++) {
            Customer customer = new Customer(ticketPool, customerRetrievalRate, this::addLog);
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
//        addLog("Simulation stopped.");

        // Update simulation log
//        SimulationLog lastLog = simulationLogRepository.findAll().stream()
//                .filter(log -> "STARTED".equals(log.getStatus()))
//                .findFirst()
//                .orElse(null);
//
//        if (lastLog != null) {
//            lastLog.setStatus("STOPPED");
//            simulationLogRepository.save(lastLog);
//        }
        addLog("Simulation stopped.");
        isSimulationRunning = false;
    }
    public int getTicketCount() {
        return ticketPool != null ? ticketPool.getTicketCount() : 0;
    }

    public List<String> getLogs() {
//        List<String> newLogs = logs.subList(lastRetrievedLogIndex, logs.size());
//        lastRetrievedLogIndex = logs.size();
        return new ArrayList<>(logs); // Ensure immutability
    }
//
    private synchronized void addLog(String log) {
        logs.add(log);
        System.out.println(log); // Optional: print logs for debugging
    }
//    private void addLog(String log) {
//        logs.add(log);
//        System.out.println(log);
//    }
//    public void resetLogs() {
//        logs.clear();
//        lastRetrievedLogIndex = 0;
//    }


//    // Simulate ticket release and retrieval (called by a scheduler or manually for demo)
//    public void simulateTicketActivity() {
//        if (!isSimulationRunning) return;
//
//        if (ticketCount > 0) {
//            ticketCount--;
//            addLog("1 ticket retrieved. Remaining: " + ticketCount);
//        } else {
//            addLog("No tickets left to retrieve.");
//        }
//    }
}
