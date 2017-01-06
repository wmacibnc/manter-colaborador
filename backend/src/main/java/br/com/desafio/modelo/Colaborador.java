package br.com.desafio.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

	@Column(name = "TXT_LATITUDE", length = 50)
	private String latitude;

	@Column(name = "TXT_LONGITUDE", length = 50)
	private String longitude;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NRO_ID_CARGO")
	private Cargo cargo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "NRO_ID_DEPARTAMENTO")
	private Departamento departamento;

	@OneToMany(mappedBy = "colaborador", targetEntity = Contato.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Contato> listaContato;

	@Column(name = "TXT_COMPETENCIA", length = 4000)
	private String competencia;
	
	@Transient
	@JsonInclude(Include.NON_EMPTY)
	private Contato[] contatos;

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

	/**
	 * 
	 * @return
	 */
	public List<Contato> getListaContato() {
		return listaContato;
	}

	/**
	 * 
	 * @param listaContato
	 */
	public void setListaContato(List<Contato> listaContato) {
		this.listaContato = listaContato;
	}

	/**
	 * @return the competencia
	 */
	public String getCompetencia() {
		return competencia;
	}

	/**
	 * @param competencia
	 *            the competencia to set
	 */
	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the contatos
	 */
	public Contato[] getContatos() {
		return contatos;
	}

	/**
	 * @param contatos the contatos to set
	 */
	public void setContatos(Contato[] contatos) {
		this.contatos = contatos;
	}

}
