/**
 * Represents a customer in the real-time ticketing system.
 * Customers continuously attempt to retrieve tickets from the ticket pool at specified intervals.
 *
 * @author [Sakith Umagiliya]
 * @version 1.0
 * @since 2024-11-20
 */
package com.example.realTimeTicketingSystem.model;

import java.util.function.Consumer;

public class Customer implements Runnable {
    /**
     * The shared ticket pool from which customers retrieve tickets.
     * The time interval (in milliseconds) between ticket retrieval attempts.
     * A callback function to log customer activities.
     */
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final Consumer<String> logCallback;

    /**
     * Constructs a new Customer instance.
     *
     * @param ticketPool The shared ticket pool from which customers retrieve tickets
     * @param customerRetrievalRate The time interval (in milliseconds) between ticket retrieval attempts
     * @param logCallback A callback function to log customer activities
     */
    public Customer(TicketPool ticketPool, int customerRetrievalRate,Consumer<String> logCallback) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.logCallback = logCallback;
    }

    /**
     * Continuously attempts to retrieve tickets from the ticket pool at specified intervals.
     * The thread will continue running until interrupted.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Ticket ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    logCallback.accept(Thread.currentThread().getName()+" Ticket retrieved");

                }
                Thread.sleep(customerRetrievalRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
