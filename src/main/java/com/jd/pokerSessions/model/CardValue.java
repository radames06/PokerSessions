package com.jd.pokerSessions.model;

public enum CardValue {
    _2(2), _3(3), _4(4), _5(5), _6(6), _7(7), _8(8), _9(9), _T(10), _J(11), _Q(12), _K(13);

    private Integer cardVal;
    private CardValue(Integer cardVal) {
        this.cardVal = cardVal;
    }
    public Integer getCardVal() {
        return this.cardVal;
    }
}
