package com.api.conta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.conta.entities.Conta;

@Repository
public interface ContaRepository  extends JpaRepository<Conta, Integer>{
	Conta findByIdConta(Integer idConta);

}
