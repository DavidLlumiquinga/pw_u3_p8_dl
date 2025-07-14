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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.repository.modelo.Hijo;
import uce.edu.web.api.service.IEstudianteService;
import uce.edu.web.api.service.IHijoService;
import uce.edu.web.api.service.mapper.EstudianteMapper;
import uce.edu.web.api.service.to.EstudianteTo;

@Path("/estudiantes")
public class EstudianteController {

   @Inject
    private IEstudianteService estudianteService;

    @Inject
    private IHijoService hijoService;
 
    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "consultar estudiante por ID",
        description = "este end point permite consultar un estudiante específico por su ID"
    )
    public Response consultarPorId(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        EstudianteTo estu = EstudianteMapper.toTo(this.estudianteService.buscarPorId(id));
        estu.buildURI(uriInfo);
        return Response.status(Response.Status.OK).entity(estu).build();
    }
 
    //?genero=F&provincia=pichincha  SOAP -> XML     RESTFUL-> JSON
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "consultar estudiante",
        description = "este end point permite consultar todos los estudiantes con filtros opcionales"
    )
    public Response consultarTodos(@QueryParam("genero") String genero, @QueryParam("provincia") String provincia) {
        System.out.println(provincia);
        List<Estudiante> estudiantes = this.estudianteService.buscarTodos(genero);
        List<EstudianteTo> estudiantesToList = EstudianteMapper.toToList(estudiantes);
        return Response.status(Response.Status.OK).entity(estudiantesToList).build();
    }
 
    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "guardar estudiante",
        description = "esta capacidad permite guardar un nuevo estudiante en la base de datos"
    )
    public Response guardar(@RequestBody EstudianteTo estudianteTo, @Context UriInfo uriInfo) {
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        this.estudianteService.guardar(estudiante);
        
        EstudianteTo estudianteGuardado = EstudianteMapper.toTo(estudiante);
        estudianteGuardado.buildURI(uriInfo);
        
        return Response.status(Response.Status.CREATED).entity(estudianteGuardado).build();
    }
 
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "actualizar estudiante",
        description = "esta capacidad permite actualizar completamente un estudiante existente"
    )
    public Response actualizar(@PathParam("id") Integer id, @RequestBody EstudianteTo estudianteTo, @Context UriInfo uriInfo) {
        estudianteTo.setId(id);
        Estudiante estudiante = EstudianteMapper.toEntity(estudianteTo);
        this.estudianteService.actualizarPorId(estudiante);
        
        EstudianteTo estudianteActualizado = EstudianteMapper.toTo(estudiante);
        estudianteActualizado.buildURI(uriInfo);
        
        return Response.status(Response.Status.OK).entity(estudianteActualizado).build();
    }
 
    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "actualizar parcialmente estudiante",
        description = "esta capacidad permite actualizar parcialmente un estudiante existente"
    )
    public Response actualizarParcialPorId(@PathParam("id") Integer id, @RequestBody EstudianteTo estudianteTo, @Context UriInfo uriInfo) {
        estudianteTo.setId(id);
        
        // Obtener el estudiante existente
        Estudiante estudianteExistente = this.estudianteService.buscarPorId(id);
        
        // Mapear los cambios del TO a la entidad existente
        if (estudianteTo.getNombre() != null) {
            estudianteExistente.setNombre(estudianteTo.getNombre());
        }
        if (estudianteTo.getApellido() != null) {
            estudianteExistente.setApellido(estudianteTo.getApellido());
        }
        if (estudianteTo.getFechaNacimiento() != null) {
            estudianteExistente.setFechaNacimiento(estudianteTo.getFechaNacimiento());
        }
        if (estudianteTo.getGenero() != null) {
            estudianteExistente.setGenero(estudianteTo.getGenero());
        }

        this.estudianteService.actualizarParcialPorId(estudianteExistente);
        
        EstudianteTo estudianteActualizado = EstudianteMapper.toTo(estudianteExistente);
        estudianteActualizado.buildURI(uriInfo);
        
        return Response.status(Response.Status.OK).entity(estudianteActualizado).build();
    }
 
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "eliminar estudiante",
        description = "esta capacidad permite eliminar un estudiante por su ID"
    )
    public Response borrarPorId(@PathParam("id") Integer id) {
        this.estudianteService.borrarPorId(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/{id}/hijos")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "obtener hijos del estudiante",
        description = "esta capacidad permite obtener todos los hijos de un estudiante específico"
    )
    public Response obtenerHijosPorId(@PathParam("id") Integer id){
        List<Hijo> hijos = this.hijoService.buscarPorId(id);
        return Response.status(Response.Status.OK).entity(hijos).build();
    }
}
