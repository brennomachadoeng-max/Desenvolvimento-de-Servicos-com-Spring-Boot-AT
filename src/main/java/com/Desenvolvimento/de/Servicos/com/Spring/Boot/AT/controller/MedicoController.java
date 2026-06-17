package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.controller;

import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity.Medico;
import com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.service.MedicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }

    @PostMapping
    public ResponseEntity<Medico> cadastrar(@RequestBody Medico medico) {
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.cadastrar(medico));
    }
    @GetMapping
    public ResponseEntity<List<Medico>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.listaMedicos());
    }
    @GetMapping("/ranking")
    public ResponseEntity<List<Medico>> rankingMedicos(){
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.buscarRankingMedicos());
    }
}
