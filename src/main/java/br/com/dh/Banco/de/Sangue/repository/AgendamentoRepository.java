package br.com.dh.Banco.de.Sangue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.Banco.de.Sangue.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository <Agendamento, Integer> {

}
