package ua.maestr0.dao;

import ua.maestr0.model.Ticket;

public class TicketDAO extends GenericDAO<Ticket, Long> {
    public TicketDAO() {
        super(Ticket.class);
    }
}
