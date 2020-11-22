package br.com.dh.Banco.de.Sangue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.dh.Banco.de.Sangue.enums.Sangue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tiposanguineo")
@Entity
public class TipoSanguineo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name =  "id_tipo_sanguineo")
	private Integer id;	
	
	@Column(name = "tipo_de_sangue")
	private Sangue tipoDeSangue;	
	
	@Column(name = "quantidade_tipo")
	private Integer quantidadeTipo;
	
	@ManyToOne
	@JoinColumn(name="id_banco")
	private BancoDeSangue bancoSangue;
	
}
