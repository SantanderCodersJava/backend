package br.com.dh.Banco.de.Sangue.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Integer id;
	
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
	private BancoDeSangue bancoSangue;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_empresa", referencedColumnName="id_empresa")
	private Empresa empresa;
	
		
}
