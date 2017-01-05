package br.com.desafio.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.desafio.dao.ColaboradorDAO;
import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Colaborador;
import br.com.desafio.service.ColaboradorService;

@Singleton
public class ColaboradorServiceImpl implements ColaboradorService {

	private final ColaboradorDAO colaboradorDAO;

	@Inject
	public ColaboradorServiceImpl(ColaboradorDAO colaboradorDAO) {
		this.colaboradorDAO = colaboradorDAO;
	}

	@Override
	public List<Colaborador> listarColaboradores(PaginacaoDTO paginacaoDTO) {
		return colaboradorDAO.listarPaginado(paginacaoDTO);
	}

	@Override
	public void alterar(Colaborador colaborador) {
		colaboradorDAO.alterar(colaborador);
	}

	@Override
	public void excluir(int id) {
		colaboradorDAO.excluir(id);
	}

	@Override
	public Colaborador salvar(Colaborador colaborador) {
		return colaboradorDAO.salvar(colaborador);
	}

}
