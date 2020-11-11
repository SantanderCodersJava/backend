package br.com.dh.Banco.de.Sangue.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Agendamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_agendamento;
	
	private Date data_agendamento;
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="id_doador")
	private Doador doador;
	
	@ManyToOne
	@JoinColumn(name="id_banco")
	private BancoDeSangue bancosangue;
	
	

	public Agendamento() {}
	
	public Agendamento(Integer id_agendamento, Date data_agendamento, boolean status) {
		this.id_agendamento = id_agendamento;
		this.data_agendamento = data_agendamento;
		this.status = status;
	}
	
	public Integer getId_agendamento() {
		return id_agendamento;
	}
	public void setId_agendamento(Integer id_agendamento) {
		this.id_agendamento = id_agendamento;
	}
	
	public Date getData_agendamento() {
		return data_agendamento;
	}
	public void setData_agendamento(Date data_agendamento) {
		this.data_agendamento = data_agendamento;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	

}
