package br.com.dh.Banco.de.Sangue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Agendamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_agendamento")
	@JsonIgnoreProperties("agendamento")
	private Integer id;
	
	@Column(name = "data_agendamento")
	private Date dataAgendamento;
	
	private boolean status;
	
	@Column(name = "data_doacao")
	private Date dataDoacao;
	
	@Column(name = "horario")
	private Integer horarioDoacao;
	
	@ManyToOne
	@JoinColumn(name="id_doador")
	@JsonIgnoreProperties("doador")
	private Doador doador;
	
	@ManyToOne
	@JoinColumn(name="id_banco")
	@JsonIgnoreProperties("banco")
	private BancoDeSangue bancoSangue;
	
}
