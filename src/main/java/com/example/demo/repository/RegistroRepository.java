package com.example.demo.repository;

import com.example.demo.model.Registro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
    List<Registro> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}