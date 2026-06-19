package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.unitario.service;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Medico;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.repository.MedicoRepository;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.service.MedicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MedicoServiceTest {

    @Mock
    private MedicoRepository medicoRepository;

    @InjectMocks
    private MedicoService medicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarMedicoComSucesso() {

        Medico medicoParaCadastrar = new Medico("Dr. Rodrigo", "CRM/BA 123456", "Cardiologia");

        when(medicoRepository.save(any(Medico.class))).thenReturn(medicoParaCadastrar);
        Medico medicoSalvo = medicoService.cadastrar(medicoParaCadastrar);

        assertNotNull(medicoSalvo);
        assertEquals("Dr. Rodrigo", medicoSalvo.getNome());
        assertEquals("CRM/BA 123456", medicoSalvo.getCrm());
        verify(medicoRepository, times(1)).save(medicoParaCadastrar);
    }
}