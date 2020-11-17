package br.com.dh.Banco.de.Sangue.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Empresa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_empresa;
    private String razao_social;
    private String email_empresa;
    private String cnpj_empresa;
    private String telefone_1;
    private String telefone_2;
    private Date data_fundacao;
    private String inscricao_estadual;
    private String nome_contato;
    private String email_contato;
    private String quantidade_colaboradores;
    
    @OneToMany(mappedBy = "empresa")
    @JsonIgnoreProperties("empresa")
    private List<Endereco> enderecos; 
    
    public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Empresa () {}

	public Empresa(Integer id_empresa, String razao_social, String email_empresa, String cnpj_empresa,
			String telefone_1, String telefone_2, Date data_fundacao, String inscricao_estadual, String nome_contato,
			String email_contato, String quantidade_colaboradores) {
		this.id_empresa = id_empresa;
		this.razao_social = razao_social;
		this.email_empresa = email_empresa;
		this.cnpj_empresa = cnpj_empresa;
		this.telefone_1 = telefone_1;
		this.telefone_2 = telefone_2;
		this.data_fundacao = data_fundacao;
		this.inscricao_estadual = inscricao_estadual;
		this.nome_contato = nome_contato;
		this.email_contato = email_contato;
		this.quantidade_colaboradores = quantidade_colaboradores;
	}

	public Integer getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getEmail_empresa() {
		return email_empresa;
	}

	public void setEmail_empresa(String email_empresa) {
		this.email_empresa = email_empresa;
	}

	public String getCnpj_empresa() {
		return cnpj_empresa;
	}

	public void setCnpj_empresa(String cnpj_empresa) {
		this.cnpj_empresa = cnpj_empresa;
	}

	public String getTelefone_1() {
		return telefone_1;
	}

	public void setTelefone_1(String telefone_1) {
		this.telefone_1 = telefone_1;
	}

	public String getTelefone_2() {
		return telefone_2;
	}

	public void setTelefone_2(String telefone_2) {
		this.telefone_2 = telefone_2;
	}

	public Date getData_fundacao() {
		return data_fundacao;
	}

	public void setData_fundacao(Date data_fundacao) {
		this.data_fundacao = data_fundacao;
	}

	public String getInscricao_estadual() {
		return inscricao_estadual;
	}

	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}

	public String getNome_contato() {
		return nome_contato;
	}

	public void setNome_contato(String nome_contato) {
		this.nome_contato = nome_contato;
	}

	public String getEmail_contato() {
		return email_contato;
	}

	public void setEmail_contato(String email_contato) {
		this.email_contato = email_contato;
	}

	public String getQuantidade_colaboradores() {
		return quantidade_colaboradores;
	}

	public void setQuantidade_colaboradores(String quantidade_colaboradores) {
		this.quantidade_colaboradores = quantidade_colaboradores;
	} 
    

}
