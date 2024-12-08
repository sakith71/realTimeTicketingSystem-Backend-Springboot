package com.example.realTimeTicketingSystem.controller;

import com.example.realTimeTicketingSystem.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200") // Enable CORS for frontend
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<Ticket> getAvailableTickets() {
        return ticketService.getAvailableTickets();
    }

    @PostMapping("/add")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    @PostMapping("/purchase/{id}")
    public String purchaseTicket(@PathVariable Long id) {
        return ticketService.purchaseTicket(id);
    }
}

