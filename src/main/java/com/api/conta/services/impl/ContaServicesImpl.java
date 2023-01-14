package com.api.conta.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.conta.dto.ContaDTO;
import com.api.conta.entities.Conta;
import com.api.conta.repositories.ContaRepository;
import com.api.conta.services.ContaServices;

@Service
public class ContaServicesImpl implements ContaServices{

	@Autowired
	ContaRepository repository;
	
	private String msgErro;
	
	private ModelMapper mapper = new ModelMapper();
	
	private static final Logger log = LoggerFactory.getLogger(ContaServicesImpl.class);
	
	@Override
	public List<ContaDTO> findAll() throws Exception {
		log.info("Buscando todas os registros de contas.");
		List<Conta> contas = new ArrayList<Conta>();
		List<ContaDTO> contasRetorno = new ArrayList<ContaDTO>();
		
		try {
			contas = this.repository.findAll();
			contas.stream().forEach(conta->contasRetorno.add(mapper.map(conta, ContaDTO.class)));
			log.info("Busca realizada com sucesso");
			return contasRetorno;
		}catch (Exception e) {
			msgErro = "Erro ao buscar contas. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}

	@Override
	public ContaDTO findById(Integer id_conta) throws Exception {
		log.info("Buscando conta.");
		Conta conta = new Conta();
		try {
			conta = this.repository.findByIdConta(id_conta);
			if(conta == null) {
				throw new Exception("Sem resultados.");
			}
			log.info("Conta encontrado.");
			return mapper.map(conta, ContaDTO.class);
		}catch (Exception e) {
			msgErro = "Erro ao buscar conta. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}

	@Override
	public List<ContaDTO> save(List<ContaDTO> contasDTO) throws Exception {
		if(contasDTO.isEmpty()){
			throw new Exception("Pesquisa em branco. ");
		}
		log.info("Salvando conta");
		final List<Conta> contas = new ArrayList<>();
		List<ContaDTO>contaRetorno = new ArrayList<ContaDTO>();
		try {
			contasDTO.forEach(conta->contas.add(mapper.map(conta, Conta.class)));
			this.repository.saveAll(contas).forEach(conta->contaRetorno.add(mapper.map(conta, ContaDTO.class)));
			return contaRetorno;
		}catch (Exception e) {
			msgErro = "Erro ao salvar conta. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}

	@Override
	public void delete(Integer id_conta) throws Exception {
		Conta conta = new Conta();
		log.info("Deletando conta...");
		
		try{
			conta = this.repository.findByIdConta(id_conta);
			this.repository.delete(conta);
		}catch (Exception e) {;
			msgErro = "Erro conta não pode ser deletado. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}
}
