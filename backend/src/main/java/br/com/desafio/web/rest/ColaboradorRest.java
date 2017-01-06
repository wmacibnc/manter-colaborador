package br.com.desafio.web.rest;

import com.google.inject.Inject;

import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Cargo;
import br.com.desafio.modelo.Colaborador;
import br.com.desafio.modelo.Departamento;
import br.com.desafio.modelo.TipoContato;
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
    @Path("lista-colaboradores")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Colaborador> listarColaboradores(PaginacaoDTO paginacaoDTO) {
        return colaboradorService.listarColaboradores(paginacaoDTO);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("salvar-colaborador")
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador salvarColaborador(Colaborador colaborador) {
        return colaboradorService.salvar(colaborador);
    }

    @PUT
    @Path("atualizar-colaborador")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador atualizarColaborador(Colaborador colaborador) {
    	colaboradorService.alterar(colaborador);
        return colaborador;
    }

    @DELETE
    @Path("excluir-colaborador/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Integer excluir(@PathParam("id") int id) {
    	colaboradorService.excluir(id);
        return id;
    }
    
    @GET
    @Path("cargos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cargo> cargos() {
        return colaboradorService.cargos();
    }
    
    
    @GET
    @Path("departamentos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Departamento> departamentos() {
        return colaboradorService.departamentos();
    }
    
    @GET
    @Path("tipos-contato")
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoContato> tiposContato() {
        return colaboradorService.tiposContato();
    }

}
