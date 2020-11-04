package br.com.dh.Banco.de.Sangue.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.dh.Banco.de.Sangue.enums.Sangue;


@Entity
public class TipoSanguineo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_tipo_sanguineo;
	@Column
	private Sangue tipoDeSangue;
	
	
	public Integer getId_tipo_sanguineo() {
		return id_tipo_sanguineo;
	}


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
	
	

	

}
