package uce.edu.web.api.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Profesor;

@Transactional
@ApplicationScoped
public class ProfesorRepoImpl implements IProfesorRepo {


    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Profesor selecionarPorId(Integer id) {
        return this.entityManager.find(Profesor.class, id);

    }
    @Override
    public List<Profesor> selecionarTodos(String asignatura) {
   
        TypedQuery<Profesor> myQuery = this.entityManager.createQuery("SELECT p FROM Profesor p WHERE e.asignatura=:asignatura", Profesor.class);
        myQuery.setParameter("asignatura", asignatura);
        return myQuery.getResultList();
    }
     @Override
    public void actualizarPorId(Profesor profesor) {
        this.entityManager.merge(profesor);
    }

    @Override
    public void actualizarParcialPorId( Profesor profesor) {
        this.entityManager.merge(profesor);
    }

    @Override
    public void borrarPorId(Integer id) {
        this.entityManager.remove(this.selecionarPorId(id));
    }

    @Override
    public void insertar(Profesor profesor) {
   
        this.entityManager.persist(profesor);
    }
}