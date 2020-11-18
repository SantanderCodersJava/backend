package br.com.dh.Banco.de.Sangue.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_endereco;
	
	private String rua; 
	private String numero; 
	private String complemento; 
	private String bairro; 
	private String cidade; 
	private String estado; 
	private String cep; 
	private String latitude; 
	private String longitude;
	
	@ManyToOne( cascade = CascadeType.ALL )
	@JoinColumn(name="id_doador", referencedColumnName="id_doador")
	private Doador doador;
	
	@OneToOne( cascade = CascadeType.ALL )
	@JoinColumn(name="id_banco", referencedColumnName = "id_banco")
	private BancoDeSangue bancosangue;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_empresa", referencedColumnName="id_empresa")
	private Empresa empresa;
	
	
	public Endereco() {
		
	}
	
	public Endereco(String rua, String numero, String complemento, String bairro, String cidade, String estado, 
			String cep, String latitude, String longitude) {
		
		this.rua = rua;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.latitude = latitude;
		this.longitude = longitude;
		
	}
	
	public Integer getId_endereco() {
		return id_endereco;
	}
	public void setId_endereco(Integer id_endereco) {
		this.id_endereco = id_endereco;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Doador getDoador() {
		return doador;
	}

	public void setDoador(Doador doador) {
		this.doador = doador;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public BancoDeSangue getBancosangue() {
		return bancosangue;
	}

	public void setBancosangue(BancoDeSangue bancosangue) {
		this.bancosangue = bancosangue;
	}
	
	
	
	
}
