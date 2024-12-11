package com.example.realTimeTicketingSystem.model;

import java.util.function.Consumer;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;
    private final int ticketsPerVendor;
    private final Consumer<String> logCallback;
    private int ticketCount = 0;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int ticketsPerVendor,Consumer<String> logCallback) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.ticketsPerVendor = ticketsPerVendor;
        this.logCallback = logCallback;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= ticketsPerVendor; i++) {
                Ticket ticket = new Ticket(i);
                ticketPool.addTicket(ticket);
                ticketCount++;
                logCallback.accept("Ticket " + ticketCount + " added by " + Thread.currentThread().getName());
                Thread.sleep(ticketReleaseRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
