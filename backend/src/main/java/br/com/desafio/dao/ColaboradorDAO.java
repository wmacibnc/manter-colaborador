package br.com.desafio.dao;

import java.util.List;

import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Colaborador;

public interface ColaboradorDAO{
	
	List<Colaborador> listar();
	
	Colaborador obterPorId(Long id);

	void alterar(Colaborador colaborador);
	    
	void excluir(int id);
	    
    Colaborador salvar (Colaborador colaborador);
    
    List<Colaborador> listarPaginado(PaginacaoDTO paginacaoDTO);

}
