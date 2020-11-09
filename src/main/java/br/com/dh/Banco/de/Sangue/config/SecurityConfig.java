package br.com.dh.Banco.de.Sangue.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
@Autowired
DataSource dataSource;
@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select email,senha from doador where email=?")
		.authoritiesByUsernameQuery("select email,autorizacao from doador where email=?");
	}
	
@Bean
public PasswordEncoder passwordEncoder() {
	return NoOpPasswordEncoder.getInstance();
}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable()              camada de segurança para acessar a aplicação diretamente através de browser
		http.authorizeRequests()
		.antMatchers("/doadores").hasAnyRole("USER", "ADMIN")
		.antMatchers("/doadores").hasRole("ADMIN")
		.antMatchers("/").permitAll()
		.and().formLogin();
		//.and().httpBasic();
	}
	
}
