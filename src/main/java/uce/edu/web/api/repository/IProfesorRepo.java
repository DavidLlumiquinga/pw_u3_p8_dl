package uce.edu.web.api.repository;

import java.util.List;

import uce.edu.web.api.repository.modelo.Profesor;
public interface IProfesorRepo {

    public Profesor selecionarPorId(Integer id);
    public List<Profesor> selecionarTodos();
    public void actualizarPorId(Profesor profesor);
    public void actualizarParcialPorId(Profesor profesor);
    public void borrarPorId(Integer id);
    public void insertar(Profesor profesor);
}
