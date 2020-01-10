package com.jd.pokerSessions.repositories;

import com.jd.pokerSessions.model.Hand;
import com.jd.pokerSessions.model.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SessionRepository extends CrudRepository<Session, Long> {

    // TODO : Voir si cette classe n'est pas inutile

}
