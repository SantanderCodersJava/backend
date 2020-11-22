package br.com.dh.Banco.de.Sangue.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
import org.springframework.web.server.ResponseStatusException;

import br.com.dh.Banco.de.Sangue.dto.CredenciaisDTO;
import br.com.dh.Banco.de.Sangue.dto.ForgottenPasswordDTO;
import br.com.dh.Banco.de.Sangue.dto.TokenDTO;
import br.com.dh.Banco.de.Sangue.exception.SenhaInvalidaException;
import br.com.dh.Banco.de.Sangue.model.Doador;
import br.com.dh.Banco.de.Sangue.repository.DoadorRepository;
import br.com.dh.Banco.de.Sangue.service.DoadorServiceImpl;
import br.com.dh.Banco.de.Sangue.service.JwtService;
import br.com.dh.Banco.de.Sangue.utils.FileUploadUtil;

@RestController
@RequestMapping(value = "/doadores")
@CrossOrigin
public class DoadorController {

	@Autowired
	private DoadorRepository repository;

	@Autowired
	private DoadorServiceImpl doadorService;

	@Autowired
	private JwtService jwtService;

	@GetMapping
	public List<Doador> listarTodos() {
		return repository.findAll();
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Doador cadastrar(@RequestBody Doador doador) {
		return doadorService.cadastrar(doador);
	}

	@PostMapping("/auth")
	public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO) {
		try {
			Doador doador = Doador.builder().email(credenciaisDTO.getEmail()).senha(credenciaisDTO.getSenha()).build();
			doadorService.autenticar(doador);
			String token = jwtService.gerarToken(doador);
			return new TokenDTO(doador.getEmail(), token);
		} catch (UsernameNotFoundException | SenhaInvalidaException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
	}

	@PostMapping("/upload")
	public String saveFile(@RequestParam("image") MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String uploadDir = "files";
		Date date = new Date();
		String filePrefix = date.getTime() + "-";

		fileName = filePrefix + fileName;

		try {
			FileUploadUtil.saveFile(uploadDir, fileName, file);
		} catch (IOException e) {
			System.out.println("O arquivo não foi salvo" + e);
			return "Error: " + e;
		}

		System.out.println("O arquivo foi salvo");
		return uploadDir + "/" + fileName;
	}

	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable Integer id) {
		repository.deleteById(id);
	}

	@PutMapping(value = "/{id}")
	public Doador atualizar(@PathVariable Integer id, @RequestBody Doador doador) {

		// pego o doador do banco pelo id
		Doador doadorParaAtualizar = repository.findById(id).get();

		// atualizo os dados com os novos dados que veio no requestBody
		doadorParaAtualizar.setId(id);
		doadorParaAtualizar.setNome(doador.getNome());
		doadorParaAtualizar.setCpf(doador.getCpf());
		doadorParaAtualizar.setCaminhoImg(doador.getCaminhoImg());
		doadorParaAtualizar.setDataNascimento(doador.getDataNascimento());
		doadorParaAtualizar.setEmail(doador.getEmail());
		doadorParaAtualizar.setRg(doador.getRg());
		doadorParaAtualizar.setSenha(doador.getSenha());
		doadorParaAtualizar.setSexo(doador.getSexo());
		doadorParaAtualizar.setTelefone(doador.getTelefone());
		doadorParaAtualizar.setTipoSanguineo(doador.getTipoSanguineo());

		// retorno ele atualizado e salvado
		return repository.save(doadorParaAtualizar);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> generateAuthenticatedLink(@RequestBody ForgottenPasswordDTO data)
			throws UsernameNotFoundException {
		doadorService.recoverPassword(data);
		return ResponseEntity.noContent().build();
	}
}
