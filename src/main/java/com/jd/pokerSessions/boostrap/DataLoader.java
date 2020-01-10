package com.jd.pokerSessions.boostrap;

import com.jd.pokerSessions.model.*;
import com.jd.pokerSessions.services.HandService;
import com.jd.pokerSessions.services.SessionService;
import org.hibernate.Hibernate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final HandService handService;
    private final SessionService sessionService;

    public DataLoader(HandService handService, SessionService sessionService) {
        this.handService = handService;
        this.sessionService = sessionService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        Session session1 = new Session();
        session1.setOwner("Julien");
        session1.setRoom("PMU");
        session1.setDate(LocalDate.now());
        Set<Hand> session1Hands = Utils.resourceReaderCsv("20191220-Bad.csv");
        session1Hands.forEach((hand -> hand.setSession(session1)));
        session1.setSessionHands(session1Hands);
        sessionService.save(session1);

        Session session2 = new Session();
        session2.setOwner("Julien");
        session2.setRoom("PMU");
        session2.setDate(LocalDate.now());
        Set<Hand> session2Hands = Utils.resourceReaderCsv("20191215-Good.csv");
        session2Hands.forEach(hand -> hand.setSession(session2));
        session2.setSessionHands(session2Hands);
        sessionService.save(session2);

        System.out.println("Dataloader :" + session1.getId().toString());
    }

}
