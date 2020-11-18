package br.com.dh.Banco.de.Sangue.service;

import br.com.dh.Banco.de.Sangue.exception.SenhaInvalidaException;
import br.com.dh.Banco.de.Sangue.model.Doador;
import br.com.dh.Banco.de.Sangue.repository.DoadorRepository;
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
}
