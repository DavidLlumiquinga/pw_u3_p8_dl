package uce.edu.web.api.controller;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

   @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response consultarPorId(@PathParam("id")Integer id) {
        return Response.status(227).entity(this.profesorService.buscarPorId(id)).build(); 
    }

    //?genero=M&provincia=Pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarTodos(@QueryParam("asignatura") String asignatura) {
    
        return Response.status(Response.Status.OK).entity(this.profesorService.buscarTodos(asignatura)).build();
            
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar profesores",description = "Esta capacidad permite guardar un profesor")
    //Puede tener o no tener el @RequestBody

     public Response guardar(Profesor profesor) {
        profesorService.guardar(profesor);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        this.profesorService.actualizarParcialPorId(profesor); 
        return Response.status(Response.Status.OK).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        Profesor e=this.profesorService.buscarPorId(id);
        if(profesor.getNombre() != null) {
            e.setNombre(profesor.getNombre());
        }
        if(profesor.getApellido() != null) {
            e.setApellido(profesor.getApellido());
        }
        if(profesor.getFechaNacimiento() != null) {
            e.setFechaNacimiento(profesor.getFechaNacimiento());
        }

        this.profesorService.actualizarParcialPorId(e);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/{id}")
        @Consumes(MediaType.APPLICATION_JSON)
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}