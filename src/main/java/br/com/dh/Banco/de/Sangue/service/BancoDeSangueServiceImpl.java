package br.com.dh.Banco.de.Sangue.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.dh.Banco.de.Sangue.exception.SenhaInvalidaException;
import br.com.dh.Banco.de.Sangue.model.BancoDeSangue;
import br.com.dh.Banco.de.Sangue.repository.BancoDeSangueRepository;

@Service
public class BancoDeSangueServiceImpl implements UserDetailsService {

    @Autowired
    private BancoDeSangueRepository bancoDeSangueRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public BancoDeSangue cadastrar(BancoDeSangue bancoSangue) {
        String senhaCriptografada = passwordEncoder.encode(bancoSangue.getSenha());
        bancoSangue.setSenha(senhaCriptografada);
        return bancoDeSangueRepository.save(bancoSangue);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // pega o banco e verifica se o email dele existe
        BancoDeSangue bancoSangue = bancoDeSangueRepository.findByEmail(email)
                .orElseThrow( ()-> new UsernameNotFoundException("Cadastro não encontrado na base de dados"));

        // da a permissão ao banco de usuario comum
        String[] permissaoBanco = new String[]{"USER"};

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
}
