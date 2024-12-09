package com.example.realTimeTicketingSystem.model;

public class Ticket {
    private final int ticketId;

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketId() {
        return ticketId;
    }

    @Override
    public String toString() {
        return "ticketId=" + ticketId ;
    }
}
