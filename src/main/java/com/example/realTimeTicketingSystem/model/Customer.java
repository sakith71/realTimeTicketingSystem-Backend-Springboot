package com.example.realTimeTicketingSystem.model;

import java.util.function.Consumer;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;
    private final Consumer<String> logCallback;
    private int ticketCount = 0;


    public Customer(TicketPool ticketPool, int customerRetrievalRate,Consumer<String> logCallback) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.logCallback = logCallback;

    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Ticket ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    ticketCount++;
                    logCallback.accept("Ticket " + ticketCount + " retrieved by " + Thread.currentThread().getName());

                }
                Thread.sleep(customerRetrievalRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
