package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.integração.controller;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Paciente;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PacienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final String PATH = "/paciente";

    @BeforeEach
    void setUp() {
        pacienteRepository.deleteAll();
    }

    @Test
    void deveCadastrarPacienteComSucesso() throws Exception {
        Paciente novoPaciente = new Paciente("Brenno", "00000000191", LocalDate.of(2003, 3, 10), "75999999999");
        String pacienteJson = objectMapper.writeValueAsString(novoPaciente);
        mockMvc.perform(post(PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pacienteJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Brenno"));

        assertEquals(1, pacienteRepository.count());
    }

    @Test
    void deveBuscarPacientePorId() throws Exception {
        Paciente pacienteSalvo = pacienteRepository.save(new Paciente("Brenno", "00000000191", LocalDate.of(2003, 3, 10), "75999999999"));

        mockMvc.perform(get(PATH + "/" + pacienteSalvo.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(pacienteSalvo.getId()))
                .andExpect(jsonPath("$.nome").value("Brenno"));
    }

    @Test
    void deveListarTodosOsPacientes() throws Exception {
        pacienteRepository.save(new Paciente("Brenno", "00000000191", LocalDate.of(2003, 3, 10), "75999999999"));
        pacienteRepository.save(new Paciente("Maria", "11111111111", LocalDate.of(1995, 5, 20), "75888888888"));

        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void deveExcluirPacienteComSucesso() throws Exception {
        Paciente pacienteSalvo = pacienteRepository.save(new Paciente("Brenno", "00000000191", LocalDate.of(2003, 3, 10), "75999999999"));

        mockMvc.perform(delete(PATH + "/" + pacienteSalvo.getId()))
                .andExpect(status().isNoContent());

        assertTrue(pacienteRepository.findById(pacienteSalvo.getId()).isEmpty());
    }
}