package br.com.dh.Banco.de.Sangue.controller;

import br.com.dh.Banco.de.Sangue.model.Doador;
import br.com.dh.Banco.de.Sangue.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/doadores")
public class DoadorController {

    @Autowired
    private DoadorRepository repository;

    @GetMapping
    public List<Doador> listarTodos(){
        return repository.findAll();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Doador cadastrar( @RequestBody Doador doador){
        return repository.save(doador);
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
