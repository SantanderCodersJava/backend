package br.com.dh.Banco.de.Sangue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.Banco.de.Sangue.enums.Sangue;
import br.com.dh.Banco.de.Sangue.model.TipoSanguineo;

public interface TipoSanguineoRepository extends JpaRepository<TipoSanguineo, Sangue>{

}
