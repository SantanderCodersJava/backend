package br.com.dh.Banco.de.Sangue.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dh.Banco.de.Sangue.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}
