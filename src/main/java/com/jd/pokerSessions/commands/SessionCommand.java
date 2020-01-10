package com.jd.pokerSessions.commands;

import com.jd.pokerSessions.model.Hand;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class SessionCommand {

    private Long id;
    private LocalDate date;
    private String owner;
    private String room;
    private Set<HandCommand> sessionHands = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Set<HandCommand> getSessionHands() {
        return sessionHands;
    }

    public void setSessionHands(Set<HandCommand> sessionHands) {
        this.sessionHands = sessionHands;
    }
}
