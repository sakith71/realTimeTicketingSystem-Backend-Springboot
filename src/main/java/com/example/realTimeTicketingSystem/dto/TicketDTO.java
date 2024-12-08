package com.example.realTimeTicketingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TicketDTO {
    private Long id;
    private String eventName;
}
