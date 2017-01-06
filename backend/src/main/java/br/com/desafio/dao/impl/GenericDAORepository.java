package br.com.desafio.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.desafio.dao.GenericoDAO;

public class GenericDAORepository<T> implements GenericoDAO<T> {

	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<T> listar(Class<T> classe) {
		return getEntityManager().createQuery("FROM " + classe.getSimpleName()).getResultList();
	}

	protected EntityManager getEntityManager() {
		if (entityManager == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("backend");
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}
	
	@Override
	public void alterar(T t) {
		getEntityManager().getTransaction().begin();
		getEntityManager().merge(t);
		getEntityManager().getTransaction().commit();
	}

	@Override
	public void excluir(T t) {
		getEntityManager().getTransaction().begin();
		getEntityManager().remove(t);
		getEntityManager().getTransaction().commit();
	}

	@Override
	public T salvar(T entidade) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(entidade);
		getEntityManager().getTransaction().commit();
		return entidade;
	}

}
