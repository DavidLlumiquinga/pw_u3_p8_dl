package uce.edu.web.api.controller;

import java.util.List;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.mapper.ProfesorMapper;
import uce.edu.web.api.service.to.ProfesorTo;

@Path("/profesores")
public class ProfesorController {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
  
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        Profesor profesor = this.profesorService.buscarPorId(id);
        
        if (profesor == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Profesor con ID " + id + " no encontrado")
                    .build();
        }
        
        ProfesorTo profesorTo = ProfesorMapper.toTo(profesor);
        profesorTo.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(profesorTo).build();
    }

    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
 
    public Response consultarTodos() {
        List<Profesor> profesores = this.profesorService.buscarTodos();
        if (profesores == null || profesores.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No se encontraron profesores")
                    .build();
        }
        List<ProfesorTo> profesoresToList = profesores.stream()
                .map(ProfesorMapper::toTo)
                .toList();
        return Response.status(Response.Status.OK).entity(profesoresToList).build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "guardar profesor",
        description = "esta capacidad permite guardar un nuevo profesor en la base de datos"
    )
    public Response guardar(@RequestBody ProfesorTo profesorTo, @Context UriInfo uriInfo) {
        if (profesorTo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Los datos del profesor son requeridos")
                    .build();
        }
        
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.profesorService.guardar(profesor);
        
        ProfesorTo profesorGuardado = ProfesorMapper.toTo(profesor);
        if (uriInfo != null) {
            profesorGuardado.buildURI(uriInfo);
        }
        
        return Response.status(Response.Status.CREATED).entity(profesorGuardado).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "actualizar profesor",
        description = "esta capacidad permite actualizar completamente un profesor existente"
    )
    public Response actualizar(@PathParam("id") Integer id, @RequestBody ProfesorTo profesorTo, @Context UriInfo uriInfo) {
        // Verificar que el profesor existe antes de actualizar
        Profesor profesorExistente = this.profesorService.buscarPorId(id);
        if (profesorExistente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Profesor con ID " + id + " no encontrado")
                    .build();
        }
        
        profesorTo.setId(id);
        Profesor profesor = ProfesorMapper.toEntity(profesorTo);
        this.profesorService.actualizarPorId(profesor);
        
        ProfesorTo profesorActualizado = ProfesorMapper.toTo(profesor);
        profesorActualizado.buildURI(uriInfo);
        
        return Response.status(Response.Status.OK).entity(profesorActualizado).build();
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
 
    public Response actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody ProfesorTo profesorTo, @Context UriInfo uriInfo) {
        profesorTo.setId(id);
        
     
        Profesor profesorExistente = this.profesorService.buscarPorId(id);
        
        if (profesorExistente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Profesor con ID " + id + " no encontrado")
                    .build();
        }
   
        if (profesorTo.getNombre() != null) {
            profesorExistente.setNombre(profesorTo.getNombre());
        }
        if (profesorTo.getApellido() != null) {
            profesorExistente.setApellido(profesorTo.getApellido());
        }
        if (profesorTo.getAsignatura() != null) {
            profesorExistente.setAsignatura(profesorTo.getAsignatura());
        }
        if (profesorTo.getFechaNacimiento() != null) {
            profesorExistente.setFechaNacimiento(profesorTo.getFechaNacimiento());
        }

        this.profesorService.actualizarParcialPorId(profesorExistente);
        
        ProfesorTo profesorActualizado = ProfesorMapper.toTo(profesorExistente);
        profesorActualizado.buildURI(uriInfo);
        
        return Response.status(Response.Status.OK).entity(profesorActualizado).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "eliminar profesor",
        description = "esta capacidad permite eliminar un profesor por su ID"
    )
    public Response borrarPorId(@PathParam("id") Integer id) {

        Profesor profesorExistente = this.profesorService.buscarPorId(id);
        if (profesorExistente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Profesor con ID " + id + " no encontrado")
                    .build();
        }
        
        this.profesorService.borrarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "obtener hijos del profesor",
        description = "esta capacidad permite obtener todos los hijos de un profesor específico"
    )
    public Response obtenerHijosPorId(@PathParam("id") Integer id){

        Profesor profesorExistente = this.profesorService.buscarPorId(id);
        if (profesorExistente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Profesor con ID " + id + " no encontrado")
                    .build();
        }

        return Response.status(Response.Status.OK)
                .entity("Los profesores no tienen hijos asociados en este modelo de datos")
                .build();
    }
}