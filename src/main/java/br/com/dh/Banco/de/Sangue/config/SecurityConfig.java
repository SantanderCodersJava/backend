package br.com.dh.Banco.de.Sangue.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.dh.Banco.de.Sangue.service.BancoDeSangueServiceImpl;
import br.com.dh.Banco.de.Sangue.service.DoadorServiceImpl;
import br.com.dh.Banco.de.Sangue.service.JwtService;
import br.com.dh.Banco.de.Sangue.service.JwtServiceBanco;




@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
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

	}
	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.csrf().disable()              //camada de segurança para acessar a aplicação diretamente através de browser
		.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/doadores/**")
			.permitAll()
			.antMatchers(HttpMethod.GET, "/doadores/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/banco/**")
			.permitAll()
			.antMatchers(HttpMethod.POST, "/doadores")
        		.permitAll()
        	.antMatchers(HttpMethod.POST, "/banco")
        		.permitAll()       
        	.antMatchers(HttpMethod.POST, "/empresas/**")
        		.permitAll()  
        	.antMatchers(HttpMethod.POST, "/enderecos/**")
        		.permitAll()
        	.antMatchers(HttpMethod.POST, "/tiposanguineo/**")
        		.permitAll()
        	.antMatchers("/empresas")
        		.permitAll()   
        	
        	//.antMatchers("/banco")
        	//	.permitAll()  
        	
        	.anyRequest().authenticated()        	         
	        .and()
	        	.sessionManagement()
	        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	         .and()
	        	.addFilterBefore( jwtFilter(), UsernamePasswordAuthenticationFilter.class);

	}

}
