package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
        SELECT m FROM Medico m LEFT JOIN m.consultas c
        GROUP BY m ORDER BY COUNT(c) DESC
    """)
    List<Medico> rankingConsultasRealizadas();
}
