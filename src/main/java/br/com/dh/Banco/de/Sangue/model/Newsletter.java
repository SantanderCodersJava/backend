package br.com.dh.Banco.de.Sangue.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Newsletter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_newsletter;
	private String titulo;
	private String destinatario;
	private String observacao;
	private Date data;
	
	public Newsletter() {}
	

	public Newsletter(Integer id_newsletter, String titulo, String destinatario, String observacao, Date data) {
		super();
		this.id_newsletter = id_newsletter;
		this.titulo = titulo;
		this.destinatario = destinatario;
		this.observacao = observacao;
		this.data = data;
	}
	
		
	public Integer getId_newsletter() {
		return id_newsletter;
	}

	public void setId_newsletter(Integer id_newsletter) {
		this.id_newsletter = id_newsletter;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
}
