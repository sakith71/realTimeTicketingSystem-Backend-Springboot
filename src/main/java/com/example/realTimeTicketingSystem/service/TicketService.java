package com.example.realTimeTicketingSystem.service;

import com.example.realTimeTicketingSystem.controller.WebSocketController;
import com.example.realTimeTicketingSystem.repository.TicketRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final WebSocketController webSocketController;

    public TicketService(TicketRepository ticketRepository, WebSocketController webSocketController) {
        this.ticketRepository = ticketRepository;
        this.webSocketController = webSocketController;
    }

    /**
     * Asynchronous task for vendors to release tickets at a fixed rate.
     *
     * @param totalTickets      Total tickets to be added.
     * @param ticketReleaseRate Time interval (in milliseconds) between adding tickets.
     */
    @Async
    public void vendorTask(int totalTickets, int ticketReleaseRate) {
        for (int i = 1; i <= totalTickets; i++) {
            try {
                // Create and save ticket
                Ticket ticket = new Ticket();
                ticket.setTicketId(i);
                ticketRepository.save(ticket);

                // Notify WebSocket clients about the new ticket
                webSocketController.broadcastTicketUpdate(ticket);

                // Delay for ticket release rate
                Thread.sleep(ticketReleaseRate);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                System.err.println("Vendor task interrupted.");
                break;
            } catch (Exception e) {
                System.err.println("Error during vendor task: " + e.getMessage());
                break;
            }
        }
    }

    /**
     * Asynchronous task for customers to purchase tickets at a fixed rate.
     *
     * @param customerRetrievalRate Time interval (in milliseconds) between ticket purchases.
     */
    @Async
    public void customerTask(int customerRetrievalRate) {
        while (true) {
            try {
                // Fetch and delete the oldest ticket
                ticketRepository.findAll().stream().findFirst().ifPresent(ticket -> {
                    ticketRepository.delete(ticket);

                    // Notify WebSocket clients about ticket purchase
                    webSocketController.broadcastTicketUpdate(ticket);
                });

                // Delay for customer retrieval rate
                Thread.sleep(customerRetrievalRate);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted status
                System.err.println("Customer task interrupted.");
                break;
            } catch (Exception e) {
                System.err.println("Error during customer task: " + e.getMessage());
                break;
            }
        }
    }
}


