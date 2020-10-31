package br.com.dh.Banco.de.Sangue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dh.Banco.de.Sangue.model.Agendamento;
import br.com.dh.Banco.de.Sangue.repository.AgendamentoRepository;

@RestController
@RequestMapping(value = "/agendamento")
public class AgendamentoController {
	
	@Autowired
	private AgendamentoRepository repository;
	
	@GetMapping
	public List <Agendamento> listarTodos(){
		return repository.findAll();	
	}
	
	@ResponseStatus(HttpStatus.CREATED)
    @PostMapping
	public Agendamento cadastrar (@RequestBody Agendamento agendamento) {
		return repository.save(agendamento);
		
	}
	
	 @DeleteMapping(value = "/{id}")
	 public void deletar(@PathVariable Integer id){
		 repository.deleteById(id);
	}
	
	 @PutMapping(value = "/{id}")
	 public Agendamento atualizar(@PathVariable Integer id,@RequestBody Agendamento agendamento){
		 Agendamento agendamentoParaAtualizar = repository.findById(id).get();
		 
		 agendamentoParaAtualizar.setId_agendamento(id);
		 agendamentoParaAtualizar.setData_agendamento(agendamento.getData_agendamento());
		 agendamentoParaAtualizar.setStatus(agendamento.isStatus());
		 
		 return repository.save(agendamentoParaAtualizar);
	 }

}
