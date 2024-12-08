package com.example.realTimeTicketingSystem.model;


import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> tickets;
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.tickets = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(Ticket ticket) {
        while (tickets.size() >= maxCapacity) {
            try {
                System.out.println("Ticket pool full. Vendor is waiting...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        tickets.add(ticket);
        System.out.println("Ticket added: " + ticket);
        notifyAll();
    }

    public synchronized Ticket removeTicket() {
        while (tickets.isEmpty()) {
            try {
                System.out.println("Ticket pool empty. Customer is waiting...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Ticket ticket = tickets.poll();
        System.out.println("Ticket removed: " + ticket);
        notifyAll();
        return ticket;
    }
}
