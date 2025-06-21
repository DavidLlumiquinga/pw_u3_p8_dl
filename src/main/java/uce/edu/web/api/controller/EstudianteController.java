package uce.edu.web.api.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.web.api.repository.modelo.Estudiante;
import uce.edu.web.api.service.IEstudianteService;

//tambien suele llamarse recurso
@Path("/estudiantes")
public class EstudianteController {
    //por cada objeto se crea su respectivo, repositori, modelo, service y controller
    //este controler y tiene y representa a la identidad estudainte
    //cada metodo tiene un path

    @Inject
    private IEstudianteService estudianteService;

    @GET
    @Path("/consultarPorId/{id}")
    public Estudiante consultarPorId(@PathParam("id")Integer id) {
        return this.estudianteService.buscarPorId(id); 
    }
}
