package com.hellfest22.springboot.services;

import com.hellfest22.springboot.model.Ticket;

import java.util.Optional;

public interface ITicketsService {
    Iterable<Ticket> getAll();
    Optional<Ticket> getById(String id);
    Iterable<Ticket> getByFullName(String name, String fname);
    Iterable<Ticket> getByEmail(String email);
    Ticket addTicket(Ticket t);
    Ticket updateTicket(Ticket t);
    void deleteTicket(Ticket t);
}
