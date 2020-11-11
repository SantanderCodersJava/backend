package br.com.dh.Banco.de.Sangue.exception;

public class SenhaInvalidaException extends RuntimeException{
	
	public SenhaInvalidaException() {
        super("Senha inválida");
    }
}