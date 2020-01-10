package com.jd.pokerSessions.model;


public enum Card {

    _2h(2,"h"), _3h(3,"h"), _4h(4,"h"),
    _5h(5,"h"), _6h(6,"h"), _7h(7,"h"),
    _8h(8,"h"), _9h(9,"h"), _Th(10,"h"),
    _Jh(11,"h"), _Qh(12,"h"), _Kh(13,"h"),
    _Ah(14,"h"),

    _2c(2,"c"), _3c(3,"c"), _4c(4,"c"),
    _5c(5,"c"), _6c(6,"c"), _7c(7,"c"),
    _8c(8,"c"), _9c(9,"c"), _Tc(10,"c"),
    _Jc(11,"c"), _Qc(12,"c"), _Kc(13,"c"),
    _Ac(14,"c"),

    _2s(2,"s"), _3s(3,"s"), _4s(4,"s"),
    _5s(5,"s"), _6s(6,"s"), _7s(7,"s"),
    _8s(8,"s"), _9s(9,"s"), _Ts(10,"s"),
    _Js(11,"s"), _Qs(12,"s"), _Ks(13,"s"),
    _As(14,"s"),

    _2d(2,"d"), _3d(3,"d"), _4d(4,"d"),
    _5d(5,"d"), _6d(6,"d"), _7d(7,"d"),
    _8d(8,"d"), _9d(9,"d"), _Td(10,"d"),
    _Jd(11,"d"), _Qd(12,"d"), _Kd(13,"d"),
    _Ad(14,"d");

    private String cardColor;
    private Integer cardValue;
    private Card(Integer cardValue, String cardColor) {

        this.cardValue = cardValue;
        this.cardColor = cardColor;
    }
    public String getColor() { return this.cardColor; }
    public Integer getValue() { return this.cardValue; }
    public String toString() {
        return this.name().substring(1,3);
    }
}
