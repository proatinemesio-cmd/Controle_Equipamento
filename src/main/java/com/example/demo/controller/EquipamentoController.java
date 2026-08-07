package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.Registro;
import com.example.demo.model.Turma;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.RegistroRepository;
import com.example.demo.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class EquipamentoController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private RegistroRepository registroRepository;

    @GetMapping("/turmas")
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    @GetMapping("/alunos/{id}")
    public Aluno buscarAlunoPorId(@PathVariable Long id) {
        return alunoRepository.findById(id).orElse(null);
    }

    @GetMapping("/alunos/turma/{turmaId}")
    public List<Aluno> listarAlunosPorTurma(@PathVariable Long turmaId) {
        return alunoRepository.findByTurmaId(turmaId);
    }

    @PostMapping("/registros")
    public Registro salvarRegistro(@RequestBody Registro registro) {
        if (registro.getDataHora() == null) {
            registro.setDataHora(LocalDateTime.now());
        }
        return registroRepository.save(registro);
    }

    // Rota de listagem com suporte a filtro por data para o painel
    @GetMapping("/registros")
    public List<Registro> listarRegistros(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        if (data != null) {
            // FiltrA do início ao fim da data escolhida no painel
            LocalDateTime inicioDoDia = data.atStartOfDay();
            LocalDateTime fimDoDia = data.atTime(23, 59, 59);
            return registroRepository.findByDataHoraBetween(inicioDoDia, fimDoDia);
        } else {
            LocalDateTime inicioHoje = LocalDate.now().atStartOfDay();
            LocalDateTime fimHoje = LocalDate.now().atTime(23, 59, 59);
            return registroRepository.findByDataHoraBetween(inicioHoje, fimHoje);
        }
    }
}