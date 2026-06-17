package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.service;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Consulta;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository.ConsultaRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    public ConsultaService(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    public Consulta cadastrar(Consulta consulta){
        return consultaRepository.save(consulta);
    }
}
