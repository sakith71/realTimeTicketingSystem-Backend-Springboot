/**
 * Represents a vendor that releases tickets into a shared ticket pool.
 * Vendors release tickets at specified intervals until their ticket quota is reached.
 *
 * @author [Sakith Umagiliya]
 * @version 1.0
 * @since 2024-11-20
 */
package com.example.realTimeTicketingSystem.model;

import java.util.function.Consumer;

public class Vendor implements Runnable {
    /**
     * The shared ticket pool to which vendors add tickets.
     * The time interval (in milliseconds) between ticket releases.
     * The total number of tickets this vendor should release.
     * A callback function to log vendor activities.
     */
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int ticketsPerVendor;
    private final Consumer<String> logCallback;

    /**
     * Constructs a new Vendor instance.
     *
     * @param ticketPool The shared ticket pool to which vendors add tickets
     * @param ticketReleaseRate The time interval (in milliseconds) between ticket releases
     * @param ticketsPerVendor The total number of tickets this vendor should release
     * @param logCallback A callback function to log vendor activities
     */
    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int ticketsPerVendor,Consumer<String> logCallback) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketsPerVendor = ticketsPerVendor;
        this.logCallback = logCallback;
    }

    /**
     * Releases tickets into the ticket pool at specified intervals until the
     * vendor's ticket quota is reached.
     *
     * @throws InterruptedException if the thread is interrupted while sleeping
     */
    @Override
    public void run() {
        try {
            for (int i = 1; i <= ticketsPerVendor; i++) {
                Ticket ticket = new Ticket(i);
                ticketPool.addTicket(ticket);
                logCallback.accept(Thread.currentThread().getName()+" Ticket added");
                Thread.sleep(ticketReleaseRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
