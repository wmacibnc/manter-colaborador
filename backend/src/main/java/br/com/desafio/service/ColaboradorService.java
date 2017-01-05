package br.com.desafio.service;

import java.util.List;

import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Colaborador;

public interface ColaboradorService {

    List<Colaborador> listarColaboradores(PaginacaoDTO paginacaoDTO);
    
    void alterar(Colaborador colaborador);
    
	void excluir(int id);
	    
    Colaborador salvar (Colaborador colaborador);
}
