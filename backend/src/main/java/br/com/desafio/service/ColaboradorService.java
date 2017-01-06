package br.com.desafio.service;

import java.util.List;

import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Cargo;
import br.com.desafio.modelo.Colaborador;
import br.com.desafio.modelo.Departamento;
import br.com.desafio.modelo.TipoContato;
import br.com.desafio.modelo.Usuario;

public interface ColaboradorService {

    List<Colaborador> listarColaboradores(PaginacaoDTO paginacaoDTO);
    
    void alterar(Colaborador colaborador);
    
	void excluir(int id);
	    
    Colaborador salvar (Colaborador colaborador);
    
    List<Cargo> cargos();
    
    List<Departamento> departamentos();
    
    List<TipoContato> tiposContato();
    
    Boolean validarUsuario(Usuario usuario);
}
