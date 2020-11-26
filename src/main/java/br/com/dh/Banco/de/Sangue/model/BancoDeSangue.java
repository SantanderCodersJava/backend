package br.com.dh.Banco.de.Sangue.model;


import java.util.List;

import javax.persistence.Column;
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
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="bancosangue")
public class BancoDeSangue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_banco")
	private Integer id;
	
	private String nome;
	private String email;
	private String cnpj;
	private String telefone;
	
	@Column(name =  "nome_contato")
	private String nomeContato;	
	
	@Column(name =  "email_contato")
	private String emailContato;
	
	@Column(name =  "telefone_contato")
	private String telefoneContato;
	
	private String cargo;
	private String senha;
	
	@Column(name =  "caminho_img")
	private String caminhoImg;	 	

	@OneToOne(mappedBy = "bancoSangue")
	@JsonIgnoreProperties("bancoSangue")
	private Endereco endereco; 
	
	@OneToMany(orphanRemoval = true, mappedBy = "bancoSangue")
    @JsonIgnoreProperties("bancoSangue")
    private List<Agendamento> agendamentos;
	
	@OneToMany(mappedBy = "bancoSangue")
	@JsonIgnoreProperties("bancoSangue")
	private List<TipoSanguineo> tipoSanguineo;	
	
}
