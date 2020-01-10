package com.jd.pokerSessions.repositories;

import com.jd.pokerSessions.model.Hand;
import org.springframework.data.repository.CrudRepository;

// TODO : Pas clair s'il faut garder cette classe - Elle est utilisée dans le service HandService
public interface HandRepository extends CrudRepository<Hand, Long> {
}
