package com.example.demo.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "registro")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeProfessor;
    private String numeroEquipamento;
    private String tipoEquipamento;

    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    private String nomeAlunoExtra;

    // Construtores
    public Registro() {}

    // Getters e Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }
    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getNumeroEquipamento() {
        return numeroEquipamento;
    }
    public void setNumeroEquipamento(String numeroEquipamento) {
        this.numeroEquipamento = numeroEquipamento;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }
    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Aluno getAluno() {
        return aluno;
    }
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getNomeAlunoExtra() {
        return nomeAlunoExtra;
    }
    public void setNomeAlunoExtra(String nomeAlunoExtra) {
        this.nomeAlunoExtra = nomeAlunoExtra;
    }
}