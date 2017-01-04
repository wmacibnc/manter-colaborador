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
@Table(name = "EN_COLABORADOR")
public class Colaborador extends EntidadeBase {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "NRO_ID_COLABORADOR")
	@GeneratedValue
	private Long id;

	@Column(name = "NME_COLABORADOR", length = 50)
	private String nome;

	@Column(name = "TXT_BIOGRAFIA", length = 4000)
	private String biografia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NRO_ID_CARGO")
	private Cargo cargo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NRO_ID_DEPARTAMENTO")
	private Departamento departamento;

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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the biografia
	 */
	public String getBiografia() {
		return biografia;
	}

	/**
	 * @param biografia
	 *            the biografia to set
	 */
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	/**
	 * @return the cargo
	 */
	public Cargo getCargo() {
		return cargo;
	}

	/**
	 * @param cargo
	 *            the cargo to set
	 */
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the departamento
	 */
	public Departamento getDepartamento() {
		return departamento;
	}

	/**
	 * @param departamento
	 *            the departamento to set
	 */
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

}
