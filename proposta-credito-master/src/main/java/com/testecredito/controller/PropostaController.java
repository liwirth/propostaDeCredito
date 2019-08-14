package com.testecredito.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.testecredito.entity.Proposta;
import com.testecredito.repository.PropostaRepository;


@RestController
@RequestMapping("propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository propostaRepository;	
	
	@Autowired
	private AnaliseCreditoController analiseCreditoController;

	@GetMapping
	public List<Proposta> getAllPropostas() {
		return (List<Proposta>) this.propostaRepository.findAll();
	}

	@GetMapping("/{id}")
	public Proposta getProposta(@PathVariable("id") final Proposta proposta) {
		return proposta;
	}
	
	@GetMapping("/filter/{cpf}")
	public List<Proposta> getByCpf(@PathVariable("cpf") final String cpf) {
		return (List<Proposta>) this.propostaRepository.findByCpf(cpf);
	}

	@PostMapping
	public ResponseEntity<Proposta> addProposta(@RequestBody final Proposta proposta) {
		final Proposta newProposta = this.propostaRepository.save(proposta);		
		

		final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newProposta.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/{id}")
	public void analisarProposta(@PathVariable("id") final Long id, @RequestBody final Proposta proposta) {
		this.analiseCreditoController.analisaProposta(id, proposta);
	}

}
