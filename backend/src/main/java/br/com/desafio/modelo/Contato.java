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
@Table(name = "EN_CONTATO")
public class Contato extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NRO_ID_CONTATO")
	@GeneratedValue
	private Long id;

	@Column(name = "DESC_CONTATO", length = 50)
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NRO_ID_TIPO_CONTATO")
	private TipoContato tipoContato;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NRO_ID_COLABORADOR")
	private Colaborador colaborador;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoContato getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(TipoContato tipoContato) {
		this.tipoContato = tipoContato;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

}
