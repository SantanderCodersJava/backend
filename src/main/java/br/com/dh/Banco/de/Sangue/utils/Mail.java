package br.com.dh.Banco.de.Sangue.utils;

import lombok.Data;

@Data
public class Mail {
	private String fromEmail;
    private String to;
    private String subject;
    private String fromName;
}
