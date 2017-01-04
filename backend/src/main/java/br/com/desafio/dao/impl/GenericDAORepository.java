package br.com.desafio.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.desafio.dao.GenericoDAO;

public class GenericDAORepository<T> implements GenericoDAO<T> {

	private T t;
	
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<T> listar() {
		return getEntityManager().createQuery("FROM " + "Colaborador").getResultList();
	}

	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("backend");
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	@Override
	public T obterPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T salvar(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alterar(T t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
	}

}
