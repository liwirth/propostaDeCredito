package com.testecredito.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testecredito.entity.Proposta;
import com.testecredito.enums.LimiteAnalise;
import com.testecredito.enums.ResultadoAnalise;
import com.testecredito.repository.PropostaRepository;

@RestController
@RequestMapping("analise")
public class AnaliseCreditoController {
	
	private static final int PONTUACAO_LIMITE_100_500 = 10;
	private static final int PONTUACAO_LIMITE_500_1000 = 15;	
	private static final int PONTUACAO_LIMITE_1000_1500 = 20;
	private static final int PONTUACAO_LIMITE_1500_2000 = 25;
	private static final int PONTUACAO_LIMITE_SUPERIOR_2000 = 30;

	@Autowired
	private PropostaRepository propostaRepository;
	
	
	@PutMapping("/{id}")
	public void analisaProposta(@PathVariable("id") final Long id, @RequestBody final Proposta proposta) {
		
		int pontuacaoCliente = 0;
		
		if(proposta.getRenda().compareTo(new BigDecimal(200)) == -1) {
			proposta.setResultado(ResultadoAnalise.NEGADO.getValor());
			proposta.setLimite(LimiteAnalise.RENDA_BAIXA.getValor());
		}else {
			//valida idade
			if(proposta.getIdade() > 30) {
				pontuacaoCliente += 5;
			}else {
				pontuacaoCliente += 1;
			}
			
			//valida renda
			if(proposta.getRenda().compareTo(new BigDecimal(10000)) == 1) {
				pontuacaoCliente += 25;
			}else if(proposta.getRenda().compareTo(new BigDecimal(5000)) == 1) {
				pontuacaoCliente += 20;
			}else if(proposta.getRenda().compareTo(new BigDecimal(4000)) == 1) {
				pontuacaoCliente += 15;
			}else if(proposta.getRenda().compareTo(new BigDecimal(2000)) == 1) {
				pontuacaoCliente += 10;
			}else {
				pontuacaoCliente += 5;
			}
			
			//valida dependentes
			switch(proposta.getDependentes()) {
				case 1: pontuacaoCliente += 10; break;
				case 2: pontuacaoCliente += 5; break;
				case 3: pontuacaoCliente += 2; break;
				case 4: pontuacaoCliente += 1; break;
				default: break;
			}
			
			//Verifica pontuação após validações
			if(pontuacaoCliente >= PONTUACAO_LIMITE_100_500) {
				proposta.setResultado(ResultadoAnalise.APROVADO.getValor());
				
				if(pontuacaoCliente >= PONTUACAO_LIMITE_SUPERIOR_2000) {
					proposta.setLimite(LimiteAnalise.SUPERIOR_2000.getValor());
				}else if(pontuacaoCliente >= PONTUACAO_LIMITE_1500_2000) {
					proposta.setLimite(LimiteAnalise.ENTRE_1500_2000.getValor());
				}else if(pontuacaoCliente >= PONTUACAO_LIMITE_1000_1500) {
					proposta.setLimite(LimiteAnalise.ENTRE_1000_1500.getValor());
				}else if(pontuacaoCliente >= PONTUACAO_LIMITE_500_1000) {
					proposta.setLimite(LimiteAnalise.ENTRE_500_1000.getValor());
				}else if(pontuacaoCliente >= PONTUACAO_LIMITE_100_500) {
					proposta.setLimite(LimiteAnalise.ENTRE_100_500.getValor());
				}
			}else {
				proposta.setResultado(ResultadoAnalise.NEGADO.getValor());
				proposta.setLimite(LimiteAnalise.REPROVADO_POLITICA_CREDITO.getValor());
			}			
		}		
		System.out.println("PONTUAÇÃO: " + pontuacaoCliente);
		this.propostaRepository.save(proposta);
	}


}
