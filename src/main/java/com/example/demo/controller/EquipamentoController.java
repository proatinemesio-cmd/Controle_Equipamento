package com.example.demo.controller;

import com.example.demo.model.Aluno;
import com.example.demo.model.Registro;
import com.example.demo.model.Turma;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.RegistroRepository;
import com.example.demo.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*") // Isso é super importante! Permite que a tela do celular acesse essa API sem ser bloqueada pela segurança do navegador.
public class EquipamentoController {

    @Autowired
    private TurmaRepository turmaRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private RegistroRepository registroRepository;

    // Rota 1: O celular pede a lista de todas as turmas
    @GetMapping("/turmas")
    public List<Turma> listarTurmas() {
        return turmaRepository.findAll();
    }

    @GetMapping("/alunos/{id}")
    public Aluno buscarAlunoPorId(@PathVariable Long id) {
        return alunoRepository.findById(id).orElse(null);
    }
    // Rota 2: O celular avisa qual turma foi selecionada e pede os alunos dela
    @GetMapping("/alunos/turma/{turmaId}")
    public List<Aluno> listarAlunosPorTurma(@PathVariable Long turmaId) {
        return alunoRepository.findByTurmaId(turmaId);
    }
    // Rota 3: O celular envia o formulário preenchido para salvar no banco
    @PostMapping("/registros")
    public Registro salvarRegistro(@RequestBody Registro registro) {
        return registroRepository.save(registro);
    }

    @GetMapping("/registros")
    public List<Registro> listarRegistros() {
        return registroRepository.findAll();
    }
}


