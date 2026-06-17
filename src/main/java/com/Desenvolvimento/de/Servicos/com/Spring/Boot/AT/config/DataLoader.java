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
            Medico cardiologista = new Medico();
            cardiologista.setNome("Dr. Carlos");
            cardiologista.setCrm("CRM123");
            cardiologista.setEspecialidade("Cardiologista");
            Medico ortopedista = new Medico();
            ortopedista.setNome("Dr. Pedro");
            ortopedista.setCrm("CRM456");
            ortopedista.setEspecialidade("Ortopedista");
            medicoRepository.save(cardiologista);
            medicoRepository.save(ortopedista);
        }
        if (pacienteRepository.count() == 0) {
            Paciente joao = new Paciente();
            joao.setNome("João Silva");
            joao.setCpf("11111111111");
            joao.setDataNascimento(LocalDate.of(1990, 5, 10));
            joao.setTelefone("75999999999");
            Paciente maria = new Paciente();
            maria.setNome("Maria Oliveira");
            maria.setCpf("22222222222");
            maria.setDataNascimento(LocalDate.of(1995, 8, 20));
            maria.setTelefone("75888888888");
            pacienteRepository.save(joao);
            pacienteRepository.save(maria);
        }

    }

}
