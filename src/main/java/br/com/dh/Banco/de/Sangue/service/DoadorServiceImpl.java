package br.com.dh.Banco.de.Sangue.service;

import br.com.dh.Banco.de.Sangue.dto.ForgottenPasswordDTO;
import br.com.dh.Banco.de.Sangue.exception.SenhaInvalidaException;
import br.com.dh.Banco.de.Sangue.model.Doador;
import br.com.dh.Banco.de.Sangue.repository.DoadorRepository;
import br.com.dh.Banco.de.Sangue.utils.Mail;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DoadorServiceImpl implements UserDetailsService {

    @Autowired
    private DoadorRepository doadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private EmailService emailService;

    public Doador cadastrar(Doador doador) {
        String senhaCriptografada = passwordEncoder.encode(doador.getSenha());
        doador.setSenha(senhaCriptografada);
        doador.setEnderecos(doador.getEnderecos());
        return doadorRepository.save(doador);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // pega o doador e verifica se o email dele existe
        Doador doador = doadorRepository.findByEmail(email)
                .orElseThrow( ()-> new UsernameNotFoundException("Doador não encontrado na base de dados"));

        // da a permissão ao doador de usuario comum
        String[] permissaoDoador = new String[]{"USER"};

        // retorna um UserDetail com os dados do doador
        return User
                .builder()
                .username(doador.getEmail())
                .password(doador.getSenha())
                .roles(permissaoDoador)
                .build();
    }

    public UserDetails autenticar(Doador doador){
        UserDetails user = loadUserByUsername(doador.getEmail());
        boolean senhasBatem = passwordEncoder.matches( doador.getSenha(), user.getPassword());

        if (senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }
    
    public void recoverPassword(ForgottenPasswordDTO dto) {
    	Optional<Doador> userOpt = doadorRepository.findByEmail(dto.getEmail());
    	if(userOpt.isPresent()) {
    		Doador doador = userOpt.get();
    		String token = jwtService.gerarToken(doador);
    		
    		Mail mail = new Mail();
    		mail.setTo(dto.getEmail());
    		mail.setSubject("Recuperação de senha - Doo Amor");
    		emailService.sendEmail(mail, token);
    	}
    }
}
