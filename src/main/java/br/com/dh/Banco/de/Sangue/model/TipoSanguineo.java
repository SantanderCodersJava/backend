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

@Table(name="tiposanguineo")
@Entity
public class TipoSanguineo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_tipo_sanguineo;
	@Column
	private Sangue tipoDeSangue;
	
	private Integer quantidade_tipo;
	
	public Integer getId_tipo_sanguineo() {
		return id_tipo_sanguineo;
	}

	@ManyToOne
	@JoinColumn(name="id_banco")
	private BancoDeSangue bancosangue;
	
	@Override
	public String toString() {
		return "TipoSanguineo [id_tipo_sanguineo=" + id_tipo_sanguineo + ", tipoDeSangue=" + tipoDeSangue + "]";
	}


	public void setId_tipo_sanguineo(Integer id_tipo_sanguineo) {
		this.id_tipo_sanguineo = id_tipo_sanguineo;
	}

	
	public Sangue getTipoDeSangue() {
		return tipoDeSangue;
	}


	public void setTipoDeSangue(Sangue tipoDeSangue) {
		this.tipoDeSangue = tipoDeSangue;
	}


	public TipoSanguineo() {}

	public TipoSanguineo(Integer Quantidade_tipo) {
		this.quantidade_tipo = Quantidade_tipo;
	}

	public Integer getQuantidade_tipo() {
		return quantidade_tipo;
	}


	public void setQuantidade_tipo(Integer quantidade_tipo) {
		this.quantidade_tipo = quantidade_tipo;
	}
	
	

	

}
