package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Estudiante;

public interface IEstudianteRepo {


    public Estudiante selecionarPorId(Integer id);
    public List<Estudiante> selecionarTodos();
    

}