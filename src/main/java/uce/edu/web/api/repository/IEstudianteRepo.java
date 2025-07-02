package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;

public interface IEstudianteRepo {


    public Estudiante selecionarPorId(Integer id);
    public List<Estudiante> selecionarTodos(String genero);
    public void actualizarPorId(Estudiante estudiante);
    public void actualizarParcialPorId(Estudiante estudiante);
    public void borrarPorId(Integer id);
    public void insertar(Estudiante estudiante);

}