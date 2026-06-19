package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.config;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Medico;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Paciente;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository.MedicoRepository;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository.PacienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.swing.plaf.PanelUI;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    DataLoader(MedicoRepository medicoRepository, PacienteRepository pacienteRepository) {
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void run(String... args) {
        if (medicoRepository.count() == 0) {
            Medico cardiologista = new Medico("Dr. Carlos", "CRM123", "Cardiologista");
            Medico ortopedista = new Medico("Dr. Pedro", "CRM456", "Ortopedista");
            medicoRepository.save(cardiologista);
            medicoRepository.save(ortopedista);
        }
        if (pacienteRepository.count() == 0) {
            Paciente joao = new Paciente("João Silva","11111111111", LocalDate.of(1990, 5, 10), "75999999999");
            Paciente maria = new Paciente("Maria Oliveira", "22222222222", LocalDate.of(1995, 8, 20), "75888888888");
            pacienteRepository.save(joao);
            pacienteRepository.save(maria);
        }

    }

}
