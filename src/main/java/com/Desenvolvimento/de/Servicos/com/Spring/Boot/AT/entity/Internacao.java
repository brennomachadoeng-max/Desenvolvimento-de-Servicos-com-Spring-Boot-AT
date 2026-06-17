package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "internacao")
@Data
public class Internacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataEntrada;
    private LocalDate dataAlta;
    private String quarto;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
