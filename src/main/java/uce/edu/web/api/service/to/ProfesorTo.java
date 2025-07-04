package uce.edu.web.api.service.to;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.controller.ProfesorController;

public class ProfesorTo {

    private Integer id;
    private String nombre;
    private String apellido;
    private String fechaNacimiento;
    private String asignatura;
     public Map<String, String> _links=new HashMap<>();

    public ProfesorTo(Integer id, String nombre, String apellido, String asignatura, String fechaNacimiento, UriInfo uriInfo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.asignatura = asignatura;
        this.fechaNacimiento = fechaNacimiento;
      
        URI todosHijos1= uriInfo.getBaseUriBuilder().path(ProfesorController.class)
        .path(ProfesorController.class,"obtenerHijosPorId").build(id);
    
    
        _links.put("hijos", todosHijos1.toString());
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
}
