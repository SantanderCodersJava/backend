package br.com.dh.Banco.de.Sangue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dh.Banco.de.Sangue.model.Newsletter;
import br.com.dh.Banco.de.Sangue.repository.NewsletterRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/newsletter")
public class NewsleterController {

		@Autowired
		private NewsletterRepository repository;
		
				
		@GetMapping
		public List<Newsletter> listarTodos(){
			return repository.findAll();
		}
		
		@ResponseStatus(HttpStatus.CREATED)
		@PostMapping
		public Newsletter cadastrar (@RequestBody Newsletter newsletter) {
			return repository.save(newsletter);
		}
		
		@DeleteMapping(value = "/{id}")
			public void deletar(@PathVariable Integer id) {
				repository.deleteById(id);
			}
		
		@PutMapping(value = "/{id}")
		public Newsletter atualizar(@PathVariable Integer id,@RequestBody Newsletter newsletter){
			Newsletter newsletterParaAtualizar = repository.findById(id).get();
			
			newsletterParaAtualizar.setId_newsletter(id);
			newsletterParaAtualizar.setTitulo(newsletter.getTitulo());
			newsletterParaAtualizar.setDestinatario(newsletter.getDestinatario());
			newsletterParaAtualizar.setObservacao(newsletter.getObservacao());
			newsletterParaAtualizar.setData(newsletter.getData());
			
			return repository.save(newsletterParaAtualizar);
			
		}
		
}
