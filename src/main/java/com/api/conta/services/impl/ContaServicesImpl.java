package com.api.conta.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.api.conta.dto.ContaDTO;
import com.api.conta.entities.Cartao;
import com.api.conta.entities.Conta;
import com.api.conta.repositories.ContaRepository;
import com.api.conta.services.ContaServices;

public class ContaServicesImpl implements ContaServices{

	@Autowired
	ContaRepository repository;
	
	private String msgErro;
	
	//private ModelMapper mapper;
	
	private static final Logger log = LoggerFactory.getLogger(ContaServicesImpl.class);
	
	@Override
	public List<ContaDTO> findAll() throws Exception {
		log.info("Buscando todas os registros de cartaos.");
		List<Cartao> cartaos = new ArrayList<Cartao>();
		List<ContaDTO> cartaosRetorno = new ArrayList<ContaDTO>();
		
		try {
			//cartaos = this.repository.findAll().stream().map(cartao-> mapper.mapModels(cartao, ContaDTO.class));
			for(Cartao cartao: cartaos) {
			//	cartaosRetorno.add(mapper.ma);
			}
			log.info("Busca realizada com sucesso");
			return cartaosRetorno;
		}catch (Exception e) {
			msgErro = "Erro ao buscar cartaos. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}

	@Override
	public ContaDTO findById(Integer id_conta) throws Exception {
		log.info("Buscando cartao.");
		Conta conta = new Conta();
		try {
			conta = this.repository.findByIdConta(id_conta);
			if(conta == null) {
				throw new Exception("Sem resultados.");
			}
			log.info("Cartao encontrado.");
			return null;// new ContaDTO(cartao);
		}catch (Exception e) {
			msgErro = "Erro ao buscar cartao. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}

	@Override
	public ContaDTO save(ContaDTO contaDTO) throws Exception {
		if(contaDTO.equals(null)){
			throw new Exception("Pesquisa em branco. ");
		}
		log.info("Salvando cartao");
		Cartao cartao = new Cartao();
		try {
			//cartao = this.repository.save(contaDTO.toEntity());
			//return new ContaDTO(cartao);
			return null;
		}catch (Exception e) {
			msgErro = "Erro ao salvar cartao. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}

	@Override
	public void delete(Integer id_conta) throws Exception {
		Conta conta = new Conta();
		log.info("Deletando cartao...");
		
		try{
			conta = this.repository.findByIdConta(id_conta);
			this.repository.delete(conta);
		}catch (Exception e) {;
			msgErro = "Erro cartao não pode ser deletado. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}
}
