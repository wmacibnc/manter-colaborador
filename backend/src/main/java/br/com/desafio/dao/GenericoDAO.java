package br.com.desafio.dao;

import java.util.List;

public interface GenericoDAO<T> {

	List<T> listar();
	
	T obterPorId(Long id);

	void alterar(T t);
	    
	void excluir(int id);
	    
    T salvar (T t);
}
