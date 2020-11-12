package br.com.dh.Banco.de.Sangue.config;

<<<<<<< HEAD
import javax.sql.DataSource;

import br.com.dh.Banco.de.Sangue.service.DoadorServiceImpl;
import br.com.dh.Banco.de.Sangue.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;


=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
>>>>>>> c8ced3dceb5328b31166030dd310c47f71fd3933
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
<<<<<<< HEAD


import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;


=======
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.dh.Banco.de.Sangue.service.BancoDeSangueServiceImpl;
import br.com.dh.Banco.de.Sangue.service.DoadorServiceImpl;
import br.com.dh.Banco.de.Sangue.service.JwtService;
import br.com.dh.Banco.de.Sangue.service.JwtServiceBanco;



>>>>>>> c8ced3dceb5328b31166030dd310c47f71fd3933
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
<<<<<<< HEAD
	private DoadorServiceImpl usuarioService;

	@Autowired
	private JwtService jwtService;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
=======
	private DoadorServiceImpl doadorService;
	
	@Autowired
	private BancoDeSangueServiceImpl bancoService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private JwtServiceBanco jwtServiceBanco;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	
	}
	
	@Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, doadorService,jwtServiceBanco, bancoService);
    }
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {	
	auth
    .userDetailsService(doadorService)
    .passwordEncoder(passwordEncoder());
    auth
    .userDetailsService(bancoService)
    .passwordEncoder(passwordEncoder());
>>>>>>> c8ced3dceb5328b31166030dd310c47f71fd3933
	}
	

<<<<<<< HEAD
	@Bean
	public OncePerRequestFilter jwtFilter(){
		return new JwtAuthFilter(jwtService, usuarioService);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
=======

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()              //camada de segurança para acessar a aplicação diretamente através de browser
		.authorizeRequests()
		
			.antMatchers(HttpMethod.POST, "/doadores/**")
			.permitAll()
			
			.antMatchers(HttpMethod.POST, "/banco/**")
			.permitAll()
		
			.antMatchers(HttpMethod.POST, "/doadores")
        		.permitAll()
        		
        	.antMatchers(HttpMethod.POST, "/banco")
        		.permitAll()
        		
        	.anyRequest().authenticated()        	
        
        
        .and()
        	.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        
        .and()
        	.addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);
		;
>>>>>>> c8ced3dceb5328b31166030dd310c47f71fd3933
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/doadores/**").permitAll()
				.anyRequest().authenticated().and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}

}
