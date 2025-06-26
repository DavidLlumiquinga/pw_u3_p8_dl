package uce.edu.web.api.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Profesor;
import jakarta.persistence.TypedQuery;
import java.util.List;

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
    public List<Profesor> selecionarTodos() {
   
        TypedQuery<Profesor> myQuery = this.entityManager.createQuery("SELECT p FROM Profesor p", Profesor.class);
        return myQuery.getResultList();
    }
}