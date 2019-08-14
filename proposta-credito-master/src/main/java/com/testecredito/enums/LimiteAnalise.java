package com.testecredito.enums;

public enum LimiteAnalise {
	
	RENDA_BAIXA ("Renda baixa"),
	REPROVADO_POLITICA_CREDITO ("Reprovado pela política de crédito"),
	ENTRE_100_500 ("entre 100 - 500"),
	ENTRE_500_1000 ("entre 500 - 1000"),
	ENTRE_1000_1500 ("entre 1000 - 1500"),
	ENTRE_1500_2000 ("entre 1500 - 2000"),
	SUPERIOR_2000 ("superior 2000");
	
	private final String valor; 
	
	LimiteAnalise(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
	
}
