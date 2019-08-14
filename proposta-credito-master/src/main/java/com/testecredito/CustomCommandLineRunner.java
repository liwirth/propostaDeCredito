package com.testecredito;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.testecredito.entity.Proposta;
import com.testecredito.repository.PropostaRepository;


@Component
public class CustomCommandLineRunner implements CommandLineRunner {

	private final Logger logger = LoggerFactory.getLogger(CustomCommandLineRunner.class);

	@Autowired
	private PropostaRepository proposRepository;

	@Override
	public void run(final String... arg0) throws Exception {
		this.logger.info("Criação de propostas iniciais para dataset inicial");
		this.logger.info(this.proposRepository.save(
				new Proposta("João Almeida", "02432569874", "SC", 35, 'M', "Casado(a)", 1, new BigDecimal(10500))
				).toString());
		this.logger.info(this.proposRepository.save(
				new Proposta("Carla Antunes", "36587215896", "RS", 40, 'M', "Viuvo(a)", 2, new BigDecimal(2500))
				).toString());
		this.logger.info(this.proposRepository.save(
				new Proposta("Ricardo Morais", "12398745685", "SP", 19, 'M', "Solteiro(a)", 4, new BigDecimal(5700))
				).toString());
		this.logger.info(this.proposRepository.save(
				new Proposta("Maria Josefina Pereira", "68574132589", "BA", 60, 'M', "Casado(a)", 3, new BigDecimal(190))
				).toString());
	
	}

}
