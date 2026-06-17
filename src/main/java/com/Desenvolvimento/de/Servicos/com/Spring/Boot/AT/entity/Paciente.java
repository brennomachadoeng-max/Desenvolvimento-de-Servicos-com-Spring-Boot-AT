package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Data
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;
    @OneToMany(mappedBy = "paciente")
    private List<Internacao> internacoes;
}
