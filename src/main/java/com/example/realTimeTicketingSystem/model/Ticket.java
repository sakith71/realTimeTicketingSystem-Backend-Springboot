/**
 * Represents a ticket with a unique identifier.
 *
 * @author [Sakith Umagiliya]
 * @version 1.0
 * @since 2024-11-20
 */
package com.example.realTimeTicketingSystem.model;

public class Ticket {

    private final int ticketId;

    /**
     * Constructs a new Ticket with a specified ID.
     *
     * @param ticketId The unique identifier for the ticket
     */
    public Ticket(int ticketId) {
        this.ticketId = ticketId;
    }

    /**
     * Gets the ID of the ticket.
     *
     * @return The ticket's unique identifier
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Returns a string representation of the ticket.
     *
     * @return A string containing the ticket's ID
     */
    @Override
    public String toString() {
        return "ticketId=" + ticketId ;
    }
}
