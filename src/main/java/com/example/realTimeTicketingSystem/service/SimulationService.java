/**
 * Service class for managing the real-time ticketing system simulation.
 * Provides methods for starting and stopping the simulation, as well as retrieving
 * the current ticket count and simulation logs.
 *
 * @author [Sakith Umagiliya]
 * @version 1.0
 * @since 2024-11-20
 */
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
/**
 * Service class for managing the real-time ticketing system simulation.
 */
@Service
public class SimulationService {
    /**
     * Repository for storing simulation logs in the database.
     */
    @Autowired
    private SimulationLogRepository simulationLogRepository;
    /**
     * The ticket pool used for the simulation.
     * List of log messages from the simulation.
     * List of vendor threads used for the simulation.
     * List of customer threads used for the simulation.
     * lag indicating whether the simulation is currently running.
     */
    private TicketPool ticketPool;
    private final List<String> logs = new ArrayList<>();
    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();
    private boolean isSimulationRunning = false;

    /**
     * Starts a new ticket distribution simulation with specified parameters.
     * Creates vendor and customer threads and initializes the ticket pool.
     *
     * @param totalTickets Total number of tickets to be distributed
     * @param ticketReleaseRate Time interval between ticket releases (in milliseconds)
     * @param customerRetrievalRate Time interval between customer retrieval attempts (in milliseconds)
     * @param maxTicketCapacity Maximum capacity of the ticket pool
     * @param numVendors Number of vendor threads to create
     * @param numCustomers Number of customer threads to create
     */
    public void startSimulation(int totalTickets, int ticketReleaseRate, int customerRetrievalRate,
                                int maxTicketCapacity, int numVendors, int numCustomers) {
        if (isSimulationRunning) {
            addLog("Simulation is already running. Stop it before starting again.");
        }

        SimulationDb simulationLog = new SimulationDb(
                totalTickets, ticketReleaseRate, customerRetrievalRate,
                maxTicketCapacity, numVendors, numCustomers
        );
        simulationLogRepository.save(simulationLog);

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

    /**
     * Stops the current simulation by interrupting all vendor and customer threads.
     * Cleans up resources and updates simulation status.
     */
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
        addLog("Simulation stopped.");
        isSimulationRunning = false;
    }
    /**
     * Gets the current number of tickets in the ticket pool.
     *
     * @return The current number of tickets in the pool, or 0 if no simulation is running
     */
    public int getTicketCount() {
        return ticketPool != null ? ticketPool.getTicketCount() : 0;
    }
    /**
     * Gets a copy of the current simulation logs.
     *
     * @return An immutable list of log messages from the simulation
     */
    public List<String> getLogs() {
        return new ArrayList<>(logs); // Ensure immutability
    }

    /**
     * Adds a log message to the simulation logs.
     * Thread-safe method for recording simulation events.
     *
     * @param log The log message to add
     */
    private synchronized void addLog(String log) {
        logs.add(log);
        System.out.println(log); // Optional: print logs for debugging
    }
}
