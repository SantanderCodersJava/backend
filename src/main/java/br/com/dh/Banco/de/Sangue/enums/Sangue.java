package br.com.dh.Banco.de.Sangue.enums;


public enum Sangue {
	APOSITIVO("A-positivo"), 
	ANEGATIVO("A-negativo"),
	BPOSITIVO("B-positivo"),
	BNEGATIVO("B-negativo"),
	ABPOSITIVO("AB-positivo"),
	ABNEGATIVO("AB-negativo"),
	OPOSITIVO("O-positivo"),
	ONEGATIVO("O-negativo");

	private String tipoDeSangue;
	
	private Sangue(String tipoDeSangue){
		this.tipoDeSangue = tipoDeSangue;
	}
	
	public String getTipoSanguineo() {
		return tipoDeSangue;
	}
}
