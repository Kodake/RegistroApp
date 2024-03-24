package com.registroapp.servicio;

import com.registroapp.modelo.Estudiante;
import java.util.List;

public interface IEstudianteServicio {
    public List<Estudiante> listar();
    public Estudiante buscarPorId(Integer idEstudiante);
    public void guardar(Estudiante estudiante);
    public void eliminar(Estudiante estudiante);
}
