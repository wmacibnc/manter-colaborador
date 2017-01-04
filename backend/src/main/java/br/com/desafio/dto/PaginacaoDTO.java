package br.com.desafio.dto;

import java.io.Serializable;

public class PaginacaoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer page;
	private String filtro;

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the filtro
	 */
	public String getFiltro() {
		return filtro;
	}

	/**
	 * @param filtro
	 *            the filtro to set
	 */
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
