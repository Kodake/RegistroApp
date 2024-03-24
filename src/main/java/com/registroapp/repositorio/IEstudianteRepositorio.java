package com.registroapp.repositorio;

import com.registroapp.modelo.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEstudianteRepositorio extends JpaRepository<Estudiante, Integer> {
}
