package uce.edu.web.api.service.mapper;

import java.util.List;
import java.util.stream.Collectors;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.to.EstudianteTo;

public class EstudianteMapper {

    public static EstudianteTo toTo(Estudiante estudiante){
        if (estudiante == null) {
            return null;
        }
        
        EstudianteTo eTo= new EstudianteTo();
        eTo.setId(estudiante.getId());
        eTo.setNombre(estudiante.getNombre());
        eTo.setApellido(estudiante.getApellido());
        eTo.setGenero(estudiante.getGenero());
        eTo.setFechaNacimiento(estudiante.getFechaNacimiento());
        return eTo;
    }
            


    public static Estudiante toEntity(EstudianteTo estudianteTo){
        if (estudianteTo == null) {
            return null;
        }
        
        Estudiante e = new Estudiante();
        e.setId(estudianteTo.getId());
        e.setNombre(estudianteTo.getNombre());
        e.setApellido(estudianteTo.getApellido());
        e.setGenero(estudianteTo.getGenero());
        e.setFechaNacimiento(estudianteTo.getFechaNacimiento());
        return e;
    }
    
    /**
     * Convierte una lista de entidades Estudiante a una lista de EstudianteTo
     */
    public static List<EstudianteTo> toToList(List<Estudiante> estudiantes) {
        if (estudiantes == null) {
            return null;
        }
        
        return estudiantes.stream()
                .map(EstudianteMapper::toTo)
                .collect(Collectors.toList());
    }
    
    /**
     * Convierte una lista de EstudianteTo a una lista de entidades Estudiante
     */
    public static List<Estudiante> toEntityList(List<EstudianteTo> estudiantesToList) {
        if (estudiantesToList == null) {
            return null;
        }
        
        return estudiantesToList.stream()
                .map(EstudianteMapper::toEntity)
                .collect(Collectors.toList());
    }
}
