package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
