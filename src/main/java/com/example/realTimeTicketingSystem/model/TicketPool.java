/**
 * Represents a pool of tickets that can be added and removed by multiple threads.
 * The pool has a maximum capacity, and threads will wait until space becomes available
 * if the pool is full, or until a ticket becomes available if the pool is empty.
 *
 * @author [Sakith Umagiliya]
 * @version 1.0
 * @since 2024-11-20
 */
package com.example.realTimeTicketingSystem.model;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    /**
     * The queue of tickets in the pool.
     * The maximum number of tickets that can be held in the pool.
     * The number of tickets that have been added to the pool.
     * The number of tickets that have been removed from the pool.
     */
    private final Queue<Ticket> tickets = new LinkedList<>();
    private final int maxCapacity;
    private int addTicketCount;
    private int removeTicketCount;
    /**
     * Constructs a new TicketPool with specified maximum capacity.
     *
     * @param maxCapacity The maximum number of tickets that can be held in the pool
     */
    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Adds a ticket to the pool. If the pool is at maximum capacity,
     * the thread will wait until space becomes available.
     *
     * @param ticket The ticket to be added to the pool
     * @throws InterruptedException if the thread is interrupted while waiting
     */
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

    /**
     * Removes and returns a ticket from the pool. If the pool is empty,
     * the thread will wait until a ticket becomes available.
     *
     * @return The removed ticket, or null if the thread was interrupted
     * @throws InterruptedException if the thread is interrupted while waiting
     */
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

    /**
     * Gets the current number of tickets in the pool.
     *
     * @return The current number of tickets in the pool
     */
    public synchronized int getTicketCount() {
        return tickets.size();
    }

}
