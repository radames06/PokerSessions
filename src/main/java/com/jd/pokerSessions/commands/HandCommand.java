package com.jd.pokerSessions.commands;

import com.jd.pokerSessions.model.Card;
import com.jd.pokerSessions.model.Session;

import java.time.LocalDate;
import java.time.LocalTime;

public class HandCommand {

    private Long id;
    private LocalDate handDate;
    private LocalTime handTime;
    private String handId;
    private String position;
    private Card H1;
    private Card H2;
    private Card B1;
    private Card B2;
    private Card B3;
    private Card B4;
    private Card B5;
    private boolean sawFlop;
    private boolean sawTurn;
    private boolean sawRiver;
    private boolean sawShowdown;
    private Long sessionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getHandDate() {
        return handDate;
    }

    public void setHandDate(LocalDate handDate) {
        this.handDate = handDate;
    }

    public LocalTime getHandTime() {
        return handTime;
    }

    public void setHandTime(LocalTime handTime) {
        this.handTime = handTime;
    }

    public String getHandId() {
        return handId;
    }

    public void setHandId(String handId) {
        this.handId = handId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Card getH1() {
        return H1;
    }

    public void setH1(Card h1) {
        H1 = h1;
    }

    public Card getH2() {
        return H2;
    }

    public void setH2(Card h2) {
        H2 = h2;
    }

    public Card getB1() {
        return B1;
    }

    public void setB1(Card b1) {
        B1 = b1;
    }

    public Card getB2() {
        return B2;
    }

    public void setB2(Card b2) {
        B2 = b2;
    }

    public Card getB3() {
        return B3;
    }

    public void setB3(Card b3) {
        B3 = b3;
    }

    public Card getB4() {
        return B4;
    }

    public void setB4(Card b4) {
        B4 = b4;
    }

    public Card getB5() {
        return B5;
    }

    public void setB5(Card b5) {
        B5 = b5;
    }

    public boolean isSawFlop() {
        return sawFlop;
    }

    public void setSawFlop(boolean sawFlop) {
        this.sawFlop = sawFlop;
    }

    public boolean isSawTurn() {
        return sawTurn;
    }

    public void setSawTurn(boolean sawTurn) {
        this.sawTurn = sawTurn;
    }

    public boolean isSawRiver() {
        return sawRiver;
    }

    public void setSawRiver(boolean sawRiver) {
        this.sawRiver = sawRiver;
    }

    public boolean isSawShowdown() {
        return sawShowdown;
    }

    public void setSawShowdown(boolean sawShowdown) {
        this.sawShowdown = sawShowdown;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
