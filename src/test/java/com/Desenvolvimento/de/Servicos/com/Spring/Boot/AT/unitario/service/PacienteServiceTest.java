package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.unitario.service;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Paciente;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.exception.RecursoNaoEncontradoException;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository.PacienteRepository;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.service.PacienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.Mockito.*;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarPaciente() {
        Paciente pacienteParaCadastrar = new Paciente("Brenno", "00000000191", LocalDate.of(2003, 3, 10), "75999999999");
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(pacienteParaCadastrar);
        Paciente pacienteSalvo = pacienteService.cadastrar(pacienteParaCadastrar);
        assertNotNull(pacienteSalvo);
        assertEquals("Brenno", pacienteSalvo.getNome());
    }

    @Test
    void deveBuscarPacientePorIdComSucesso() {
        Long idExistente = 1L;
        Paciente pacienteMock = new Paciente("Brenno", "00000000191", LocalDate.of(2003, 3, 10), "75999999999");
        ReflectionTestUtils.setField(pacienteMock, "id", idExistente);

        when(pacienteRepository.findById(idExistente)).thenReturn(Optional.of(pacienteMock));
        Paciente resultado = pacienteService.buscarPacientePorId(idExistente);

        assertNotNull(resultado);
        assertEquals(idExistente, resultado.getId());
        assertEquals("Brenno", resultado.getNome());
        verify(pacienteRepository, times(1)).findById(idExistente);
    }

    @Test
    void deveLancatExcecaoAoBuscarIdInexistente() {
        Long idInexistente = 99L;
        when(pacienteRepository.findById(idInexistente)).thenReturn(Optional.empty());
        RecursoNaoEncontradoException excecao = assertThrows(RecursoNaoEncontradoException.class, () -> {
            pacienteService.buscarPacientePorId(idInexistente);
        });
        assertEquals("Paciente não encontrado com id: 99", excecao.getMessage());
        verify(pacienteRepository, times(1)).findById(idInexistente);
    }

    @Test
    void deveExcluirPacienteComSucesso() {
        Long idExistente = 1L;

        when(pacienteRepository.existsById(idExistente)).thenReturn(true);

        assertDoesNotThrow(() -> pacienteService.excluirPaciente(idExistente));
        verify(pacienteRepository, times(1)).deleteById(idExistente);
    }

    @Test
    void deveLancatExcecaoAoExcluirPacienteInexistente() {
        Long idInexistente = 99L;
        when(pacienteRepository.existsById(idInexistente)).thenReturn(false);

        RecursoNaoEncontradoException excecao = assertThrows(RecursoNaoEncontradoException.class, () -> {
            pacienteService.excluirPaciente(idInexistente);
        });

        assertEquals("Paciente não encontrado com id: 99", excecao.getMessage());
        verify(pacienteRepository, never()).deleteById(idInexistente);
    }
}