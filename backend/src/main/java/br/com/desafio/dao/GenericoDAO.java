package br.com.desafio.dao;

import java.util.List;

public interface GenericoDAO<T> {

	List<T> listar(Class<T> classe);
	
	void alterar(T t);
	    
	void excluir(T t);
	    
	T salvar(T t);
}
