package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.service;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Medico;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicoService {
    private final MedicoRepository medicoRepository;
    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public Medico cadastrar(Medico medico){
        return medicoRepository.save(medico);
    }
    public List<Medico> listaMedicos(){
        return medicoRepository.findAll();
    }
    public List<Medico> buscarRankingMedicos() {
        return medicoRepository.rankingConsultasRealizadas();
    }
}
