package br.com.desafio.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.google.inject.Singleton;

import br.com.desafio.dao.ColaboradorDAO;
import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Cargo;
import br.com.desafio.modelo.Colaborador;
import br.com.desafio.modelo.Departamento;
import br.com.desafio.modelo.TipoContato;


@Singleton
@SuppressWarnings("unchecked")
public class ColaboradorDAOImpl extends GenericDAORepository<Colaborador> implements ColaboradorDAO {

	private static final int TOTAL_REGISTROS_PAGINA = 6;

	@Override
	public List<Colaborador> listarPaginado(PaginacaoDTO paginacaoDTO) {
		
		StringBuilder sb = new StringBuilder();
		sb.append("select c from Colaborador c ");
		sb.append(" where 1 = 1 ");
		preencherDadosFiltro(paginacaoDTO, sb);
		preencherOrdenacao(sb);
		Query query = getEntityManager().createQuery(sb.toString());
		configurarPaginacao(paginacaoDTO, query);
		preencherFiltro(paginacaoDTO, query);
		return query.getResultList();
	}

	private void preencherFiltro(PaginacaoDTO paginacaoDTO, Query query) {
		if(paginacaoDTO.getFiltro() !=null){
			query.setParameter("filtro", "%" +paginacaoDTO.getFiltro().toUpperCase() + "%");
		}
	}

	private void preencherOrdenacao(StringBuilder sb) {
		sb.append(" order by c.nome asc ");
	}

	private void preencherDadosFiltro(PaginacaoDTO paginacaoDTO, StringBuilder sb) {
		if(paginacaoDTO.getFiltro() !=null){
			sb.append(" and UPPER(c.nome) like :filtro ");			
		}
	}

	private void configurarPaginacao(PaginacaoDTO paginacaoDTO, Query query) {
		if(paginacaoDTO.getPage() != null){
			if(paginacaoDTO.getPage() != 1){
				query.setFirstResult(((paginacaoDTO.getPage()- 1) * TOTAL_REGISTROS_PAGINA ) +1);				
			}else{
				query.setFirstResult(paginacaoDTO.getPage()-1);
			}
			query.setMaxResults(TOTAL_REGISTROS_PAGINA);			
		}
	}

	@Override
	public List<Colaborador> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Colaborador obterPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(Colaborador colaborador) {
		getEntityManager().getTransaction().begin();
		StringBuilder sb = new StringBuilder();
		sb.append(" delete from ");
		sb.append(" EN_COLABORADOR");
		sb.append(" where NRO_ID_COLABORADOR = :id ");
		getEntityManager().createNativeQuery(sb.toString()).setParameter("id", colaborador.getId()).executeUpdate();
		getEntityManager().getTransaction().commit();
	}

	@Override
	public List<Cargo> cargos() {
		return getEntityManager().createQuery("FROM Cargo").getResultList();
	}

	@Override
	public List<Departamento> departamentos() {
		return getEntityManager().createQuery("FROM Departamento").getResultList();
	}
	
	@Override
	public List<TipoContato> tiposContato() {
		return getEntityManager().createQuery("FROM TipoContato").getResultList();
	}

}
