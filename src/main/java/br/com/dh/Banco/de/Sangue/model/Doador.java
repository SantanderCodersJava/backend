package br.com.dh.Banco.de.Sangue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.dh.Banco.de.Sangue.enums.Sangue;

import java.util.Date;
import java.util.List;

@Entity
public class Doador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_doador;
    private String nome;
    private String rg;
    private Date data_nascimento;
    private String email;
    private String autorizacao;
    private String cpf;
    private String telefone;
    private String sexo;
    private Sangue tipo_sanguineo;
    private String senha;
    private String caminho_img;
   
    

	@OneToMany(orphanRemoval = true, mappedBy = "doador")
    @JsonIgnoreProperties("doador")
    private List<Endereco> enderecos; 
    
    @OneToMany(orphanRemoval = true, mappedBy = "doador")
    @JsonIgnoreProperties("doador")
    private List<Agendamento> agendamentos;
    

    public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Doador(){}

    public Doador(Integer id_doador, String nome, String rg, Date data_nascimento, String email,  String autorizacao, String cpf, String telefone, String sexo, Sangue tipo_sanguineo, String senha, String caminho_img) {
        this.id_doador = id_doador;
        this.nome = nome;
        this.rg = rg;
        this.data_nascimento = data_nascimento;
        this.email = email;
        this.autorizacao = autorizacao;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.tipo_sanguineo = tipo_sanguineo;
        this.senha = senha;
        this.caminho_img = caminho_img;
       
    }

    public Integer getId_doador() {
        return id_doador;
    }

    public void setId_doador(Integer id_doador) {
        this.id_doador = id_doador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Sangue getTipo_sanguineo() {
        return tipo_sanguineo;
    }

    public void setTipo_sanguineo(Sangue tipo_sanguineo) {
        this.tipo_sanguineo = tipo_sanguineo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCaminho_img() {
        return caminho_img;
    }

    public void setCaminho_img(String caminho_img) {
        this.caminho_img = caminho_img;
    }
    

    public String getAutorizacao() {
		return autorizacao;
	}

	public void setAutorizacao(String autorizacao) {
		this.autorizacao = autorizacao;
	}
    
    
}
