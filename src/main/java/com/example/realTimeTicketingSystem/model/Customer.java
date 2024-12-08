package com.example.realTimeTicketingSystem.model;


public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Ticket ticket = ticketPool.removeTicket();
                if (ticket != null) {
                    System.out.println(Thread.currentThread().getName() + " purchased Ticket ID: " + ticket.getTicketId());
                }
                Thread.sleep(customerRetrievalRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }
}
