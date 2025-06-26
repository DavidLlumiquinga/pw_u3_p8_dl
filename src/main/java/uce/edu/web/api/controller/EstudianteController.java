package uce.edu.web.api.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

@Path("/estudiantes")
public class EstudianteController {

    @Inject
    private IEstudianteService estudianteService;

    @GET
    @Path("/{id}")
    public Estudiante consultarPorId(@PathParam("id")Integer id) {
        return this.estudianteService.buscarPorId(id); 
    }

    @GET
    @Path("")
    public List<Estudiante> consultarTodos() {
        return this.estudianteService.buscarTodos();
    }

    @POST
    @Path("")
    //Puede tener o no tener el @RequestBody
    public void guardar(@RequestBody Estudiante estudiante) {
   
    }

    @PUT
    @Path("/{id}")
    public void actualizar(@PathParam("id") Integer id, @RequestBody Estudiante estudiante) {

    }

    @PATCH
    @Path("/{id}")
    public void actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody Estudiante estudiante) {

    }

    @DELETE
    @Path("/{id}")
    public void borrarPorId(@PathParam("id") Integer id) {
  
    }
}