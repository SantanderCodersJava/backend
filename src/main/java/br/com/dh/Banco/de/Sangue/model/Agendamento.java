package br.com.dh.Banco.de.Sangue.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private Integer id;
	
	@Column(name = "data_agendamento")
	private Date dataAgendamento;
	
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="id_doador")
	private Doador doador;
	
	@ManyToOne
	@JoinColumn(name="id_banco")
	private BancoDeSangue bancoSangue;
	
}
