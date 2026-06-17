package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "medicos")
@Data
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String crm;
    private String especialidade;
    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
}
