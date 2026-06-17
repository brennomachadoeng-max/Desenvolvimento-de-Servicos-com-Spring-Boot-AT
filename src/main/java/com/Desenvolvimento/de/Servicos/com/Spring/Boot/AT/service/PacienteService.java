package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.service;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Paciente;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.exception.RecursoNaoEncontradoException;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente cadastrar(Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public Paciente buscarPacientePorId(Long id){
        return pacienteRepository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Paciente não encontrado com id: " + id));
    }
    public List<Paciente> listarPacientes(){
        return pacienteRepository.findAll();
    }
    public void excluirPaciente(Long id){
        if (!pacienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Paciente não encontrado com id: " + id);
        }
        pacienteRepository.deleteById(id);
    }


}
