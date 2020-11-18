package br.com.dh.Banco.de.Sangue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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


import br.com.dh.Banco.de.Sangue.model.Endereco;
import br.com.dh.Banco.de.Sangue.repository.EnderecoRepository;

@CrossOrigin
@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public List<Endereco> listarTodos() {
		return enderecoRepository.findAll();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Endereco cadastrar(@RequestBody Endereco endereco){
		endereco.setDoador(endereco.getDoador());
		endereco.setEmpresa(endereco.getEmpresa());
		
		if(endereco.getBancosangue() != null) {
			String senhaCriptografada = passwordEncoder.encode(endereco.getBancosangue().getSenha());
		    endereco.getBancosangue().setSenha(senhaCriptografada);		        
		}
		
		endereco.setBancosangue(endereco.getBancosangue());
        return enderecoRepository.save(endereco);
	}

	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable Integer id){
		enderecoRepository.deleteById(id);
	}

	@PutMapping(value = "/{id}")
	public Endereco atualizar(@PathVariable Integer id ,@RequestBody Endereco endereco){

		// pego o endereço do banco pelo id
	    Endereco enderecoParaAtualizar = enderecoRepository.findById(id).get();

	    // atualizo os dados com os novos dados que veio no requestBody
	    enderecoParaAtualizar.setId_endereco(id);
	    enderecoParaAtualizar.setRua(endereco.getRua());
	    enderecoParaAtualizar.setNumero(endereco.getNumero());
	    enderecoParaAtualizar.setComplemento(endereco.getComplemento());
	    enderecoParaAtualizar.setBairro(endereco.getBairro());
	    enderecoParaAtualizar.setCidade(endereco.getCidade());
	    enderecoParaAtualizar.setEstado(endereco.getEstado());
	    enderecoParaAtualizar.setCep(endereco.getCep());
	    
	    
	    // retorno ele atualizado e salvado
	    return enderecoRepository.save(enderecoParaAtualizar);
	}
	

}
