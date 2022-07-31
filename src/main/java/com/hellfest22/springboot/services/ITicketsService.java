package com.hellfest22.springboot.services;

import com.hellfest22.springboot.model.Tickets;

import java.util.Optional;

public interface ITicketsService {
    Iterable<Tickets> getAll();
    Optional<Tickets> getByID(String id);
    Optional<Tickets> getByFullName(String name, String fname);
    Tickets addTicket(Tickets t);
}
