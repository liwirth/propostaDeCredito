package com.testecredito.enums;

public enum ResultadoAnalise {
	
	APROVADO("Aprovado"),
	NEGADO("Negado");
	
	private final String valor;

	ResultadoAnalise(String valor){
		this.valor = valor;
	}
	
	public String getValor() {
		return valor;
	}	
}
