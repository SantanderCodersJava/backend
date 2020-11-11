package br.com.dh.Banco.de.Sangue.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.dh.Banco.de.Sangue.service.DoadorServiceImpl;
import br.com.dh.Banco.de.Sangue.service.JwtService;

public class JwtAuthFilter extends OncePerRequestFilter {
	
	private JwtService jwtService;
    private DoadorServiceImpl doadorService;
    
    public JwtAuthFilter( JwtService jwtService, DoadorServiceImpl doadorService ) {
        this.jwtService = jwtService;
        this.doadorService = doadorService;
    }
    
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        
        if( authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValido(token);

            if(isValid){
                String emailDoador = jwtService.obterLoginDoador(token);
                UserDetails doador = doadorService.loadUserByUsername(emailDoador);
                UsernamePasswordAuthenticationToken user = new
                        UsernamePasswordAuthenticationToken(doador,null,
                        doador.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

}
