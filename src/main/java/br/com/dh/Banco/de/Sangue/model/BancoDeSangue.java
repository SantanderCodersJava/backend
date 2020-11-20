package br.com.dh.Banco.de.Sangue.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name="bancosangue")
public class BancoDeSangue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_banco;
	
	private String nome;
	private String email;
	private String cnpj;
	private String telefone;
	private String nome_contato;
	private String email_contato;
	private String telefone_contato;
	private String cargo;
	private String senha;
	private String caminho_img;
	   
	

	@OneToOne(mappedBy = "bancosangue")
	@JsonIgnoreProperties("bancosangue")
	private Endereco endereco; 
	
	@OneToMany(orphanRemoval = true, mappedBy = "bancosangue")
    @JsonIgnoreProperties("bancosangue")
    private List<Agendamento> agendamentos;
	
	@OneToMany(mappedBy = "bancosangue")
	@JsonIgnoreProperties("bancosangue")
	private List<TipoSanguineo> tipoSanguineo;
	
	public List<TipoSanguineo> getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(List<TipoSanguineo> tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public BancoDeSangue() {}
	
	public BancoDeSangue(Integer id_banco, String nome, String email, String cnpj, String telefone, String nome_contato, String email_contato, String telefone_contato, String cargo, String senha ) {
		this.id_banco = id_banco;
		this.nome = nome;
		this.email = email;
		this.cnpj = cnpj;
		this.telefone = telefone;
		this.nome_contato = nome_contato;
		this.email_contato = email_contato;
		this.telefone_contato = telefone_contato;
		this.cargo = cargo;
		this.senha = senha;
	}

	
	public Integer getId_banco() {
		return id_banco;
	}

	public void setId_banco(Integer id_banco) {
		this.id_banco = id_banco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public String getTelefone_contato() {
		return telefone_contato;
	}

	public void setTelefone_contato(String telefone_contato) {
		this.telefone_contato = telefone_contato;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}	

}
