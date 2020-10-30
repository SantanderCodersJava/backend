package br.com.dh.Banco.de.Sangue.repository;

import br.com.dh.Banco.de.Sangue.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<Doador, Integer> {
}
