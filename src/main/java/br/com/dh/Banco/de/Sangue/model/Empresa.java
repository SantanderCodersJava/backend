package br.com.dh.Banco.de.Sangue.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Empresa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa")
    private Integer id;
	
	@Column(name = "razao_social")
    private String razaoSocial;
	
	@Column(name = "email_empresa")
    private String emailEmpresa;
	
	@Column(name = "cnpj_empresa")
    private String cnpjEmpresa;
	
	@Column(name = "telefone_1")
    private String telefone1;
	
	@Column(name = "telefone_2")
    private String telefone2;
	
	@Column(name = "data_fundacao")
    private Date dataFundacao;
	
	@Column(name = "inscricao_estadual")
    private String inscricaoEstadual;
	
	@Column(name = "nome_contato")
    private String nomeContato;
	
	@Column(name = "email_contato")
    private String emailContato;
	
	@Column(name = "quantidade_colaboradores")
    private String quantidadeColaboradores;
	
	@Column(name = "cargo_empresa")
	private String cargoEmpresa;
    
    @OneToMany(mappedBy = "empresa")
    @JsonIgnoreProperties("empresa")
    private List<Endereco> enderecos; 

}
