package br.com.dh.Banco.de.Sangue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dh.Banco.de.Sangue.model.TipoSanguineo;
import br.com.dh.Banco.de.Sangue.repository.TipoSanguineoRepository;

@RestController
@RequestMapping(value = "/tiposanguineo")
public class TipoSanguineoController{
	
	@Autowired
	private TipoSanguineoRepository tipoSanguineoRepository;

	@GetMapping
	public List<TipoSanguineo> listarTodos() {
		return tipoSanguineoRepository.findAll();
	}
	
	  @ResponseStatus(HttpStatus.CREATED)
	    @PostMapping
	    public TipoSanguineo cadastrar( @RequestBody TipoSanguineo tipoSanguineo){
	        return tipoSanguineoRepository.save(tipoSanguineo);
	    }
	

}
