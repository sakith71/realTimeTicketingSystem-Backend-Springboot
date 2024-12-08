package com.example.realTimeTicketingSystem.model;

public class Ticket {
    private int ticketId;

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketId=" + ticketId + '}';
    }
}
