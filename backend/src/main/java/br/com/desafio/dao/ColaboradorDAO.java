package br.com.desafio.dao;

import java.util.List;

import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Cargo;
import br.com.desafio.modelo.Colaborador;
import br.com.desafio.modelo.Departamento;
import br.com.desafio.modelo.TipoContato;

public interface ColaboradorDAO{
	
	List<Cargo> cargos();
	
	List<Departamento> departamentos();
	
	List<TipoContato> tiposContato();
	
	List<Colaborador> listar();
	
	Colaborador obterPorId(Long id);

	void alterar(Colaborador colaborador);
	    
	void excluir(Colaborador colaborador);
	    
    Colaborador salvar (Colaborador colaborador);
    
    List<Colaborador> listarPaginado(PaginacaoDTO paginacaoDTO);

}
