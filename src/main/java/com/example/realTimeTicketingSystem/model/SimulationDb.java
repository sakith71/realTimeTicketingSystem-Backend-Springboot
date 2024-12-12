/**
 * Represents a simulation record in the database.
 * This entity stores the parameters of a simulation, including the total number of tickets,
 * ticket release rate, customer retrieval rate, maximum ticket capacity, number of vendors,
 * and number of customers.
 *
 * @author [Sakith Umagiliya]
 * @version 1.0
 * @since 2024-11-20
 */
package com.example.realTimeTicketingSystem.model;

import jakarta.persistence.*;

@Entity
public class SimulationDb {
    /**
     * The unique identifier of the simulation record.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * The total number of tickets in the simulation.
     * The rate at which vendors release tickets.
     * The rate at which customers attempt to retrieve tickets.
     * The maximum capacity of the ticket pool.
     * The number of vendors in the simulation.
     * The number of customers in the simulation.
     */
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
    private int numVendors;
    private int numCustomers;

    /**
     * Default constructor for JPA entity creation.
     */
    public SimulationDb() {}

    /**
     * Constructs a new SimulationDb instance with specified parameters.
     *
     * @param totalTickets Total number of tickets in the simulation
     * @param ticketReleaseRate Rate at which vendors release tickets
     * @param customerRetrievalRate Rate at which customers attempt to retrieve tickets
     * @param maxTicketCapacity Maximum capacity of the ticket pool
     * @param numVendors Number of vendors in the simulation
     * @param numCustomers Number of customers in the simulation
     */
    public SimulationDb(int totalTickets, int ticketReleaseRate, int customerRetrievalRate,
                        int maxTicketCapacity, int numVendors, int numCustomers) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.numVendors = numVendors;
        this.numCustomers = numCustomers;
    }

    /**
     * Gets the unique identifier of the simulation record.
     *
     * @return The ID of the simulation record
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the simulation record.
     *
     * @param id The ID of the simulation record
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the total number of tickets in the simulation.
     *
     * @return The total number of tickets
     */
    public int getTotalTickets() {
        return totalTickets;
    }

    /**
     * Sets the total number of tickets in the simulation.
     *
     * @param totalTickets The total number of tickets
     */
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    /**
     * Gets the rate at which vendors release tickets.
     *
     * @return The ticket release rate
     */
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    /**
     * Sets the rate at which vendors release tickets.
     *
     * @param ticketReleaseRate The ticket release rate
     */
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    /**
     * Gets the rate at which customers attempt to retrieve tickets.
     *
     * @return The customer retrieval rate
     */
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    /**
     * Sets the rate at which customers attempt to retrieve tickets.
     *
     * @param customerRetrievalRate The customer retrieval rate
     */
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    /**
     * Gets the maximum capacity of the ticket pool.
     *
     * @return The maximum ticket capacity
     */
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    /**
     * Sets the maximum capacity of the ticket pool.
     *
     * @param maxTicketCapacity The maximum ticket capacity
     */
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    /**
     * Gets the number of vendors in the simulation.
     *
     * @return The number of vendors
     */
    public int getNumVendors() {
        return numVendors;
    }

    /**
     * Sets the number of vendors in the simulation.
     *
     * @param numVendors The number of vendors
     */
    public void setNumVendors(int numVendors) {
        this.numVendors = numVendors;
    }

    /**
     * Gets the number of customers in the simulation.
     *
     * @return The number of customers
     */
    public int getNumCustomers() {
        return numCustomers;
    }

    /**
     * Sets the number of customers in the simulation.
     *
     * @param numCustomers The number of customers
     */
    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }
}
