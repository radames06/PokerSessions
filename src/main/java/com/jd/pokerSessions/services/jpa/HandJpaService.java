package com.jd.pokerSessions.services.jpa;

import com.jd.pokerSessions.model.Hand;
import com.jd.pokerSessions.repositories.HandRepository;
import com.jd.pokerSessions.services.HandService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class HandJpaService implements HandService {

    private final HandRepository handRepository;

    public HandJpaService(HandRepository handRepository) {
        this.handRepository = handRepository;
    }

    @Override
    public Set<Hand> findAll() {

        Set<Hand> hands = new HashSet<>();

        handRepository.findAll().forEach(hands::add);

        return hands;
    }

    @Override
    public Hand findById(Long aLong) {
        return handRepository.findById(aLong).orElse(null);
    }

    @Override
    public Hand save(Hand object) {
        return handRepository.save(object);
    }

    @Override
    public void delete(Hand object) {
        handRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        handRepository.deleteById(aLong);
    }
}
