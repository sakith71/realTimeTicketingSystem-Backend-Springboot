package com.example.realTimeTicketingSystem.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void broadcastTicketUpdate(Ticket ticket) {
        messagingTemplate.convertAndSend("/topic/tickets", ticket);
    }
}


