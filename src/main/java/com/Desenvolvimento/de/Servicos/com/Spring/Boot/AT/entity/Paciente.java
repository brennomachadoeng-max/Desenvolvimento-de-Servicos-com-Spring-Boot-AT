package com.Desenvolvimento.de.Servicos.com.Spring.Boot.AT.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
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

    public Paciente(String nome, String cpf, LocalDate dataNascimento, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

}
