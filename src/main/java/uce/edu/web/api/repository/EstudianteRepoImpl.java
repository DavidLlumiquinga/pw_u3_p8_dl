package uce.edu.web.api.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import uce.edu.web.api.repository.modelo.Estudiante;

//debe teneer estas implementaciones, aplicacion scpoed me da un ambito de implementacion, de que los datos se van a mantener dentro de la apliacion
@Transactional
@ApplicationScoped
public class EstudianteRepoImpl implements IEstudianteRepo {

    //interfaz entiti manager, esta interfaz me permite interactuar con la base de datos
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Estudiante selecionarPorId(Integer id) {
        return this.entityManager.find(Estudiante.class, id);
        //find me permite buscar un objeto por su id, en este caso el id del estudiante
    }
    
}
