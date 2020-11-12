package br.com.dh.Banco.de.Sangue.controller;

import br.com.dh.Banco.de.Sangue.dto.CredenciaisDTO;
import br.com.dh.Banco.de.Sangue.dto.TokenDTO;
import br.com.dh.Banco.de.Sangue.exception.SenhaInvalidaException;
import br.com.dh.Banco.de.Sangue.model.Doador;
import br.com.dh.Banco.de.Sangue.repository.DoadorRepository;
import br.com.dh.Banco.de.Sangue.service.DoadorServiceImpl;
import br.com.dh.Banco.de.Sangue.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/doadores")
public class DoadorController {

    @Autowired
    private DoadorRepository repository;
<<<<<<< HEAD

    @Autowired
    private DoadorServiceImpl service;
=======
    
    @Autowired
    private DoadorServiceImpl doadorService;
    
    @Autowired
    private JwtService jwtService;
>>>>>>> c8ced3dceb5328b31166030dd310c47f71fd3933

    @GetMapping
    public List<Doador> listarTodos(){
        return repository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Doador cadastrar( @RequestBody Doador doador){
        return doadorService.cadastrar(doador);
    }
    
    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO){
        try {
            Doador doador = Doador.builder()
                    .email(credenciaisDTO.getEmail())
                    .senha(credenciaisDTO.getSenha())
                    .build();
            doadorService.autenticar(doador);
            String token = jwtService.gerarToken(doador);
            return new TokenDTO(doador.getEmail(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id){
        repository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public Doador atualizar(@PathVariable Integer id ,@RequestBody Doador doador){

        // pego o doador do banco pelo id
        Doador doadorParaAtualizar = repository.findById(id).get();

        // atualizo os dados com os novos dados que veio no requestBody
        doadorParaAtualizar.setId_doador(id);
        doadorParaAtualizar.setNome(doador.getNome());
        doadorParaAtualizar.setCpf(doador.getCpf());
        doadorParaAtualizar.setCaminho_img(doador.getCaminho_img());
        doadorParaAtualizar.setData_nascimento(doador.getData_nascimento());
        doadorParaAtualizar.setEmail(doador.getEmail());
        doadorParaAtualizar.setRg(doador.getRg());
        doadorParaAtualizar.setSenha(doador.getSenha());
        doadorParaAtualizar.setSexo(doador.getSexo());
        doadorParaAtualizar.setTelefone(doador.getTelefone());
        doadorParaAtualizar.setTipo_sanguineo(doador.getTipo_sanguineo());
       

        // retorno ele atualizado e salvado
        return repository.save(doadorParaAtualizar);
    }
}
