package br.com.desafio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "EN_COMPETENCIA")
public class Competencia extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NRO_ID_COMPETENCIA")
	@GeneratedValue
	private Long id;

	@Column(name = "DESC_COMPETENCIA", length = 30)
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NRO_ID_COLABORADOR")
	private Colaborador colaborador;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the colaborador
	 */
	public Colaborador getColaborador() {
		return colaborador;
	}

	/**
	 * @param colaborador
	 *            the colaborador to set
	 */
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

}
