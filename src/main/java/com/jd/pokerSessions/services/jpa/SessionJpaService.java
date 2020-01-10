package com.jd.pokerSessions.services.jpa;

import com.jd.pokerSessions.model.Session;
import com.jd.pokerSessions.repositories.SessionRepository;
import com.jd.pokerSessions.services.SessionService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class SessionJpaService implements SessionService {

    private final SessionRepository sessionRepository;

    public SessionJpaService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Set<Session> findAll() {

        Set<Session> sessions = new HashSet<>();

        sessionRepository.findAll().forEach(sessions::add);

        return sessions;
    }

    @Override
    public Session findById(Long aLong) {
        System.out.println(aLong);
        Optional returnOption = sessionRepository.findById(aLong);
        System.out.println(returnOption.toString());
        System.out.println(returnOption.isPresent());
        return sessionRepository.findById(aLong).orElse(null);
    }

    @Override
    public Session save(Session object) {
        return sessionRepository.save(object);
    }

    @Override
    public void delete(Session object) {
        sessionRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        sessionRepository.deleteById(aLong);
    }
}
