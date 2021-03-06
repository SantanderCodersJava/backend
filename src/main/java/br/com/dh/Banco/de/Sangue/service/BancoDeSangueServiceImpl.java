package br.com.dh.Banco.de.Sangue.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.dh.Banco.de.Sangue.dto.ForgottenPasswordDTO;
import br.com.dh.Banco.de.Sangue.exception.SenhaInvalidaException;
import br.com.dh.Banco.de.Sangue.model.BancoDeSangue;
import br.com.dh.Banco.de.Sangue.repository.BancoDeSangueRepository;
import br.com.dh.Banco.de.Sangue.utils.Mail;

@Service
public class BancoDeSangueServiceImpl implements UserDetailsService {

    @Autowired
    private BancoDeSangueRepository bancoDeSangueRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtServiceBanco jwtServiceBanco;
    
    @Autowired
    private EmailService emailService;

    public BancoDeSangue cadastrar(BancoDeSangue bancoSangue) {
        String senhaCriptografada = passwordEncoder.encode(bancoSangue.getSenha());
        bancoSangue.setSenha(senhaCriptografada);
        bancoSangue.setEndereco(bancoSangue.getEndereco());
        return bancoDeSangueRepository.save(bancoSangue);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // pega o banco e verifica se o email dele existe
        BancoDeSangue bancoSangue = bancoDeSangueRepository.findByEmail(email)
                .orElseThrow( ()-> new UsernameNotFoundException("Cadastro não encontrado na base de dados"));

        // da a permissão ao banco de usuario comum
        String[] permissaoBanco = new String[]{"ADMIN"};

        // retorna um UserDetail com os dados do banco
        return User
                .builder()
                .username(bancoSangue.getEmail())
                .password(bancoSangue.getSenha())
                .roles(permissaoBanco)
                .build();
    }

    public UserDetails autenticar(BancoDeSangue bancoSangue){
        UserDetails user = loadUserByUsername(bancoSangue.getEmail());
        boolean senhasBatem = passwordEncoder.matches( bancoSangue.getSenha(), user.getPassword());

        if (senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }
    
    public void recoverPassword(ForgottenPasswordDTO dto) {
    	Optional<BancoDeSangue> userOpt = bancoDeSangueRepository.findByEmail(dto.getEmail());
    	if(userOpt.isPresent()) {
    		BancoDeSangue banco = userOpt.get();
    		String token = jwtServiceBanco.gerarToken(banco);
    		
    		Mail mail = new Mail();
    		mail.setTo(dto.getEmail());
    		mail.setSubject("Recuperação de senha - Doo Amor");
    		emailService.sendEmail(mail, token);
    	}
    }
}
