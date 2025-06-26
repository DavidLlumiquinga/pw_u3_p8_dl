package uce.edu.web.api.service;
import uce.edu.web.api.repository.modelo.Profesor;

import java.util.List;
public interface IProfesorService {

    public Profesor buscarPorId(Integer id);
     public List<Profesor> buscarTodos();
}