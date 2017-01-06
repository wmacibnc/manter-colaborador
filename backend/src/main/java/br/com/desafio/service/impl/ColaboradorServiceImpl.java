package br.com.desafio.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import br.com.desafio.dao.ColaboradorDAO;
import br.com.desafio.dto.PaginacaoDTO;
import br.com.desafio.modelo.Cargo;
import br.com.desafio.modelo.Colaborador;
import br.com.desafio.modelo.Departamento;
import br.com.desafio.modelo.TipoContato;
import br.com.desafio.modelo.Usuario;
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
		colaboradorDAO.excluir(preencherObjetoColaborador(id));
	}

	private Colaborador preencherObjetoColaborador(int id) {
		Integer identificador = id;
		Colaborador colaborador = new Colaborador();
		colaborador.setId(identificador.longValue());
		return colaborador;
	}

	@Override
	public Colaborador salvar(Colaborador colaborador) {
		return colaboradorDAO.salvar(colaborador);
	}
	
	@Override
	public List<Cargo> cargos() {
		return colaboradorDAO.cargos();
	}
	
	@Override
	public List<Departamento> departamentos() {
		return colaboradorDAO.departamentos();
	}
	
	@Override
	public List<TipoContato> tiposContato() {
		return colaboradorDAO.tiposContato();
	}

	@Override
	public Boolean validarUsuario(Usuario usuario) {
		return colaboradorDAO.validarUsuario(usuario);
	}

}
