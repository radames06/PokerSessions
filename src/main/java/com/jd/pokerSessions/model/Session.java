package com.jd.pokerSessions.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "session_date")
    private LocalDate date;

    @Column(name = "owner")
    private String owner;

    @Column(name = "room")
    private String room;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "session")
    private Set<Hand> sessionHands = new HashSet<>();

    public Session addHand(Hand hand) {
        hand.setSession(this);
        this.sessionHands.add(hand);
        return this;
    }

    public long nbFlopPairs() {
        return sessionHands.stream().filter(hh -> hh.flopPair().size()==1).count();
    }

    public long nbFlopDoublePairs() {
        return sessionHands.stream().filter(hh -> hh.flopPair().size()==2).count();
    }

    public long nbFlopTopPairs() {
        return sessionHands.stream().filter(hh -> hh.flopTopPair()).count();
    }

    public long nbPocketPairs() {
        return sessionHands.stream().filter(hh -> hh.pocketPair()).count();
    }

    public long nbFlopOverPairs() {
        return sessionHands.stream().filter(hh -> hh.flopOverPair()).count();
    }

    public long nbFlopPocketSets() {
        return sessionHands.stream().filter(hh -> hh.flopPocketSet()!=null).count();
    }

    public long nbFlopBoardSets() {
        return sessionHands.stream().filter(hh -> hh.flopBoardSet()!=null).count();
    }

    public long nbFlopFlush() {
        return sessionHands.stream().filter(hh -> hh.flopFlush()).count();
    }

    public long nbFlopPocketFlushDraws() {
        return sessionHands.stream().filter(hh -> hh.flopPocketFlushDraw()).count();
    }

    public long nbFlopBoardFlushDraws() {
        return sessionHands.stream().filter(hh -> hh.flopBoardFlushDraw()).count();
    }

    public Integer getNbHands() {
        return sessionHands.size();
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

    public Set<Hand> getSessionHands() {
        return sessionHands;
    }

    public void setSessionHands(Set<Hand> sessionHands) {
        this.sessionHands = sessionHands;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
