package uce.edu.web.api.controller;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    public Profesor consultarPorId(@PathParam("id")Integer id) {
        return this.profesorService.buscarPorId(id); 
    }

    @GET
    @Path("")
    public List<Profesor> consultarTodos() {
        return this.profesorService.buscarTodos();
    }

    @POST
    @Path("")
    //Puede tener o no tener el @RequestBody
    public void guardar(@RequestBody Profesor profesor) {
        this.profesorService.guardar(profesor);
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        Profesor p = this.profesorService.buscarPorId(id);
        if (profesor.getNombre() != null) {
            p.setNombre(profesor.getNombre());
        }
        if (profesor.getApellido() != null) {
            p.setApellido(profesor.getApellido());
        }
        if (profesor.getAsignatura() != null) {
            p.setAsignatura(profesor.getAsignatura());
        }
        if(profesor.getFechaNacimiento() != null) {
            p.setFechaNacimiento(profesor.getFechaNacimiento());
        }
        this.profesorService.actualizarParcialPorId(p);
    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
    }
}