package com.jd.pokerSessions.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name = "hands")
public class Hand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hand_date")
    private LocalDate handDate;

    @Column(name = "hand_time")
    private LocalTime handTime;

    @Column(name = "hand_id")
    private String handId;

    @Column(name = "position")
    private String position;

    @Column(name = "H1")
    private Card H1;

    @Column(name = "H2")
    private Card H2;

    @Column(name = "B1")
    private Card B1;

    @Column(name = "B2")
    private Card B2;

    @Column(name = "B3")
    private Card B3;

    @Column(name = "B4")
    private Card B4;

    @Column(name = "B5")
    private Card B5;

    @Column(name = "saw_flop")
    private boolean sawFlop;

    @Column(name = "saw_turn")
    private boolean sawTurn;

    @Column(name = "saw_river")
    private boolean sawRiver;

    @Column(name = "saw_showdown")
    private boolean sawShowdown;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    public String toString() {
        if ((H1 != null) && (H2 != null)) {
            throw new RuntimeException("Main invalide");
        }

        String returnValue = H1.toString() + " " + H2.toString();
        if (sawFlop) {
            returnValue += " / " + B1.toString() + " " + B2.toString() + " " + B3.toString();
            if (sawTurn) {
                returnValue += " " + B4.toString();
                if(sawRiver) {
                    returnValue += " " + B5;
                }
            }
        }
        return returnValue;
    }

    public Set<Integer> flopPair() {
        if(!sawFlop) {
            return null;
        } else {
            Set<Integer> returnPair = new HashSet<>();
            Integer[] hole = {H1.getValue(), H2.getValue()};
            Integer[] board = {B1.getValue(), B2.getValue(), B3.getValue()};
            for (int i=0; i<=1; i++) {
                int duplicate = 0;
                for (int j=0; j<=2; j++) {
                    if (hole[i] == board[j]) {
                        duplicate ++;
                    }
                }
                if (duplicate == 1) {
                    returnPair.add(hole[i]);
                }
            }
            return returnPair;
        }
    }

    public boolean flopTopPair() {
        if (sawFlop & flopPair().size()>0) {
            Integer[] flop = {B1.getValue(), B2.getValue(), B3.getValue()};
            if (Collections.max(flopPair()) == Collections.max(Arrays.asList(flop))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean pocketPair() {
        return (H1.getValue() == H2.getValue());
    }

    public boolean flopOverPair() {
        if (sawFlop && pocketPair()) {
            Integer[] board = {B1.getValue(), B2.getValue(), B3.getValue()};
            if (H1.getValue() > Collections.max(Arrays.asList(board))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Le flop contient une paire
    public Integer flopBoardPair() {
        if(B1.getValue() == B2.getValue() && B1.getValue() == B3.getValue()) {
            return null;
        } else {
            if(B1.getValue() == B2.getValue()) return B1.getValue();
            if(B1.getValue() == B3.getValue()) return B1.getValue();
            if(B2.getValue() == B3.getValue()) return B2.getValue();
            return null;
        }
    }

    public Integer flopPocketSet() {
        if (sawFlop && pocketPair() && flopBoardPair()==null) {
            Integer[] boardArray = {B1.getValue(), B2.getValue(), B3.getValue()};
            List<Integer> board = Arrays.asList(boardArray);
            if (board.contains(H1.getValue())) return H1.getValue();
            if (board.contains(H2.getValue())) return H2.getValue();
            return null;
        } else {
            return null;
        }
    }

    public Integer flopBoardSet() {
        if (sawFlop && !pocketPair() && flopBoardPair()!=null) {
            if (flopBoardPair() == H1.getValue()) return H1.getValue();
            if (flopBoardPair() == H2.getValue()) return H2.getValue();
            return null;
        } else {
            return null;
        }
    }

    public String flopSuited3() {
        if (sawFlop && B1.getColor()==B2.getColor() && B1.getColor()==B3.getColor()) {
            return B1.getColor();
        } else {
            return null;
        }
    }

    public String flopSuited2() {
        if (sawFlop) {
            if (B1.getColor()==B2.getColor() && B1.getColor()!=B3.getColor()) return B1.getColor();
            if (B1.getColor()==B3.getColor() && B1.getColor()!=B2.getColor()) return B1.getColor();
            if (B2.getColor()==B3.getColor() && B2.getColor()!=B1.getColor()) return B2.getColor();
            return null;
        } else {
            return null;
        }
    }

    public String handSuited() {
        if (H1.getColor()==H2.getColor()) {
            return H1.getColor();
        } else {
            return null;
        }
    }

    public boolean flopFlush() {
        if (sawFlop
            && H1.getColor()==H2.getColor()
            && H1.getColor()==B1.getColor()
            && H1.getColor()==B2.getColor()
            && H1.getColor()==B3.getColor()) {
            return true;
        } else {
            return false;
        }
    }

    // Flush Draw avec 2 holecards
    public boolean flopPocketFlushDraw() {
        if (handSuited()!=null && flopSuited2()!=null) {
            return handSuited()==flopSuited2();
        } else {
            return false;
        }
    }

    // Flush Draw avec 1 holecard et 3 cartes du flop
    public boolean flopBoardFlushDraw() {
        if (handSuited()==null && flopSuited3()!=null
            && (H1.getColor()==flopSuited3() || H2.getColor()==flopSuited3())) {
            return true;
        } else {
            return false;
        }
    }

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

    public String getHandId() {
        return handId;
    }

    public void setHandId(String handId) {
        this.handId = handId;
    }

    public void setH(Card card1, Card card2) {
        H1 = card1;
        H2 = card2;
    }

    public void setB(Card card1, Card card2, Card card3) {
        setB(card1, card2, card3, null, null);
    }

    public void setB(Card card1, Card card2, Card card3, Card card4, Card card5) {
        B1 = card1;
        B2 = card2;
        B3 = card3;
        B4 = card4;
        B5 = card5;
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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalTime getHandTime() {
        return handTime;
    }

    public void setHandTime(LocalTime handTime) {
        this.handTime = handTime;
    }
}
