package com.testecredito.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.testecredito.entity.Proposta;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {
	List<Proposta> findByCpf(String cpf);
}
