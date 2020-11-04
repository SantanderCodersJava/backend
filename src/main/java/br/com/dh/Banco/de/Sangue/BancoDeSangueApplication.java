package br.com.dh.Banco.de.Sangue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.dh.Banco.de.Sangue.model.TipoSanguineo;


@SpringBootApplication
public class BancoDeSangueApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancoDeSangueApplication.class, args);
		
		TipoSanguineo s1 = new TipoSanguineo();
		
		System.out.println(s1.toString());
	}
	
}
