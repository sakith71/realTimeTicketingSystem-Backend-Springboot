package com.example.realTimeTicketingSystem.model;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> tickets = new LinkedList<>();
    private final int maxCapacity;
    private int addTicketCount;
    private int removeTicketCount;

    public TicketPool(int maxCapacity) {
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
        addTicketCount++;
        System.out.println("Ticket " + addTicketCount + " added by " + Thread.currentThread().getName() + " | Total Tickets: " + tickets.size());
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
        removeTicketCount++;
        System.out.println("Ticket " + removeTicketCount + " bought by " + Thread.currentThread().getName() + " | Remaining Tickets: " + tickets.size());
        notifyAll();
        return ticket;
    }
}
