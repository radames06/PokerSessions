package com.jd.pokerSessions.model;

public enum CardColor {
    SPADE("s"), HEART("h"), DIAMOND("d"), CLUB("c");

    private String cardColor;
    private CardColor(String cardColor) {
        this.cardColor = cardColor;
    }
    public String getCardColor() { return this.cardColor; };
}
