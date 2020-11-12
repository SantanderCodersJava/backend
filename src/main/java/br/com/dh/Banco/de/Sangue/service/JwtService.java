package br.com.dh.Banco.de.Sangue.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.dh.Banco.de.Sangue.model.Doador;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
<<<<<<< HEAD

	private final String expiracao = "30";

	private final String chaveAssinatura = "YmFuY29zYW5ndWU=";
=======
	@Value("${security.jwt.expiracao}")
	private String expiracao;
	
	@Value("${security.jwt.chave-assinatura}")
	private String chaveAssinatura;
>>>>>>> c8ced3dceb5328b31166030dd310c47f71fd3933
	
	public String gerarToken(Doador doador) {
		long expString = Long.valueOf(expiracao);
		LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
		Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date data = Date.from(instant);
		
		return Jwts
				.builder()
				.setSubject(doador.getEmail())
				.setExpiration(data)
				.signWith(SignatureAlgorithm.HS512, chaveAssinatura)
				.compact();
	}
	
	private Claims obterClaims(String token) throws ExpiredJwtException {
		return Jwts
				.parser()
				.setSigningKey(chaveAssinatura)
				.parseClaimsJws(token)
				.getBody();
	}
	
	public boolean tokenValido(String token) {
		try {
			Claims claims = obterClaims(token);
			Date dataExpiracao = claims.getExpiration();
			LocalDateTime data = dataExpiracao.toInstant()
					.atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(data);
		}catch(Exception e) {
			return false;
		}
	}
	
	public String obterLoginDoador(String token) throws ExpiredJwtException {
		return (String) obterClaims(token).getSubject();
	}
	
}
