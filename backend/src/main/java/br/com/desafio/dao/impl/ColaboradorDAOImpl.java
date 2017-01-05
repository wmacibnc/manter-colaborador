package br.com.desafio.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.google.inject.Singleton;

import br.com.desafio.dao.ColaboradorDAO;
import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Colaborador;


@Singleton
public class ColaboradorDAOImpl extends GenericDAORepository<Colaborador> implements ColaboradorDAO {

	private static final int TOTAL_REGISTROS_PAGINA = 5;

	@Override
	public List<Colaborador> listarPaginado(PaginacaoDTO paginacaoDTO) {
		StringBuilder sb = new StringBuilder();
		sb.append("select c from Colaborador c ");
		
		if(paginacaoDTO.getFiltro() !=null){
			sb.append(" c.nome like :filtro ");			
		}
		
		Query query = getEntityManager().createQuery(sb.toString());
		if(paginacaoDTO.getPage() != null){
			if(paginacaoDTO.getPage() != 1){
				query.setFirstResult(((paginacaoDTO.getPage()- 1) * TOTAL_REGISTROS_PAGINA ) +1);				
			}else{
				query.setFirstResult(paginacaoDTO.getPage());
			}
			query.setMaxResults(TOTAL_REGISTROS_PAGINA);			
		}
		
		if(paginacaoDTO.getFiltro() !=null){
			query.setParameter("filtro", paginacaoDTO.getFiltro());
		}
		
		return query.getResultList();
	}

}
