package com.example.realTimeTicketingSystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;
}


