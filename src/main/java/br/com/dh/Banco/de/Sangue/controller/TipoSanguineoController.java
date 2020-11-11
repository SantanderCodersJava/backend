package br.com.dh.Banco.de.Sangue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dh.Banco.de.Sangue.enums.Sangue;
import br.com.dh.Banco.de.Sangue.model.TipoSanguineo;
import br.com.dh.Banco.de.Sangue.repository.TipoSanguineoRepository;

@RestController
@RequestMapping(value = "/tiposanguineo")
public class TipoSanguineoController{
	
	@Autowired
	private TipoSanguineoRepository repository;

	@GetMapping
	public List<TipoSanguineo> listarTodos() {
		return repository.findAll();
	}
	
	  @ResponseStatus(HttpStatus.CREATED)
	    @PostMapping
	    public TipoSanguineo cadastrar( @RequestBody TipoSanguineo tipoSanguineo){
	        return repository.save(tipoSanguineo);
	    }
	  
	  @PutMapping(value = "/{id}")
	  public TipoSanguineo atualizar(@PathVariable Sangue id,@RequestBody TipoSanguineo tipoSanguineo) {
		  
		  TipoSanguineo tipoSanguineoParaAtualizar = repository.findById(id).get();
		  
		  tipoSanguineoParaAtualizar.setQuantidade_tipo(tipoSanguineo.getQuantidade_tipo());
		
		 
		  return repository.save(tipoSanguineoParaAtualizar);
	  }
	  
	

}
