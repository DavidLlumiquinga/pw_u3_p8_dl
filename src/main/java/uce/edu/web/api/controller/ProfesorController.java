package uce.edu.web.api.controller;
import java.util.List;
import java.util.ArrayList;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.Operation;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Profesor;
import uce.edu.web.api.service.IProfesorService;
import uce.edu.web.api.service.to.ProfesorTo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;


@Path("/profesores")
public class ProfesorController extends BaseController {

    @Inject
    private IProfesorService profesorService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ProfesorTo profe = this.profesorService.buscarPorId(id, uriInfo);
        return Response.status(Response.Status.OK).entity(profe).build();
    }

    // ?asignatura=Matemáticas&provincia=Pichincha
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarTodos(@QueryParam("asignatura") String asignatura) {
        return Response.status(Response.Status.OK)
                .entity(this.profesorService.buscarTodos(asignatura))
                .build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Guardar profesores", description = "Esta capacidad permite guardar un profesor")
    public Response guardar(Profesor profesor) {
        this.profesorService.guardar(profesor);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") Integer id, @RequestBody Profesor profesor) {
        profesor.setId(id);
        this.profesorService.actualizarPorId(profesor);
        this.profesorService.actualizarParcialPorId(profesor); // si aplica como en estudiante
        return Response.status(Response.Status.OK).build();
    }

    /*
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody Profesor profesor) {
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
        if (profesor.getFechaNacimiento() != null) {
            p.setFechaNacimiento(profesor.getFechaNacimiento());
        }
        this.profesorService.actualizarParcialPorId(p);
        return Response.status(Response.Status.OK).build();
    }
    */

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.profesorService.borrarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profesor> obtenerHijosPorId(@PathParam("id") Integer id) {
        Profesor p1 = new Profesor();
        Profesor p2 = new Profesor();
        p1.setNombre("Profesor 1");
        p2.setNombre("Profesor 2");

        List<Profesor> estudiantes = new ArrayList<>();
        estudiantes.add(p1);
        estudiantes.add(p2);
        return estudiantes;
    }
}

