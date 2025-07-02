package uce.edu.web.api.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import uce.edu.web.api.repository.IProfesorRepo;
import uce.edu.web.api.repository.modelo.Profesor;


@ApplicationScoped
public class ProfesorServiceImpl implements IProfesorService {

    @Inject
    private IProfesorRepo profesorRepo;

    @Override
    public Profesor buscarPorId(Integer id) {
        return this.profesorRepo.selecionarPorId(id);
    }

    @Override
    public List<Profesor> buscarTodos() {
        return this.profesorRepo.selecionarTodos();
    }

    @Override
    public void actualizarPorId(Profesor profesor) {
        this.profesorRepo.actualizarPorId(profesor);
    }

    @Override
    public void actualizarParcialPorId(Profesor profesor) {
        this.profesorRepo.actualizarParcialPorId(profesor); 
    }

    @Override
    public void borrarPorId(Integer id) {
        this.profesorRepo.borrarPorId(id);
    }

    @Override
    public void guardar(Profesor profesor) {
        this.profesorRepo.insertar(profesor);
    }
    
}
