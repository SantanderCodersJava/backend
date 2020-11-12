package br.com.dh.Banco.de.Sangue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.dh.Banco.de.Sangue.dto.CredenciaisDTO;
import br.com.dh.Banco.de.Sangue.dto.TokenDTO;
import br.com.dh.Banco.de.Sangue.exception.SenhaInvalidaException;
import br.com.dh.Banco.de.Sangue.model.BancoDeSangue;
import br.com.dh.Banco.de.Sangue.repository.BancoDeSangueRepository;
import br.com.dh.Banco.de.Sangue.service.BancoDeSangueServiceImpl;
import br.com.dh.Banco.de.Sangue.service.JwtServiceBanco;

@RestController
@RequestMapping(value = "/banco")
public class BancoDeSangueController {
	
	@Autowired
	private BancoDeSangueRepository repository;
	
	@Autowired
    private BancoDeSangueServiceImpl bancoSangueService;
    
    @Autowired
    private JwtServiceBanco jwtService;
	
	@GetMapping
	public List <BancoDeSangue> listarTodos(){
		return repository.findAll();	
	}
	
	@ResponseStatus(HttpStatus.CREATED)
    @PostMapping
	public BancoDeSangue cadastrar (@RequestBody BancoDeSangue banco) {
		return bancoSangueService.cadastrar(banco);
	}
	
	@PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO){
        try {
            BancoDeSangue banco = BancoDeSangue.builder()
                    .email(credenciaisDTO.getEmail())
                    .senha(credenciaisDTO.getSenha())
                    .build();
            bancoSangueService.autenticar(banco);
            String token = jwtService.gerarToken(banco);
            return new TokenDTO(banco.getEmail(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
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
		 bancoParaAtualizar.setEmail(banco.getEmail());
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
