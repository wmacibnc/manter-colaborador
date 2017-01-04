package br.com.desafio.web.rest;

import com.google.inject.Inject;

import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Colaborador;
import br.com.desafio.service.ColaboradorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/colaborador")
public class ColaboradorRest {

    private final ColaboradorService colaboradorService;

    @Inject
    public ColaboradorRest(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("lista-colaboradores")
    public List<Colaborador> listarColaboradores(PaginacaoDTO paginacaoDTO) {
    	System.out.println(paginacaoDTO);
        return colaboradorService.listarColaboradores();
    }

}
