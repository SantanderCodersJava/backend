package br.com.dh.Banco.de.Sangue.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.Banco.de.Sangue.model.BancoDeSangue;

public interface BancoDeSangueRepository extends JpaRepository <BancoDeSangue, Integer>{
	Optional<BancoDeSangue> findByEmail(String email);
	Optional<BancoDeSangue> findOneByEmail(String email);
}
