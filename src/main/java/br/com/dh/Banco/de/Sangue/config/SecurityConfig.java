package br.com.dh.Banco.de.Sangue.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
@Autowired
DataSource dataSource;
@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	auth.jdbcAuthentication()
		.dataSource(dataSource)
		.usersByUsernameQuery("select email,senha "+"from doador "+"where email=?")
		.authoritiesByUsernameQuery("select email,autorizacao "+"from doador "+"where email=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
	}
}
