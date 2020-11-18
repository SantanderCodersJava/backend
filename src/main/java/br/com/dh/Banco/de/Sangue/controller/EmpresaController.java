package br.com.dh.Banco.de.Sangue.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.dh.Banco.de.Sangue.model.Empresa;
import br.com.dh.Banco.de.Sangue.repository.EmpresaRepository;
import br.com.dh.Banco.de.Sangue.utils.FileUploadUtil;

@CrossOrigin
@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {
	
	@Autowired
    private EmpresaRepository repository;
	
	@GetMapping
    public List<Empresa> listarTodos(){
        return repository.findAll();
    }
	
	@ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Empresa cadastrar(@RequestBody Empresa empresa){
        return repository.save(empresa);
    }
	

    @CrossOrigin
    @PostMapping("/upload")
    public String saveFile(@RequestParam("image") MultipartFile file) {
    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    	String uploadDir = "files";
    	Date date = new Date();
    	String filePrefix = date.getTime() + "-";
    	
    	fileName = filePrefix + fileName;
    	
    	try {
    		FileUploadUtil.saveFile(uploadDir, fileName, file);
    	}
    	catch(IOException e) {
    		System.out.println("O arquivo não foi salvo" + e);
    		return "Error: " + e; 
    	}
    	
    	System.out.println("O arquivo foi salvo");
    	return uploadDir + "/" + fileName;
    }
	
	
	@DeleteMapping(value = "/{id}")
    public void deletar(@PathVariable Integer id){
        repository.deleteById(id);
    }
	
	@PutMapping(value = "/{id}")
    public Empresa atualizar(@PathVariable Integer id ,@RequestBody Empresa empresa){

        Empresa empresaParaAtualizar = repository.findById(id).get();

        empresaParaAtualizar.setId_empresa(id);
        empresaParaAtualizar.setRazao_social(empresa.getRazao_social());
        empresaParaAtualizar.setEmail_empresa(empresa.getEmail_empresa());
        empresaParaAtualizar.setCnpj_empresa(empresa.getCnpj_empresa());
        empresaParaAtualizar.setTelefone_1(empresa.getTelefone_1());
        empresaParaAtualizar.setTelefone_2(empresa.getTelefone_2());
        empresaParaAtualizar.setData_fundacao(empresa.getData_fundacao());
        empresaParaAtualizar.setInscricao_estadual(empresa.getInscricao_estadual());
        empresaParaAtualizar.setNome_contato(empresa.getNome_contato());
        empresaParaAtualizar.setEmail_contato(empresa.getEmail_contato());
        empresaParaAtualizar.setQuantidade_colaboradores(empresa.getQuantidade_colaboradores());

        return repository.save(empresaParaAtualizar);
    }

}
