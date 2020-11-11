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

import br.com.dh.Banco.de.Sangue.model.BancoDeSangue;
import br.com.dh.Banco.de.Sangue.repository.BancoDeSangueRepository;

@RestController
@RequestMapping(value = "/banco")
public class BancoDeSangueController {
	
	@Autowired
	private BancoDeSangueRepository repository; 
	
	@GetMapping
	public List <BancoDeSangue> listarTodos(){
		return repository.findAll();	
	}
	
	@ResponseStatus(HttpStatus.CREATED)
    @PostMapping
	public BancoDeSangue cadastrar (@RequestBody BancoDeSangue banco) {
		return repository.save(banco);
		
	}
	
	 @DeleteMapping(value = "/{id}")
	 public void deletar(@PathVariable Integer id){
		 repository.deleteById(id);
	}
	 
	 @PutMapping(value = "/{id}")
	 public BancoDeSangue atualizar(@PathVariable Integer id,@RequestBody BancoDeSangue banco){
		 BancoDeSangue bancoParaAtualizar = repository.findById(id).get();
		 
		 bancoParaAtualizar.setId_banco(id);
		 bancoParaAtualizar.setNome(banco.getNome());
		 bancoParaAtualizar.setEmail_instucional(banco.getEmail_instucional());
		 bancoParaAtualizar.setCnpj(banco.getCnpj());
		 bancoParaAtualizar.setTelefone(banco.getTelefone());
		 bancoParaAtualizar.setNome_contato(banco.getNome_contato());
		 bancoParaAtualizar.setEmail_contato(banco.getEmail_contato());
		 bancoParaAtualizar.setTelefone_contato(banco.getTelefone_contato());
		 bancoParaAtualizar.setCargo(banco.getCargo());
		 bancoParaAtualizar.setSenha(banco.getSenha());
		 
		 return repository.save(bancoParaAtualizar);
	 }
}
